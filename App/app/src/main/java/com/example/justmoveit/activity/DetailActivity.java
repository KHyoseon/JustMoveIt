package com.example.justmoveit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

//        url = getIntent().getParcelableExtra("imgUrl");
        layoutSliderIndicators = findViewById(R.id.layoutSliderIndicators);

        imageMoviePoster = findViewById(R.id.imageMoviePoster);
        Picasso.get().load("https://movie-phinf.pstatic.net/20220516_144/1652665409592Chvey_JPEG/movie_image.jpg?type=m203_290_2").into(imageMoviePoster);

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

        url.add("https://movie-phinf.pstatic.net/20220426_19/16509365610560EIGm_JPEG/movie_image.jpg?type=m665_443_2");
        url.add("https://movie-phinf.pstatic.net/20220426_89/1650936584302EbdIF_JPEG/movie_image.jpg?type=m665_443_2");
        url.add("https://movie-phinf.pstatic.net/20220426_87/1650936606581h5FqT_JPEG/movie_image.jpg?type=m665_443_2");
        url.add("https://movie-phinf.pstatic.net/20220426_216/1650936633838obfoa_JPEG/movie_image.jpg?type=m665_443_2");
        url.add("https://movie-phinf.pstatic.net/20220426_184/1650936656952WBd3u_JPEG/movie_image.jpg?type=m665_443_2");

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
            }
        });
    }

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
    private void loadDetail(){

    }
}
