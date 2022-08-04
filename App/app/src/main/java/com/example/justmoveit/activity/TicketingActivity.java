package com.example.justmoveit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.model.Movie;

public class TicketingActivity extends AppCompatActivity {

    Movie movie;
    TextView textMovieName,textMovieTime,textAdult,textChild,textDisabled,textTotalCost,
            runningTime,textNowDate;

    Integer[] peopleNum = {0,1,2,3,4,5};
    String cusRes="";//결제 총 목록
    String seatRes="";//좌석 총 목록


    private void loadMovie(){
        Intent intent =getIntent();

        movie=(Movie)intent.getSerializableExtra("movie");

    }
    private void setInfo(){

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMovie();

        setContentView(R.layout.activity_ticketing);

        Spinner adultSpinner = (Spinner)findViewById(R.id.adultSpinner);
        Spinner childSpinner = (Spinner)findViewById(R.id.childSpinner);
        Spinner disabledSpinner = (Spinner)findViewById(R.id.disabledSpinner);


        textMovieName= findViewById(R.id.textMovieName);
        textMovieTime= findViewById(R.id.textMovieTime);
        textAdult= findViewById(R.id.textAdult);
        textChild= findViewById(R.id.textChild);
        textDisabled= findViewById(R.id.textDisabled);
        textTotalCost= findViewById(R.id.textTotalCost);
        runningTime= findViewById(R.id.runningTime);
        textNowDate= findViewById(R.id.textNowDate);


        ArrayAdapter<Integer> adultSpinnerAdapter = new ArrayAdapter<Integer>(
                this, R.layout.spinner_dropdown_item,peopleNum
        );
        ArrayAdapter<Integer> childSpinnerAdapter = new ArrayAdapter<Integer>(
                this, R.layout.spinner_dropdown_item,peopleNum
        );
        ArrayAdapter<Integer> disabledSpinnerAdapter = new ArrayAdapter<Integer>(
                this, R.layout.spinner_dropdown_item,peopleNum
        );

        adultSpinnerAdapter.setDropDownViewResource(
                R.layout.spinner_dropdown_item
        );
        childSpinnerAdapter.setDropDownViewResource(
                R.layout.spinner_dropdown_item
        );
        disabledSpinnerAdapter.setDropDownViewResource(
                R.layout.spinner_dropdown_item
        );

        adultSpinner.setAdapter(adultSpinnerAdapter);
        childSpinner.setAdapter(childSpinnerAdapter);
        disabledSpinner.setAdapter(disabledSpinnerAdapter);

        adultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textAdult.setText("성인 "+peopleNum[i]+"명");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textAdult.setText("");
            }
        });
        childSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textChild.setText("어린이 "+peopleNum[i]+"명");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textChild.setText("");
            }
        });
        disabledSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textDisabled.setText("장애인 "+peopleNum[i]+"명");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textDisabled.setText("");
            }
        });
    }
}
