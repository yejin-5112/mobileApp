package com.example.imageproperties;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnScale, btnRotate, btnAlpha;

    float currentScale = 1.0f;
    float rotationAngle = 0f;
    float[] alphaValues = {1.0f, 0.7f, 0.4f, 0.1f};
    int alphaIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnScale = findViewById(R.id.btnScaleType);
        btnRotate = findViewById(R.id.btnRotate);
        btnAlpha = findViewById(R.id.btnAlpha);

        // Scale: 1.2배씩 증가
        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentScale *= 1.2f;
                imageView.setScaleX(currentScale);
                imageView.setScaleY(currentScale);
            }
        });

        // Rotate: 20도씩 회전
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotationAngle += 20f;
                imageView.setRotation(rotationAngle);
            }
        });

        // Alpha: 순차적으로 변경
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alphaIndex = (alphaIndex + 1) % alphaValues.length;
                imageView.setAlpha(alphaValues[alphaIndex]);
            }
        });
    }
}
