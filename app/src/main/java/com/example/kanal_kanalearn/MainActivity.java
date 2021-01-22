package com.example.kanal_kanalearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mInputNama;
    Button mBtnNama;
    CheckBox checkBox;
    SharedPreferences sharedPref;

    boolean isRemembered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputNama = findViewById(R.id.inputNama);
        mBtnNama = findViewById(R.id.btnNama);
        checkBox = findViewById(R.id.checkBox);
        sharedPref = getSharedPreferences("SHARED_PREF", MODE_PRIVATE); //initialize shared prefs
        isRemembered = sharedPref.getBoolean("CHECKBOX", false); //save checkbox boolean on shared pref

        if(isRemembered){ //if there is already saved prefs load the menu activity immediately
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }

        mBtnNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mInputNama.getText().toString();
                Boolean checked = checkBox.isChecked();

                //still share prefs stuff prolly hhh
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("NAME",name);
                editor.putBoolean("CHECKBOX",checked);
                editor.apply();

                Toast.makeText(MainActivity.this, "Information Saved !", Toast.LENGTH_SHORT).show();

                //move to next intent after saving name and remember me
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}