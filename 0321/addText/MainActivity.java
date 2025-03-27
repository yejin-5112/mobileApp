package com.example.addtext;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText ui, pwd, pn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui = (EditText) findViewById(R.id.userId);
        pwd = (EditText) findViewById(R.id.password);
        pn = (EditText) findViewById(R.id.Pnumber);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onClicked(View v) {
        String uii = ui.getText().toString();
        String pwdd = pwd.getText().toString();
        String pnn = pn.getText().toString();
        String output = "아이디: " + uii + "\n" +
                "패스워드: " + pwdd + "\n" +
                "전화번호: " + pnn;
        textView.setText(output);
    }
}