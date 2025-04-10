package com.example.textview;

import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);

        tv1.setText("자바 코드로 변경하였습니다.");
        tv2.setTextColor(Color.BLUE);
        tv2.setTextSize(60);
        tv3.setTextSize(60);
        tv3.setTypeface(Typeface.SERIF, Typeface.ITALIC);

    }
}