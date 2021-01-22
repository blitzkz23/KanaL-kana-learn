package com.example.kanal_kanalearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HiraganaActivity extends AppCompatActivity {

    RecyclerView dataList;
    String titles[];
    ImageButton btnKatakana2;

    int images[]={R.drawable.a, R.drawable.i, R.drawable.u, R.drawable.e, R.drawable.o,
            R.drawable.ka, R.drawable.ki, R.drawable.ku, R.drawable.ke, R.drawable.ko,
            R.drawable.sa, R.drawable.shi, R.drawable.su, R.drawable.se, R.drawable.so,
            R.drawable.ta, R.drawable.chi, R.drawable.tsu, R.drawable.te, R.drawable.to,
            R.drawable.na, R.drawable.ni, R.drawable.nu, R.drawable.ne, R.drawable.no,
            R.drawable.ha, R.drawable.hi, R.drawable.fu, R.drawable.he, R.drawable.ho,
            R.drawable.ma, R.drawable.mi, R.drawable.mu, R.drawable.me, R.drawable.mo,
            R.drawable.ya, R.drawable.yi, R.drawable.yo, R.drawable.wa, R.drawable.wo,
            R.drawable.ra, R.drawable.ri, R.drawable.ru, R.drawable.re, R.drawable.ro,
            R.drawable.n};
    KanaAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hiragana_page);

        btnKatakana2 = findViewById(R.id.btnLanjutKana);
        dataList = findViewById(R.id.recyclerHiragana);

        titles = getResources().getStringArray(R.array.kana);

        adapter = new KanaAdapter(this, titles, images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);

        btnKatakana2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HiraganaActivity.this, KatakanaActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
