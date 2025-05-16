package com.example.todolistsql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    TodoListAdapter adapter;
    DBHelper dbHelper;
    SQLiteDatabase database;
    List<TodoListItem> todoList = new ArrayList<>();

    private static final int REQUEST_CODE_TODOLIST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        loadTodoListFromDB();

        adapter = new TodoListAdapter(todoList, new TodoListAdapter.OnItemClickListener() {
            @Override
            public void onEdit(int position) {
                Intent intent = new Intent(MainActivity.this, TodoListActivity.class);
                intent.putExtra("id", todoList.get(position).id);
                intent.putExtra("content", todoList.get(position).content);
                intent.putExtra("date", todoList.get(position).date);
                startActivityForResult(intent, REQUEST_CODE_TODOLIST);
            }

            @Override
            public void onDelete(int position) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("todolists", "id=?", new String[]{String.valueOf(todoList.get(position).id)});
                loadTodoListFromDB();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "삭제됨", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TodoListActivity.class);
            startActivityForResult(intent, REQUEST_CODE_TODOLIST);
        });
    }

    private void loadTodoListFromDB() {
        todoList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM todolists ORDER BY id DESC", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            todoList.add(new TodoListItem(id, content, date));
        }
        cursor.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TODOLIST && resultCode == RESULT_OK) {
            loadTodoListFromDB();
            adapter.notifyDataSetChanged();
        }
    }
}
