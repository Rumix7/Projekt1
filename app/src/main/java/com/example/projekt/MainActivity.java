package com.example.projekt;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.Model.Question;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText newUser;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startQuiz();
            }
        });


        //Pobranie referencji do bazy danych
        mDatabase = FirebaseDatabase.getInstance().getReference();
        newUser = (EditText) findViewById(R.id.newUser);

    }

    public void startQuiz() {
        String message  = newUser.getText().toString();
        //odbiornik, który po pomyślnym zapisaniu danych w bazie danych wyczyści wartość wpisaną
        // w polu newUser oraz wyświetli komunikat typu Toast informujący o sukcesie operacji.
        // Skorzystaj z odbiornika klasy OnSuccessListener oraz metody addOnSuccessListener
        if(!message.equals("")) {
            mDatabase.child("Users").push().setValue(message).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
            Toast.makeText(getApplication().getBaseContext(),"Zaczynamy", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,QuizActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(getApplication().getBaseContext(),"Podaj nazwę użytkownika", Toast.LENGTH_SHORT).show();
        }

    }

}