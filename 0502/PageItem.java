package com.example.viewpager;

public class PageItem {
    int imageResId;  // 이미지 리소스 ID (예: R.drawable.image1)
    String title;    // 이미지 제목

    public PageItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}
