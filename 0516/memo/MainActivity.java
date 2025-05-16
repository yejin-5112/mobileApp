package com.example.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    MemoAdapter adapter;
    Map<String, String> memoMap = new TreeMap<>();  // 정렬된 상태로 보여줌
    SharedPreferences sharedPreferences;

    private static final int REQUEST_CODE_MEMO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("memo_prefs", MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        loadMemos();

        adapter = new MemoAdapter(new ArrayList<>(memoMap.keySet()), memoMap,
                new MemoAdapter.OnItemClickListener() {
                    @Override
                    public void onEdit(String key) {
                        Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                        intent.putExtra("key", key);
                        intent.putExtra("content", memoMap.get(key));
                        startActivityForResult(intent, REQUEST_CODE_MEMO);
                    }

                    @Override
                    public void onDelete(String key) {
                        sharedPreferences.edit().remove(key).apply();
                        loadMemos();
                    }
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MemoActivity.class);
            startActivityForResult(intent, REQUEST_CODE_MEMO);
        });
    }

    private void loadMemos() {
        memoMap.clear();
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            memoMap.put(entry.getKey(), (String) entry.getValue());
        }
        if (adapter != null) {
            adapter.updateList(new ArrayList<>(memoMap.keySet()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_MEMO && resultCode == RESULT_OK && data != null) {
            String key = data.getStringExtra("key");
            String content = data.getStringExtra("content");
            if (key != null && content != null) {
                sharedPreferences.edit().putString(key, content).apply();
                loadMemos();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
