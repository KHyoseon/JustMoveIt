package com.example.justmoveit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justmoveit.R;
import com.example.justmoveit.model.Movie;

import java.util.HashSet;
import java.util.Set;

public class TicketingActivity extends AppCompatActivity {
    private Movie movie;
    private TextView textMovieName, textMovieTime, textAdult, textChild, textDisabled, textTotalCost, runningTime;

    private Spinner adultSpinner;
    private Spinner childSpinner;
    private Spinner disabledSpinner;

    private Integer[] peopleNum = {0, 1, 2, 3, 4, 5};
    private String cusRes = "";//결제 총 목록
    private String seatRes = "";//좌석 총 목록

    private int[] cntSeat; // 0:성인, 1:어린이, 2:장애인
    private static Set<String> reservedSeat;

    private void loadMovie() {
        Intent intent = getIntent();
        movie = (Movie) intent.getSerializableExtra("movie");
    }

    private void setInfo() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);

        cntSeat = new int[3];
        reservedSeat = new HashSet<>();

        // 예매할 영화 정보 가져옴
        loadMovie();
        // 영화 정보 뿌림
        textMovieName = findViewById(R.id.textMovieName);
        textMovieTime = findViewById(R.id.textMovieTime);
        textAdult = findViewById(R.id.textAdult);
        textChild = findViewById(R.id.textChild);
        textDisabled = findViewById(R.id.textDisabled);
        textTotalCost = findViewById(R.id.textTotalCost);
        runningTime = findViewById(R.id.runningTime);

        // 인원 설정 스피너
        adultSpinner = findViewById(R.id.adultSpinner);
        childSpinner = findViewById(R.id.childSpinner);
        disabledSpinner = findViewById(R.id.disabledSpinner);

        ArrayAdapter<Integer> adultSpinnerAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_dropdown_item, peopleNum
        );
        ArrayAdapter<Integer> childSpinnerAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_dropdown_item, peopleNum
        );
        ArrayAdapter<Integer> disabledSpinnerAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_dropdown_item, peopleNum
        );

        // 스피너 - 레이아웃 적용
        adultSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        childSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        disabledSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // 스피너 - 어댑터 적용
        adultSpinner.setAdapter(adultSpinnerAdapter);
        childSpinner.setAdapter(childSpinnerAdapter);
        disabledSpinner.setAdapter(disabledSpinnerAdapter);

        // 스피너 - 클릭 이벤트 적용
        setOnAllSpinnerSelectedListener();

        setAllSeatListener();

        Button paybtn = findViewById(R.id.payment);
        paybtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                PaymentActivity paymentActivity = new PaymentActivity(movie.getTitle(), 18000);
                Intent it = new Intent(getApplicationContext(), paymentActivity.getClass());
                startActivity(it);
            }
        });
    }

    public void setOnAllSpinnerSelectedListener(){
        adultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textAdult.setText("성인 " + peopleNum[i] + "명");
                cntSeat[0] = peopleNum[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textAdult.setText("");
            }
        });

        childSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textChild.setText("어린이 " + peopleNum[i] + "명");
                cntSeat[1] = peopleNum[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textChild.setText("");
            }
        });

        disabledSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textDisabled.setText("장애인 " + peopleNum[i] + "명");
                cntSeat[2] = peopleNum[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textDisabled.setText("");
            }
        });
    }

    public void setAllSeatListener(){
        ((ToggleButton) findViewById(R.id.A01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.A05)).setOnCheckedChangeListener(new seatSelectListener());

        ((ToggleButton) findViewById(R.id.B01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.B05)).setOnCheckedChangeListener(new seatSelectListener());

        ((ToggleButton) findViewById(R.id.C01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.C05)).setOnCheckedChangeListener(new seatSelectListener());

        ((ToggleButton) findViewById(R.id.D01)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D02)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D03)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D04)).setOnCheckedChangeListener(new seatSelectListener());
        ((ToggleButton) findViewById(R.id.D05)).setOnCheckedChangeListener(new seatSelectListener());

    }

    static class seatSelectListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked){
                Log.i("checked", compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
                reservedSeat.add(compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
            } else{
                Log.i("unChecked", compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
                reservedSeat.remove(compoundButton.getResources().getResourceEntryName(compoundButton.getId()));
            }
        }
    }
}