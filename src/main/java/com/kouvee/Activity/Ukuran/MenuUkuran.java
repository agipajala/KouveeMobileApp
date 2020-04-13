package com.kouvee.Activity.Ukuran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kouvee.Activity.HomeActivity;
import com.kouvee.R;
import com.kouvee.Views.Ukuran.showUkuran;

public class MenuUkuran extends AppCompatActivity {

    private ImageView btnTambah, btnTampil, btnTampilLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ukuran);

        setAtribut();
        init();
    }

    private void setAtribut() {
        btnTambah = (ImageView) findViewById(R.id.btnTambahProduk);
        btnTampil = (ImageView) findViewById(R.id.btnTampilProduk);
        btnTampilLog = (ImageView) findViewById(R.id.btnTampilLogProduk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo2);
    }

    private void init() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), createUkuran.class));
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showUkuran.class);
                i.putExtra("status","getAll" );
                startActivity(i);
            }
        });

        btnTampilLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showUkuran.class);
                i.putExtra("status","softDelete" );
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {

        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("status","dataMaster" );
        startActivity(i);

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("status","dataMaster" );
        startActivity(i);
    }
}
