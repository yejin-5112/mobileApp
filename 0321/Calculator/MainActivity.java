package com.example.calculator; // 패키지는 프로젝트 설정에 맞게 수정

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText number1, number2;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML에서 요소들을 가져오기
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        result = findViewById(R.id.result);

        // 각 버튼에 대한 클릭 이벤트 설정
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
    }

    // 계산 로직
    private void calculate(char operator) {
        String num1Str = number1.getText().toString();
        String num2Str = number2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "숫자를 입력하세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double calcResult = 0;

        switch (operator) {
            case '+':
                calcResult = num1 + num2;
                break;
            case '-':
                calcResult = num1 - num2;
                break;
            case '*':
                calcResult = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    Toast.makeText(this, "0으로 나눌 수 없습니다!", Toast.LENGTH_SHORT).show();
                    return;
                }
                calcResult = num1 / num2;
                break;
        }

        result.setText("Result: " + calcResult);
    }
}
