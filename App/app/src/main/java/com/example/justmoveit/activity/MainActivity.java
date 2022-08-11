package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.LoadingActivity.movieSP;
import static com.example.justmoveit.activity.LoadingActivity.userSP;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.justmoveit.R;
import com.example.justmoveit.fragment.BlankFragment;

import java.security.MessageDigest;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

//    private ViewPagerFragment viewPagerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        setContentView(R.layout.activity_main);

        // 툴바 등록
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // 서버에서 받아왔는데 아무것도 없으면 빈 프래그먼트로 교체
        Map<String, ?> map = movieSP.getAll();
        if(map.size() == 0){
            BlankFragment blankFragment = new BlankFragment("상영 중인 영화가 없습니다.");
            getSupportFragmentManager().beginTransaction().replace(R.id.VP_container, blankFragment).commit();
        }

        Log.d("MainActivity", "view Pager start");
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
            String session = userSP.getString("user_info", "");
            if (session != null && !session.equals("")) {
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