package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.MainActivity.userSP;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.exception.KakaoException;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
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
                    Intent intent = new Intent(LoginActivity.this, MyTicketsListActivity.class);
                    UserAccount account = result.getKakaoAccount();

                    SharedPreferences.Editor editor = userSP.edit();

                    editor.putString("user_name", account.getProfile().getNickname());
                    editor.putString("user_img_url", account.getProfile().getProfileImageUrl());
                    editor.putString("user_email", account.getEmail());
                    editor.putString("user_age_range", account.getAgeRange().getValue());
                    editor.putString("user_gender", Objects.requireNonNull(account.getGender()).getValue());
                    Log.e("user_name", userSP.getString("user_name", ""));
                    editor.apply();

                    startActivity(intent);
                    finish();
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

        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();// 자동 로그인
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
}