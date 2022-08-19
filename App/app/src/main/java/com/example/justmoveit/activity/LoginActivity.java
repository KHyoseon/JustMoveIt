package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.LoadingActivity.movieSP;
import static com.example.justmoveit.activity.LoadingActivity.userSP;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.justmoveit.R;
import com.example.justmoveit.api.MovieApi;
import com.example.justmoveit.model.Movie;
import com.example.justmoveit.model.Recommend;
import com.example.justmoveit.model.User;
import com.google.gson.Gson;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.AgeRange;
import com.kakao.usermgmt.response.model.Gender;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.exception.KakaoException;

import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private static final int PERMISSIONS_REQUEST_CODE = 22;

    private final ISessionCallback mSessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {

            // 로그인 요청
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    // 로그인 실패
                    Toast.makeText(LoginActivity.this, "로그인 도중에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    Log.i("session onFailure()", errorResult.getErrorMessage());
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    // 세션이 닫힘..
                    Toast.makeText(LoginActivity.this, "세션이 닫혔습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                    Log.i("session onSessionClosed()", errorResult.getErrorMessage());
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    // 로그인 성공
                    UserAccount account = result.getKakaoAccount();

                    AgeRange age = account.getAgeRange();
                    Gender gender = account.getGender();

                    if(age!=null && gender!=null) {
                        Recommend recommend = new Recommend(age.getValue().split("~")[0], gender.getValue());
                        ConnectionThread thread = new ConnectionThread(recommend);
                        thread.start();
                        synchronized (thread){
                            try {
                                thread.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Log.e("LoginActivity", "no age, gender info");
                    }

                    User user = new User(account.getProfile().getProfileImageUrl(),
                            account.getProfile().getNickname(), account.getEmail(),
                            gender==null? "null": gender.getValue(),
                            age==null? "null": age.getValue());

                    Gson gson = new Gson();
                    editor = userSP.edit();
                    editor.putString("user_info", gson.toJson(user));
                    editor.apply();

                    if (chkPermission()) {
                        // 휴대폰 정보는 TelephonyManager 를 이용
                        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

                        // READ_PHONE_NUMBERS 또는 READ_PHONE_STATE 권한을 허가 받았는지 확인
                        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        editor.putString("phone_number", (tm.getLine1Number().equals("")? "01012345678": tm.getLine1Number()));
                        editor.apply();
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(LoginActivity.this::finish, 100);
                }

            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(LoginActivity.this, "onSessionOpenFailed" + exception.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        editor = userSP.edit();
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();// 자동 로그인

    }

    public boolean chkPermission() {
        // 위험 권한을 모두 승인했는지 여부
        boolean mPermissionsGranted = false;
        String[] mRequiredPermissions = new String[1];
        // 승인 받기 위한 권한 목록
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            mRequiredPermissions[0] = Manifest.permission.READ_PHONE_NUMBERS;
        }else{
            mRequiredPermissions[0] = Manifest.permission.READ_PRECISE_PHONE_STATE;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 필수 권한을 가지고 있는지 확인한다.
            mPermissionsGranted = hasPermissions(mRequiredPermissions);

            // 필수 권한 중에 한 개라도 없는 경우
            if (!mPermissionsGranted) {
                // 권한을 요청한다.
                ActivityCompat.requestPermissions(LoginActivity.this, mRequiredPermissions, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            mPermissionsGranted = true;
        }

        return mPermissionsGranted;
    }


    public boolean hasPermissions(String[] permissions) {
        // 필수 권한을 가지고 있는지 확인한다.
        for (String permission : permissions) {
            if (checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            // 권한을 모두 승인했는지 여부
            boolean chkFlag = false;
            // 승인한 권한은 0 값, 승인 안한 권한은 -1을 값으로 가진다.
            for (int g : grantResults) {
                if (g == -1) {
                    chkFlag = true;
                    break;
                }
            }

            // 권한 중 한 개라도 승인 안 한 경우
            if (chkFlag) {
                chkPermission();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // onActivityResult: 서브에서 메인으로 돌아올 때 값을 주고 싶을 경우 사용
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        // 액티비티가 죽었을 때 객체를 해제
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }

    // 토큰 관리
    protected void requestAccessToken() {
        AuthService.getInstance()
                .requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "토큰 정보 요청 실패: " + errorResult);
                    }

                    @Override
                    public void onSuccess(AccessTokenInfoResponse result) {
                        Log.i("KAKAO_API", "사용자 아이디: " + result.getUserId());
                        Log.i("KAKAO_API", "남은 시간(s): " + result.getExpiresInMillis());
                    }
                });
    }

    private static class ConnectionThread extends Thread {
        private final Recommend recommend;

        public ConnectionThread(Recommend recommend){
            this.recommend = recommend;
        }

        @Override
        public void run() {
            super.run();
            synchronized (this){
                getMyRanking();
                notify();
            }
        }

        private void getMyRanking(){
            MovieApi service = MovieApi.retrofit.create(MovieApi.class);
            service.getMovieOrderedList(recommend).enqueue(new Callback<Movie[]>() {
                @Override
                public void onResponse(Call<Movie[]> call, Response<Movie[]> response) {
                    Movie[] movies = response.body();
                    if (!response.isSuccessful()) {
                        Log.e("LoginActivity - getMovieOrderedList", "onResponse(): " + response.code());
                        return;
                    }
                    if (movies == null) {
                        Log.e("LoginActivity - getMovieOrderedList", "onResponse(): there are no movies");
                        return;
                    }
                    SharedPreferences.Editor editor2 = movieSP.edit();
                    Gson gson = new Gson();
                    editor2.putString("my_ranking", gson.toJson(Arrays.asList(movies)));
                    editor2.apply();
                }

                @Override
                public void onFailure(Call<Movie[]> call, Throwable t) {
                    Log.e("LoginActivity - getMovieOrderedList", "onFailure(): " + t.getMessage());
                }
            });
        }
    }
}