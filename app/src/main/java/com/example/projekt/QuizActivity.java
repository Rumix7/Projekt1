
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
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    Button b1,b2,b3,b4;
    TextView t1_question,timerTxt;
    int total = -1;
    int correct = 0;
    int wrong = 0;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);

        t1_question = (TextView) findViewById(R.id.questionsTxt);
        timerTxt = (TextView) findViewById(R.id.timerTxt);


        updateQuestion();
        reverseTimer(30,timerTxt);


    }

    private void updateQuestion() {

        total++;
        if(total > 3)
        {


            //przejdz do aktywnosci wyników
            Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("total",String.valueOf(total));
            intent.putExtra("correct",String.valueOf(correct));
            intent.putExtra("incorrect",String.valueOf(wrong));
            startActivity(intent);
            finish();


        }
        else
        {
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //przypisanie odpowiedzi do przycisku
                    final Question question = dataSnapshot.getValue(Question.class);
                    t1_question.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());

                    //sprawdzenie poprawnosci odpowiedzi pod danym przyciskiem
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //jesli option1 = answer
                            if(b1.getText().toString().equals(question.getAnswer()))
                            {
                                //wyswietlenie po delayu poprawnej odpowiedzi
                                Toast.makeText(getApplicationContext(), "Poprawna odpowiedz", Toast.LENGTH_SHORT).show();
                                b1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                            else // jesli odpowiedz jest zla, znajdz poprawna odp i wyswietl na zielono
                            {
                                Toast.makeText(getApplicationContext(), "Zła odpowiedz", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b1.setBackgroundColor(Color.RED);

                                if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }


                                //update koloru tla przyciskow po wyswietleniu poprawnej i zlych odp
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //jesli option2 = answer
                            if(b2.getText().toString().equals(question.getAnswer()))
                            {
                                //wyswietlenie po delayu poprawnej odpowiedzi
                                Toast.makeText(getApplicationContext(), "Poprawna odpowiedz", Toast.LENGTH_SHORT).show();
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                            else // jesli odpowiedz jest zla, znajdz poprawna odp i wyswietl na zielono
                            {
                                Toast.makeText(getApplicationContext(), "Zła odpowiedz", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b2.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }


                                //update koloru tla przyciskow po wyswietleniu poprawnej i zlych odp
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                        }

                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //jesli option3 = answer
                            if(b3.getText().toString().equals(question.getAnswer()))
                            {
                                //wyswietlenie po delayu poprawnej odpowiedzi
                                Toast.makeText(getApplicationContext(), "Poprawna odpowiedz", Toast.LENGTH_SHORT).show();
                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                            else // jesli odpowiedz jest zla, znajdz poprawna odp i wyswietl na zielono
                            {
                                Toast.makeText(getApplicationContext(), "Zła odpowiedz", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b3.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }


                                //update koloru tla przyciskow po wyswietleniu poprawnej i zlych odp
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }

                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //jesli option4 = answer
                            if(b4.getText().toString().equals(question.getAnswer()))
                            {
                                //wyswietlenie po delayu poprawnej odpowiedzi
                                Toast.makeText(getApplicationContext(), "Poprawna odpowiedz", Toast.LENGTH_SHORT).show();
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                            else // jesli odpowiedz jest zla, znajdz poprawna odp i wyswietl na zielono
                            {
                                Toast.makeText(getApplicationContext(), "Zła odpowiedz", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b4.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }


                                //update koloru tla przyciskow po wyswietleniu poprawnej i zlych odp
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                },1500);
                            }

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }

    public void reverseTimer (int seconds, final TextView tv)
    {

        final CountDownTimer timer = new CountDownTimer(seconds * 1000 + 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if(total!=4) {
                    int seconds = (int) (millisUntilFinished/1000);
                    int minutes = seconds/60;
                    seconds = seconds%60;
                    tv.setText(String.format("%02d",minutes) +
                            ":" + String.format("%02d",seconds));}
                else {
                    cancel();
                    tv.setText("Zakończono");


                }


            }

            @Override
            public void onFinish() {

                tv.setText("Zakończono");
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("total", String.valueOf(total));
                intent.putExtra("correct", String.valueOf(correct));
                intent.putExtra("incorrect", String.valueOf(wrong));
                startActivity(intent);
                finish();


            }
        }.start();

    }
}

