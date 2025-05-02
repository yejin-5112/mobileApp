package com.example.viewpager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    List<PageItem> pageItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);

        // 데이터 준비
        pageItems = new ArrayList<>();
        pageItems.add(new PageItem(R.drawable.image1, "첫 번째 이미지"));
        pageItems.add(new PageItem(R.drawable.image2, "두 번째 이미지"));
        pageItems.add(new PageItem(R.drawable.image3, "세 번째 이미지"));

        ViewPagerAdapter adapter = new ViewPagerAdapter(pageItems);
        viewPager.setAdapter(adapter);
    }
}
