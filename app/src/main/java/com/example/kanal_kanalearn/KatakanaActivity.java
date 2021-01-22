package com.example.kanal_kanalearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KatakanaActivity extends AppCompatActivity {

    RecyclerView dataList;
    String titles[];
    ImageButton btnHiragana2;
    int images[]={R.drawable.a2, R.drawable.i2, R.drawable.u2, R.drawable.e2, R.drawable.o2,
            R.drawable.ka2, R.drawable.ki2, R.drawable.ku2, R.drawable.ke2, R.drawable.ko2,
            R.drawable.sa2, R.drawable.si2, R.drawable.su2, R.drawable.se2, R.drawable.so2,
            R.drawable.ta2, R.drawable.ti2, R.drawable.tu2, R.drawable.te2, R.drawable.to2,
            R.drawable.na2, R.drawable.ni2, R.drawable.nu2, R.drawable.ne2, R.drawable.no2,
            R.drawable.ha2, R.drawable.hi2, R.drawable.hu2, R.drawable.he2, R.drawable.ho2,
            R.drawable.ma2, R.drawable.mi2, R.drawable.mu2, R.drawable.me2, R.drawable.mo2,
            R.drawable.ya2, R.drawable.yu2, R.drawable.yo2, R.drawable.wa2, R.drawable.wo2,
            R.drawable.ra2, R.drawable.ri2, R.drawable.ru2, R.drawable.re2, R.drawable.ro2,
            R.drawable.n};
    KanaAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.katakana_page);

        btnHiragana2 = findViewById(R.id.btnLanjutKana);
        dataList = findViewById(R.id.recyclerKatakana);

        titles = getResources().getStringArray(R.array.kana);

        adapter = new KanaAdapter(this, titles, images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);

        btnHiragana2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KatakanaActivity.this, HiraganaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
