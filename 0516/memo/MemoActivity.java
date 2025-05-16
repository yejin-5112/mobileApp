package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {

    EditText editText;
    Button btnSave, btnCancel;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        editText = findViewById(R.id.editTextMemo);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        String content = intent.getStringExtra("content");

        if (content != null) {
            editText.setText(content);
        }

        btnSave.setOnClickListener(v -> {
            String contentInput = editText.getText().toString().trim();
            if (contentInput.isEmpty()) {
                Toast.makeText(this, "메모 내용을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }
            if (key == null) {
                key = "메모 " + System.currentTimeMillis();  // 간단한 키 생성
            }
            Intent resultIntent = new Intent();
            resultIntent.putExtra("key", key);
            resultIntent.putExtra("content", contentInput);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
