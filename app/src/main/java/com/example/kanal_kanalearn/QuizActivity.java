package com.example.kanal_kanalearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1; //req code for quiz score

    public static final String SHARED_PREFS = "sharefPrefs"; //share prefs to save highscore
    public static final String KEY_HIGHSCORE = "keyHighscore"; //same but the actual highscore

    private TextView textViewHighScore;
    private int highscore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_starting_page);

        textViewHighScore = findViewById(R.id.txtHighScore);
        loadHighscore();

        Button mBtnStart = findViewById(R.id.btnStart);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz(){
        Intent intent = new Intent(QuizActivity.this, StartQuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){ //RESULT_OK is on the StartQuizActivity after finishing a quiz and we'll get it here to store the highscore
                int score = data.getIntExtra(StartQuizActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore() {
        //pretty much self explanatory by the func name
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("Highscore : " + highscore);
    }

    private void updateHighscore(int highscoreNew){
        //update score if bigger
        highscore = highscoreNew;
        textViewHighScore.setText("Highscore : " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
