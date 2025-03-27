package com.example.rowhigh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private EditText userGuess;
    private TextView hintText;
    private Button guessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 요소들 연결
        userGuess = findViewById(R.id.userGuess);
        hintText = findViewById(R.id.hintText);
        guessButton = findViewById(R.id.guessButton);

        // 난수 생성 (1에서 100 사이)
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        // 버튼 클릭 시 예측한 숫자 확인
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int guess = Integer.parseInt(userGuess.getText().toString());

                    if (guess < 1 || guess > 100) {
                        Toast.makeText(MainActivity.this, "1과 100 사이의 숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (guess < randomNumber) {
                        hintText.setText("더 큰 숫자입니다.");
                    } else if (guess > randomNumber) {
                        hintText.setText("더 작은 숫자입니다.");
                    } else {
                        hintText.setText("정답입니다! 축하합니다.");
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
