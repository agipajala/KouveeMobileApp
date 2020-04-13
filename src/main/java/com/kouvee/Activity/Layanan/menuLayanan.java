package com.kouvee.Activity.Layanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kouvee.Activity.HomeActivity;
import com.kouvee.Activity.Layanan.createLayanan;
import com.kouvee.R;
import com.kouvee.Views.Layanan.showLayanan;
import com.kouvee.Views.Produk.showProduk;

public class menuLayanan extends AppCompatActivity {
    private ImageView btnTambahLayanan, btnTampilLayanan, btnTampilLogLayanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_layanan);

        setAtribut();
        init();
    }

    private void setAtribut() {
        btnTambahLayanan = (ImageView) findViewById(R.id.btnTambahLayanan);
        btnTampilLayanan = (ImageView) findViewById(R.id.btnTampilLayanan);
        btnTampilLogLayanan = (ImageView) findViewById(R.id.btnTampilLogLayanan);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView judul =(TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        judul.setText("Menu Layanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        btnTambahLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), createLayanan.class));
            }
        });

        btnTampilLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showLayanan.class);
                i.putExtra("status","getAll" );
                startActivity(i);
            }
        });

        btnTampilLogLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showLayanan.class);
                i.putExtra("status","softDelete" );
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("status", "dataMaster");
        startActivity(i);

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("status", "dataMaster" );
        startActivity(i);
    }
}
