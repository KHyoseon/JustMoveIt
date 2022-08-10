package com.example.justmoveit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.model.MoviePlayingInfo;
import com.example.justmoveit.model.ReservedTicket;
import com.example.justmoveit.model.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

public class TicketingActivity extends AppCompatActivity {
    private MoviePlayingInfo moviePlayingInfo;
    private TextView textAdult, textChild, textTotalCost;

    private NumberPicker adultPicker;
    private NumberPicker childPicker;

    private int[] clsf; // 0:성인, 1:어린이
    private int canSelectedSeat, totalCost;
    private static Set<String> selectedSeat;

    private void loadMovie() {
        Intent intent = getIntent();
        moviePlayingInfo = (MoviePlayingInfo) intent.getSerializableExtra("moviePlayingInfo");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);

        canSelectedSeat = 0;
        totalCost = 0;
        clsf = new int[2];
        selectedSeat = new HashSet<>();

        // 예매할 영화 정보 가져옴
        loadMovie();

        // 레이아웃 매핑
        textAdult = findViewById(R.id.textAdult);
        textChild = findViewById(R.id.textChild);
        textTotalCost = findViewById(R.id.textTotalCost);

        // 데이터 뿌림
        ((TextView) findViewById(R.id.textMovieName)).setText(moviePlayingInfo.getMovieTitle());
        ((TextView) findViewById(R.id.textMovieTime)).setText(moviePlayingInfo.getStartTime() + " - " + moviePlayingInfo.getEndTime());

        // 좌석 배치도 - 이미 예매된 좌석 표시
        for(Ticket t: moviePlayingInfo.getTickets()){
            for(String seat: t.getSeat().split(",")){
                int seat_r_id = getResources().getIdentifier(seat, "id", getPackageName());
                findViewById(seat_r_id).setEnabled(false);
            }
        }

        // 인원 설정 넘버 피커
        adultPicker = findViewById(R.id.adult_picker);
        childPicker = findViewById(R.id.child_picker);

        // 넘버피커 - 기본, 최대, 최소값 설정
        setNumberPickerValue();
        // 넘버피커 - 이벤트 리스너 적용
        setOnAllValueChangeListener();

        // 좌석 선택 - 토글 이벤트 리스너 적용
        setAllSeatListener();

        Button payBtn = findViewById(R.id.payment);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 선택 인원과 선택 좌석 수가 다른 경우 return
                if(canSelectedSeat != selectedSeat.size()){
                    Toast.makeText(TicketingActivity.this, "선택한 좌석 수가 인원 수와 다릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // classification 저장
                String classification = ReservedTicket.convertClassificationToString(clsf);

                // seat 저장
                StringBuilder sb = new StringBuilder();
                ArrayList<String> al = new ArrayList<>(selectedSeat);
                Collections.sort(al);
                for(String seat: al){
                    sb.append(seat).append(",");
                }
                sb.setLength(sb.length()-1);
                String seat = sb.toString();

                // 티켓 객체 생성 후 paymentActivity로 넘겨줌
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                String nowDate = simpleDateFormat.format(new Date());
                Ticket ticket = new Ticket(moviePlayingInfo.getMoviePlayingInfoId(), moviePlayingInfo.getMovieId(), moviePlayingInfo.getMovieTitle(), "12세",
                        moviePlayingInfo.getStartTime(), moviePlayingInfo.getEndTime(), "01012345678", classification, nowDate+"", seat, moviePlayingInfo.getTheaterNo(), "12000");

                PaymentActivity paymentActivity = new PaymentActivity(ticket);
                Intent it = new Intent(getApplicationContext(), paymentActivity.getClass());
                startActivity(it);
            }
        });
    }

    private void setNumberPickerValue() {
        adultPicker.setMaxValue(5);
        adultPicker.setMinValue(0);
        adultPicker.setValue(0);

        childPicker.setMaxValue(5);
        childPicker.setMinValue(0);
        childPicker.setValue(0);

    }

    public void setOnAllValueChangeListener() {
        adultPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                textAdult.setText("성인 " + newValue);
                clsf[0] = newValue;
                canSelectedSeat += (newValue - oldValue);
                totalCost += (newValue - oldValue) * 12000;
                textTotalCost.setText(totalCost+"");
            }
        });

        childPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                textChild.setText("어린이 " + newValue);
                clsf[1] = newValue;
                canSelectedSeat += (newValue - oldValue);
                totalCost += (newValue - oldValue) * 12000 * (0.8);
                textTotalCost.setText(totalCost+"");
            }
        });
    }

    public void setAllSeatListener() {
        ((ToggleButton) findViewById(R.id.A01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A05)).setOnCheckedChangeListener(new seatSelectListener());

        ((ToggleButton) findViewById(R.id.B01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B05)).setOnCheckedChangeListener(new seatSelectListener());

        ((ToggleButton) findViewById(R.id.C01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C05)).setOnCheckedChangeListener(new seatSelectListener());

        ((ToggleButton) findViewById(R.id.D01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D05)).setOnCheckedChangeListener(new seatSelectListener());

    }

    class seatSelectListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                if (canSelectedSeat == 0) {
                    Toast.makeText(TicketingActivity.this, "인원을 선택해주세요.", Toast.LENGTH_SHORT).show();
                    compoundButton.setChecked(false);
                } else if (canSelectedSeat <= selectedSeat.size()) {
                    Toast.makeText(TicketingActivity.this, "선택 가능한 좌석 수를 넘었습니다.", Toast.LENGTH_SHORT).show();
                    compoundButton.setChecked(false);
                } else {
                    Log.i("checked", compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
                    selectedSeat.add(compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
                }
            } else {
                Log.i("unChecked", compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
                selectedSeat.remove(compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
            }
        }
    }
}