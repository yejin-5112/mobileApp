package com.example.imageviewer;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageButton btnPrevious, btnNext;
    private int[] imageResIds = {
            R.drawable.img_1,  // 첫 번째 이미지
            R.drawable.img_2
    };
    private int currentIndex = 0;  // 현재 이미지 인덱스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ImageView와 ImageButton 연결
        imageView = findViewById(R.id.imageView);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // 첫 번째 이미지 표시
        imageView.setImageResource(imageResIds[currentIndex]);

        // 이전 버튼 클릭 시
        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;  // 인덱스 감소
                imageView.setImageResource(imageResIds[currentIndex]);  // 이미지 변경
            }
        });

        // 다음 버튼 클릭 시
        btnNext.setOnClickListener(v -> {
            if (currentIndex < imageResIds.length - 1) {
                currentIndex++;  // 인덱스 증가
                imageView.setImageResource(imageResIds[currentIndex]);  // 이미지 변경
            }
        });
    }
}
