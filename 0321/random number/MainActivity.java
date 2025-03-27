package com.example.randomnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewRandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewById는 setContentView 이후에 호출해야 함
        textViewRandomNumber = findViewById(R.id.textViewRandomNumber);
    }

    public void generateRandomNumber(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);  // 0부터 99까지 난수 생성
        textViewRandomNumber.setText("난수 : " + randomNumber);
    }
}
