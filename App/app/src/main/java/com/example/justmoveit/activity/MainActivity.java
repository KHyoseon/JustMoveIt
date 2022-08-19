package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.LoadingActivity.movieSP;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.justmoveit.R;
import com.example.justmoveit.fragment.ViewPagerFragment;
import com.kakao.auth.Session;

import java.security.MessageDigest;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private TextView generalRanking;
    private TextView myRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // 툴바 등록
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        generalRanking = findViewById(R.id.general_ranking);
        myRanking = findViewById(R.id.my_ranking);

        myRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOffMovieRankTab(myRanking, generalRanking);
                ViewPagerFragment myRankingView = new ViewPagerFragment(1);
                getSupportFragmentManager().beginTransaction().replace(R.id.VP_container, myRankingView).commit();
            }
        });

        generalRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOffMovieRankTab(generalRanking, myRanking);
                ViewPagerFragment genRankingView = new ViewPagerFragment(0);
                getSupportFragmentManager().beginTransaction().replace(R.id.VP_container, genRankingView).commit();
            }
        });
        Log.d("MainActivity", "view Pager start");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Map<String, ?> map = movieSP.getAll();

        // 로그인되어 있으면 마이 랭킹 켜고 일반 랭킹 끄기
        if(Session.getCurrentSession().isOpened() && movieSP.getString("my_ranking","") != null && !movieSP.getString("my_ranking","").equals("")){
            myRanking.setVisibility(View.VISIBLE);
            myRanking.performClick();
        } else if (map != null && map.size() > 1) {
            myRanking.setVisibility(View.INVISIBLE);
            generalRanking.performClick();
        }
    }

    private void onOffMovieRankTab(TextView on, TextView off) {
        // on
        on.setTypeface(null, Typeface.BOLD);
        on.setTextColor(Color.parseColor("#ffffff"));
        // off
        if(off == null) return;
        off.setTypeface(null, Typeface.NORMAL);
        off.setTextColor(Color.parseColor("#757575"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 옵션 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it = new Intent();
        // 프로필 클릭시 로그인 페이지 or 티켓 예매 내역 페이지
        if (item.getItemId() == R.id.profile) {
            if (Session.getCurrentSession().isOpened()) {
//                Toast.makeText(this, "CurrentSession is opend ", Toast.LENGTH_SHORT).show();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.MyTicketListActivity");
            } else {
//                Toast.makeText(this, "CurrentSession is closed", Toast.LENGTH_SHORT).show();
                it.setClassName("com.example.justmoveit", "com.example.justmoveit.activity.LoginActivity");
            }
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }


}