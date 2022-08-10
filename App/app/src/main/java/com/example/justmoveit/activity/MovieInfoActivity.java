package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.MainActivity.movieSP;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.justmoveit.R;
import com.example.justmoveit.adapters.ImageSliderAdapter;
import com.example.justmoveit.adapters.TimesAdapter;
import com.example.justmoveit.api.MovieApi;
import com.example.justmoveit.model.Movie;
import com.example.justmoveit.model.MoviePlayingInfo;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieInfoActivity extends AppCompatActivity {
    private Movie movie;
    private LinearLayout layoutSliderIndicators;
    private ViewPager2 sliderViewPager;
    private Handler sliderHandler = new Handler();

    private void loadMovieDetails(){
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movie_id");
        Gson gson = new Gson();
        movie = gson.fromJson(movieSP.getString(movieId, ""), Movie.class);

        // 서버 통신 스레드 - movie id로 통신 -> 새로운 playinginfo 있으면 SP 저장
        ConnectionThread thread = new ConnectionThread(movieId);
        Log.d("TicketingActivity", "connection thread start");
        thread.start();
        synchronized (thread){
            try {
                Log.d("TicketingActivity", "main thread waiting");
                thread.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        // 서버에서 movie 최신 정보 가져옴
        loadMovieDetails();

        layoutSliderIndicators = findViewById(R.id.layoutSliderIndicators);
        RoundedImageView imgMoviePoster = findViewById(R.id.imageMoviePoster);

        // 영화 포스터 등록
        Picasso.get().load(movie.getImg()).into(imgMoviePoster);
        //영화 디테일 정보 등록
        setDetail();
        // 뷰 페이저 (서브 이미지) 등록
        setupSliderViewPager();
        // 뷰 페이저 하단 인디케이터 등록
        setupSliderIndicators(5);
        // 뒤로가기 버튼 등록
        setImageBack();

        // 영화 상영 시간 버튼
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<String> timeList= new ArrayList<>();

        for(MoviePlayingInfo info: movie.getMoviePlayingInfoList()){
            timeList.add(info.getStartTime());
        }

        TimesAdapter timesAdapter = new TimesAdapter();
        timesAdapter.setTimes(timeList, movie);
        recyclerView.setAdapter(timesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
    }

    private  void setImageBack(){
        ImageView imgBack = findViewById(R.id.imageBack);
        imgBack.setOnClickListener(view -> onBackPressed());
    }

    private void setupSliderViewPager(){
        sliderViewPager = findViewById(R.id.sliderViewPager);
        sliderViewPager.setClipToPadding(false);
        sliderViewPager.setClipChildren(false);
        sliderViewPager.setOffscreenPageLimit(3);
        sliderViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(((page, position) -> {
            float r = 1 -Math.abs(position);
            page.setScaleY(0.85f + r* 0.15f);
        }));
        sliderViewPager.setPageTransformer(compositePageTransformer);

        List<String> movieImgs = new ArrayList<>();
        movieImgs.add(movie.getImg2());
        movieImgs.add(movie.getImg3());
        movieImgs.add(movie.getImg4());
        movieImgs.add(movie.getImg5());
        movieImgs.add(movie.getImg6());

        // Slider Indicator을 바인딩 하기 위한 코드
        sliderViewPager.setAdapter(new ImageSliderAdapter(movieImgs));
        sliderViewPager.setVisibility(View.VISIBLE);
        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position%5); //5는 배열 사이즈 (실제 배열 사이즈는 무한 스크롤을 위해 무한대임)
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,2000);//2초마다 슬라이드 바뀜

            }
        });
    }

    private final Runnable sliderRunnable =new Runnable() {
        @Override
        public void run() {
            sliderViewPager.setCurrentItem(sliderViewPager.getCurrentItem()+1);
        }
    };

    private void setupSliderIndicators(int count){
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8,0,8,0);

        for(int i=0, l=indicators.length; i<l; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.background_slider_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutSliderIndicators.addView(indicators[i]);
            layoutSliderIndicators.setVisibility(View.VISIBLE);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutSliderIndicators.getChildCount();

        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutSliderIndicators.getChildAt(i);
            if (i == position) {    // 활성화 된 인디케이터
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.background_slider_indicator_active
                ));
            } else {  // 비활성화된 인디케이터
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.background_slider_indicator_inactive
                ));
            }
        }
    }

    private void setDetail(){
        TextView textTitle = findViewById(R.id.textTitle);
        TextView textCountry = findViewById(R.id.textCountry);
        TextView textAgeLimit = findViewById(R.id.textAgeLimit);

        TextView textGenre = findViewById(R.id.textGenre);
        TextView textReleaseDate = findViewById(R.id.textReleaseDate);
        TextView textSummary = findViewById(R.id.textSummary);
        TextView textReadMore = findViewById(R.id.textReadMore);
        TextView textEngTitle = findViewById(R.id.textEngTitle);
        TextView textDirector = findViewById(R.id.textDirector);
        TextView textActor = findViewById(R.id.textActor);
        RatingBar ratingBar =findViewById(R.id.ratingBar);
        TextView textRatingBarNum = findViewById(R.id.textRatingBarNum);


        textTitle.setText(movie.getTitle());
        textCountry.setText(movie.getCountry());
        textAgeLimit.setText(movie.getAgeLimit());

        textGenre.setText(movie.getGenre());
        textReleaseDate.setText("개봉일자: "+movie.getReleaseDate());
        textSummary.setText(movie.getSummary());

        textReadMore.setOnClickListener(view -> {
            if(textReadMore.getText().toString().equals("더보기")){
                textSummary.setMaxLines(Integer.MAX_VALUE);
                textSummary.setEllipsize(null);
                textReadMore.setText(R.string.read_less);
            }else{
                textSummary.setMaxLines(4);
                textSummary.setEllipsize(TextUtils.TruncateAt.END);
                textReadMore.setText(R.string.read_more);
            }
        });

        textEngTitle.setText(movie.getEngTitle());
        textDirector.setText(movie.getDirector());
        textActor.setText(movie.getActor());
        ratingBar.setRating(Float.parseFloat(movie.getRating())/2);
        textRatingBarNum.setText((movie.getRating()));
    }

    private static class ConnectionThread extends Thread {
        private String id;

        public ConnectionThread(String movieId){
            this.id = movieId;
        }

        @Override
        public void run() {
            synchronized (this) {
                // 메인 스레드 멈추고 실행할 부분
                getMoviePlayingInfoFromServer();
                Log.d("MovieInfoActivity", "connection thread end");
                notify();
            }
        }

        private void getMoviePlayingInfoFromServer() {
            SharedPreferences.Editor editor = movieSP.edit();

            MovieApi service = MovieApi.retrofit.create(MovieApi.class);
            service.getMoviePlayingInfo(id).enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movie = response.body();
                    if(movie == null) {
                        Log.d("MovieInfoActivity - getMoviesFromServer", "onResponse(): "+response.code());
                        return;
                    }
                    Log.d("MovieInfoActivity - getMoviesFromServer", "onResponse(): success");

                    Gson gson = new Gson();
                    // 서버에서 가져온 movie 정보를 다시 덮어씌워줌
                    String movieId = movie.getMoviePlayingInfoByIndex(0).getMovieId()+"";
                    editor.putString(movieId, gson.toJson(movie));
                    editor.apply();
                    // 이러면 최신 좌석 현황을 저장할 수 있음
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }
    }

}
