package com.example.todolistsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodoListActivity extends AppCompatActivity {

    EditText editText;
    Button btnSave, btnCancel;
    DBHelper dbHelper;
    int todoId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        editText = findViewById(R.id.editTextMemo);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        todoId = intent.getIntExtra("id", -1);
        String content = intent.getStringExtra("content");

        if (content != null) {
            editText.setText(content);
        }

        btnSave.setOnClickListener(v -> {
            String contentInput = editText.getText().toString().trim();
            if (contentInput.isEmpty()) {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }

            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("content", contentInput);
            values.put("date", date);

            if (todoId == -1) {
                db.insert("todolists", null, values);
            } else {
                db.update("todolists", values, "id=?", new String[]{String.valueOf(todoId)});
            }

            setResult(RESULT_OK);
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
