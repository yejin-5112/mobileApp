package com.example.dice; // 패키지는 프로젝트 설정에 맞게 수정

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageDice; // 주사위 이미지 뷰
    private Button buttonRoll; // 주사위 굴리기 버튼
    private Random random = new Random(); // 랜덤 숫자 생성기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML에서 요소 가져오기
        imageDice = findViewById(R.id.imageDice);
        buttonRoll = findViewById(R.id.buttonRoll);

        // 버튼 클릭 이벤트 설정
        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    // 주사위를 굴리는 메서드
    private void rollDice() {
        int diceNumber = random.nextInt(6) + 1; // 1~6 랜덤 숫자 생성
        int diceImageId = getResources().getIdentifier("dice" + diceNumber, "drawable", getPackageName());
        imageDice.setImageResource(diceImageId); // 이미지 변경
    }
}
