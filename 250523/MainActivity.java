package com.example.calendarapp;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
            Toast.makeText(MainActivity.this, "선택한 날짜: " + date, Toast.LENGTH_SHORT).show();
        });
    }
}
