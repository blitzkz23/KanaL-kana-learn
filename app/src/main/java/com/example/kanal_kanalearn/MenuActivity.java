package com.example.kanal_kanalearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class MenuActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView mTxtNama;
    Button mBtnHiragana,mBtnKatakana,mBtnQuiz,mBtnGuide,mBtnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        mTxtNama = findViewById(R.id.txtNama);
        mBtnOut = findViewById(R.id.btnOut);
        mBtnHiragana = findViewById(R.id.btnHiragana);
        mBtnKatakana = findViewById(R.id.btnKatakana);
        mBtnQuiz = findViewById(R.id.btnQuiz);
        mBtnGuide = findViewById(R.id.btnGuide);

        preferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE); //initialize shared prefs

        String name = preferences.getString("NAME", "Anon"); //string value for name on shared prefs
        mTxtNama.setText(name);

        mBtnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mBtnHiragana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HiraganaActivity.class);
                startActivity(intent);
            }
        });

        mBtnKatakana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, KatakanaActivity.class);
                startActivity(intent);
            }
        });

        mBtnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        mBtnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GuideActivity.class);
                startActivity(intent);
            }
        });

    }

}
