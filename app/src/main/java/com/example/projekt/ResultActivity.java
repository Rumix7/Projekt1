package com.example.projekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

        TextView t1,t2,t3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        Button button_again = findViewById(R.id.button_again);
        Button button_quit = findViewById(R.id.button_quit);


        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quit();

            }
        });

        button_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAgain();
            }
        });

        t1 = (TextView) findViewById(R.id.textView4);
        t2 = (TextView) findViewById(R.id.textView5);
        t3 = (TextView) findViewById(R.id.textView6);

        Intent intent = getIntent();

        String questions = intent.getStringExtra("total");
        String correct = intent.getStringExtra("correct");
        String wrong = intent.getStringExtra("incorrect");


        t1.setText(questions);
        t2.setText(correct);
        t3.setText(wrong);


    }

    private void startAgain() {
        Intent intent = new Intent(ResultActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void Quit() {

        super.finish();
    }
}
