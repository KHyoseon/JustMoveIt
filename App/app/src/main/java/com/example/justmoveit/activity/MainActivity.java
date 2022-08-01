package com.example.justmoveit.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.justmoveit.adapters.MoviesAdapter;
import com.example.justmoveit.R;
import com.example.justmoveit.model.ImgUrl;
import com.example.justmoveit.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        button =(Button) findViewById(R.id.moveDetail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                startActivity(intent);
            }
        });
        

        setupMoviesViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.profile){
            Toast.makeText(this,"Clicked on profile",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupMoviesViewPager(){

            ViewPager2 moviesViewPager = findViewById(R.id.moviesViewPager);
            moviesViewPager.setClipToPadding(false);
            moviesViewPager.setClipChildren(false);
            moviesViewPager.setOffscreenPageLimit(3);
            moviesViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(10));
            compositePageTransformer.addTransformer(((page, position) -> {
                float r = 1 -Math.abs(position);
                page.setScaleY(0.85f + r* 0.15f);
            }));
            moviesViewPager.setPageTransformer(compositePageTransformer);

            moviesViewPager.setAdapter(new MoviesAdapter(getMovies()));

        }
        private List<Movie> getMovies(){


            List<Movie> movies = new ArrayList<>();


            Movie mov1 = new Movie(1L, "한국", "최동훈", "류준열, 김우빈, 김태리", "외계+인 1부",
                    "액션, 판타지, SF", "2022년 현재, ‘가드’(김우빈)’와 ‘썬더’는 인간의 몸에 가두어진 외계인 죄수를 관리하며 지구에 살고 있다.\n" +
                    " 어느 날, 서울 상공에 우주선이 나타나고\n" +
                    " 형사 ‘문도석’(소지섭)은 기이한 광경을 목격하게 되는데..\n" +
                    " \n" +
                    " 한편, 630년 전 고려에선 얼치기 도사 ‘무륵’(류준열)과 천둥 쏘는 처자 ‘이안’(김태리)이\n" +
                    " 엄청난 현상금이 걸린 신검을 차지하기 위해 서로를 속고 속이는 가운데\n" +
                    " 신검의 비밀을 찾는 두 신선 ‘흑설’(염정아)과 ‘청운’(조우진),\n" +
                    " 가면 속의 ‘자장’(김의성)도 신검 쟁탈전에 나선다.\n" +
                    " 그리고 우주선이 깊은 계곡에서 빛을 내며 떠오르는데…\n" +
                    " \n" +
                    " 2022년 인간 속에 수감된 외계인 죄수를 쫓는 이들\n" +
                    " 1391년 고려 말 소문 속의 신검을 차지하려는 도사들\n" +
                    " \n" +
                    " 시간의 문이 열리고\n" +
                    " 모든 것이 바뀌기 시작했다!", "142분", "https://movie-phinf.pstatic.net/20220708_75/16572722362230AyHS_JPEG/movie_image.jpg", "https://movie-phinf.pstatic.net/20220616_274/16553440662302SpBU_JPEG/movie_image.jpg?type=m665_443_2",
                    "https://movie-phinf.pstatic.net/20220617_113/1655427761368j8QHv_JPEG/movie_image.jpg?type=m665_443_2", "https://movie-phinf.pstatic.net/20220620_61/1655692158584QeRHN_JPEG/movie_image.jpg?type=m665_443_2", "\thttps://movie-phinf.pstatic.net/20220622_262/1655880463805ndpvh_JPEG/movie_image.jpg?type=m665_443_2", "https://movie-phinf.pstatic.net/20220622_124/1655880480965Aqu7D_JPEG/movie_image.jpg?type=m665_443_2",
                    "7.04", "Alienoid, 2022",
                    "12세 관람가", "2022.07.20", "281243", "192151");
            movies.add(mov1);

            Movie mov2 = new Movie(2L, "미국", "조셉 코신스키", "톰 크루즈, 마일즈 텔러, 제니퍼 코넬리", "탑건: 매버릭", "액션", "최고의 파일럿이자 전설적인 인물 매버릭(톰 크루즈)은 자신이 졸업한 훈련학교 교관으로 발탁된다.\n" +
                    " 그의 명성을 모르던 팀원들은 매버릭의 지시를 무시하지만 실전을 방불케 하는 상공 훈련에서 눈으로 봐도 믿기 힘든 전설적인 조종 실력에 모두가 압도된다.\n" +
                    " \n" +
                    " 매버릭의 지휘아래 견고한 팀워크를 쌓아가던 팀원들에게 국경을 뛰어넘는 위험한 임무가 주어지자\n" +
                    " 매버릭은 자신이 가르친 동료들과 함께 마지막이 될 지 모를 하늘 위 비행에 나서는데…", "130분","https://movie-phinf.pstatic.net/20220509_176/1652081912471yhg3N_JPEG/movie_image.jpg", "https://movie-phinf.pstatic.net/20191219_253/1576735700330webEM_JPEG/movie_image.jpg?type=m665_443_2", "\thttps://movie-phinf.pstatic.net/20191219_176/1576735700763y1rP2_JPEG/movie_image.jpg?type=m665_443_2", "\thttps://movie-phinf.pstatic.net/20220331_153/1648689964919opv47_JPEG/movie_image.jpg?type=m665_443_2", "\thttps://movie-phinf.pstatic.net/20220331_102/1648689986537YrqQP_JPEG/movie_image.jpg?type=m665_443_2", "https://movie-phinf.pstatic.net/20220518_121/1652840771393vLWSH_JPEG/movie_image.jpg?type=m665_443_2",
                    "9.6", "Top Gun: Maverick, 2021", "12세 관람가", "2022.06.22", "6053414", "81888");

            movies.add(mov2);

            Movie mov3 = new Movie(3L, "한국","이상용", "마동석, 손석구, 최귀화","범죄도시2", "범죄, 액션", "가리봉동 소탕작전 후 4년 뒤,\n" +
                    " 금천서 강력반은 베트남으로 도주한 용의자를 인도받아 오라는 미션을 받는다.\n" +
                    " \n" +
                    " 괴물형사 ‘마석도’(마동석)와 ‘전일만’(최귀화) 반장은 현지 용의자에게서 수상함을 느끼고,\n" +
                    " 그의 뒤에 무자비한 악행을 벌이는 ‘강해상’(손석구)이 있음을 알게 된다.\n" +
                    " \n" +
                    " ‘마석도’와 금천서 강력반은 한국과 베트남을 오가며\n" +
                    " 역대급 범죄를 저지르는 ‘강해상’을 본격적으로 쫓기 시작하는데...\n" +
                    " \n" +
                    " 나쁜 놈들 잡는 데 국경 없다!\n" +
                    " 통쾌하고 화끈한 범죄 소탕 작전이 다시 펼쳐진다!", "106분", "https://movie-phinf.pstatic.net/20220516_144/1652665409592Chvey_JPEG/movie_image.jpg", "https://movie-phinf.pstatic.net/20220426_19/16509365610560EIGm_JPEG/movie_image.jpg?type=m665_443_2", "https://movie-phinf.pstatic.net/20220426_89/1650936584302EbdIF_JPEG/movie_image.jpg?type=m665_443_2", "https://movie-phinf.pstatic.net/20220426_87/1650936606581h5FqT_JPEG/movie_image.jpg?type=m665_443_2","https://movie-phinf.pstatic.net/20220426_216/1650936633838obfoa_JPEG/movie_image.jpg?type=m665_443_2", "https://movie-phinf.pstatic.net/20220426_184/1650936656952WBd3u_JPEG/movie_image.jpg?type=m665_443_2",
                    "9.03", "the roundup, 2022", "15세 관람가", "2022.05.18", "12675394", "192608");

            movies.add(mov3);

            return movies;
        }

}