package com.example.counter; // 패키지는 프로젝트 설정에 맞게 수정

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textCounter; // 카운터 값을 표시할 텍스트뷰
    private Button btnIncrease, btnDecrease; // 증가 및 감소 버튼
    private int counter = 0; // 카운터 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML에서 요소 가져오기
        textCounter = findViewById(R.id.textCounter);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);

        // 증가 버튼 클릭 시 카운터 증가
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                updateCounter();
            }
        });

        // 감소 버튼 클릭 시 카운터 감소
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                updateCounter();
            }
        });
    }

    // 카운터 값을 업데이트하는 메서드
    private void updateCounter() {
        textCounter.setText("카운터: " + counter);
    }
}
