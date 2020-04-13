package com.kouvee.Activity.Produk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kouvee.Activity.HomeActivity;
import com.kouvee.R;
import com.kouvee.Views.Produk.showProduk;

public class MenuProduk extends AppCompatActivity {

    private ImageView btnTambah, btnTampil, btnTampilLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_produk);

        setAtribut();
        init();
    }

    private void setAtribut() {
        btnTambah = (ImageView) findViewById(R.id.btnTambahProduk);
        btnTampil = (ImageView) findViewById(R.id.btnTampilProduk);
        btnTampilLog = (ImageView) findViewById(R.id.btnTampilLogProduk);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView judul =(TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        judul.setText("Menu Produk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), createProduk.class));
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showProduk.class);
                i.putExtra("status","getAll" );
                startActivity(i);
            }
        });

        btnTampilLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showProduk.class);
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
