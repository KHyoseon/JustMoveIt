package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.LoadingActivity.movieSP;
import static com.example.justmoveit.activity.LoadingActivity.userSP;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.api.UserTicketApi;
import com.example.justmoveit.model.Movie;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_done);

        Intent intent = getIntent();
        ReservedTicket reservedTicket = (ReservedTicket) intent.getExtras().getSerializable("ticket");

        // 만료 여부 - 만료시 배경색 회색으로 변경
        if(reservedTicket.isExpired()){
            ImageView ticketBg = findViewById(R.id.ticket_bg);
            ticketBg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#a5a5aa")));
            findViewById(R.id.reserve_cancel_button).setVisibility(View.INVISIBLE);
        }

        Ticket ticket = reservedTicket.getTicket();

        // 영화 예매 정보 뿌림
        getSetTexts(ticket);

        // 이전 페이지
        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 예매 취소
        findViewById(R.id.reserve_cancel_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ConnectionThread thread = new ConnectionThread(ticket);
                thread.start();
                /*try {
                    thread.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                synchronized (thread) {
                    try {
                        thread.wait();
                        Toast.makeText(TicketInfoActivity.this, "예매 취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void getSetTexts(Ticket ticket) {

        // 영화 포스터 등록
        Gson gson = new Gson();
        ImageView poster = findViewById(R.id.movie_poster);
        Movie movie = gson.fromJson(movieSP.getString(ticket.getMovieId()+"", ""), Movie.class);

        /*ArrayList<Movie> movies = gson.fromJson(movieSP.getString("general_ranking", ""), TypeToken.getParameterized(ArrayList.class, Movie.class).getType());
        Movie movie = null;
        for(Movie m: movies){
            if(m.getTitle().equals(ticket.getMovieTitle())){
                movie = m;
            }
        }*/

        Picasso.get().load(movie.getImg()).into(poster);

        ((TextView) findViewById(R.id.movie_title)).setText(ticket.getMovieTitle());
        ((TextView) findViewById(R.id.reserve_date)).setText(ticket.getReservationTime());
        ((TextView) findViewById(R.id.viewing_date)).setText((ticket.getReservationTime().split(" "))[0] + " " + ticket.getStartTime());
        ((TextView) findViewById(R.id.total_cost)).setText((ticket.getTotalCost()==null? "알 수 없음": ticket.getTotalCost()));

        int[] clsf = ReservedTicket.convertClassificationToInt(ticket.getClassification());
        ((TextView) findViewById(R.id.classification)).setText(
                (clsf[0]>0?"성인 "+clsf[0]+"명  ":"").concat(clsf[1]>0?"어린이 "+clsf[1]+"명":""));

        ((TextView) findViewById(R.id.reserve_seat)).setText(ticket.getSeat().replace(",", ", "));
    }

    private static class ConnectionThread extends Thread {
        private final Ticket ticket;

        public ConnectionThread(Ticket tkt){
            this.ticket = tkt;
        }

        @Override
        public void run() {
            synchronized (this){
                cancelReservation();
                notify();
            }
        }

        private void cancelReservation() {
            UserTicketApi service = UserTicketApi.retrofit.create(UserTicketApi.class);
            Long id = ticket.getTicketId();
            service.cancelTicket(id).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    SharedPreferences.Editor editor = userSP.edit();
                    // SP에서 사용자 예매 정보 불러옴
                    Gson gson = new Gson();
                    String json = userSP.getString("user_tickets", "");
                    ArrayList<ReservedTicket> reservedTickets = gson.fromJson(json, TypeToken.getParameterized(ArrayList.class, ReservedTicket.class).getType());
                    // id에 해당하는 티켓 예매 정보 삭제
                    for(int i=0, n=reservedTickets.size(); i<n; i++){
                        Ticket tkt = reservedTickets.get(i).getTicket();
                        if(Objects.equals(id, tkt.getTicketId())){
                            reservedTickets.remove(i);
                            Log.i("TicketInfoActivity - cancelReservation", "cancel done");
                            break;
                        }
                    }
                    // 다시 삽입
                    editor.putString("user_tickets", gson.toJson(reservedTickets));
                    editor.apply();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("canceling ticket reservation failed", t.getMessage());
                }
            });
        }
    }
}
