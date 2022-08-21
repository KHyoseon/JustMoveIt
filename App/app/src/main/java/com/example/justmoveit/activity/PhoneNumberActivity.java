package com.example.justmoveit.activity;

import static com.example.justmoveit.activity.LoadingActivity.userSP;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;

public class PhoneNumberActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        EditText phone = findViewById(R.id.edit_phone_num);
        Button button = findViewById(R.id.register_btn);

        button.setOnClickListener(v -> {
            SharedPreferences.Editor editor = userSP.edit();
            editor.putString("phone_number", phone.getText().toString());
            editor.apply();

            finish();
        });

    }
}
