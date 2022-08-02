package com.example.justmoveit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.justmoveit.R;
import com.example.justmoveit.adapters.ImageSliderAdapter;
import com.example.justmoveit.adapters.MoviesAdapter;
import com.example.justmoveit.model.Movie;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    private LinearLayout layoutSliderIndicators;
    private ViewPager2 sliderViewPager;
    private ImageView imageBack;
    private RoundedImageView imageMoviePoster;
    private Movie movie;
//    private Handler sliderHandler;

    private void loadMovieDetails(){
        Intent intent =getIntent();

        movie=(Movie)intent.getSerializableExtra("movie");

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        //intent로 movie 정보 가져옴
        loadMovieDetails();

        layoutSliderIndicators = findViewById(R.id.layoutSliderIndicators);

        imageMoviePoster = findViewById(R.id.imageMoviePoster);
        Picasso.get().load(movie.getImg1()).into(imageMoviePoster);

        setDetail();
        setupSliderViewPager();

        setImageBack();
        setupSliderIndicators(5);
    }
    private  void setImageBack(){
        //뒤로가기 버튼 구현
        imageBack =  findViewById(R.id.imageBack);
        imageBack.setOnClickListener(view ->onBackPressed());

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

        List<String> url=new ArrayList<>();

        url.add(movie.getImg2());
        url.add(movie.getImg3());
        url.add(movie.getImg4());
        url.add(movie.getImg5());
        url.add(movie.getImg6());

//        System.out.println("ddd"+url);


        //Slider Indicator을 바인딩 하기 위한 코드
        sliderViewPager.setAdapter(new ImageSliderAdapter(url));
        sliderViewPager.setVisibility(View.VISIBLE);
        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position%5); //5는 배열 사이즈 (실제 배열 사이즈는 무한 스크롤을 위해 무한대임)
//                sliderHandler.removeCallbacks(sliderRunnable);
//                sliderHandler.postDelayed(sliderRunnable,2000);//2초마다 슬라이드 바뀜

            }
        });


    }
//    private Runnable sliderRunnable =new Runnable() {
//        @Override
//        public void run() {
//            sliderViewPager.setCurrentItem(sliderViewPager.getCurrentItem()+1);
//        }
//    };
    private void setupSliderIndicators(int count){
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){
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
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.background_slider_indicator_active
                ));
            } else {
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
}
