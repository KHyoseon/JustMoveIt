package com.example.justmoveit.retrofit;/*
package com.example.justmoveit.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;

public class TempActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitManager.getInstance().searchPhotos("cat", ((responseState, responseBody) -> {
                    if(responseState.equals("success")){
                        Log.d("TAG", "api 호출 성공: " + responseBody);
                    } else {
                        Toast.makeText(TempActivity.this, "api 호출 에러: "+responseBody, Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });
    }
}
*/
