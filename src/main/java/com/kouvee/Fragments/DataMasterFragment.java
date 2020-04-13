package com.kouvee.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.kouvee.Activity.Produk.MenuProduk;
import com.kouvee.Activity.Ukuran.MenuUkuran;
import com.kouvee.R;
import com.kouvee.SQLite.DatabaseHandler;

public class DataMasterFragment extends Fragment {
    private ImageView btnProduk, btnLayanan, btnJenis, btnUkuran, btnSupplier, btnCustomer, btnHewan;
    private String jabatan;
    private DatabaseHandler db;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_datamaster, container, false);

        setAtribut();
        init();

        return view;
    }

    private void setAtribut() {
        btnProduk = (ImageView) view.findViewById(R.id.btnProduk);
        btnLayanan = (ImageView) view.findViewById(R.id.btnLayanan);
        btnJenis = (ImageView) view.findViewById(R.id.btnJenis);
        btnUkuran = (ImageView) view.findViewById(R.id.btnUkuran);
        btnSupplier = (ImageView) view.findViewById(R.id.btnSupplier);
        btnCustomer = (ImageView) view.findViewById(R.id.btnCustomer);
        btnHewan = (ImageView) view.findViewById(R.id.btnHewan);

        db = new DatabaseHandler(getContext());

        if(db.getUser(1).getJabatan().equals("Owner"))
        {
            btnHewan.setVisibility(View.INVISIBLE);
            btnCustomer.setVisibility(View.INVISIBLE);
        }
        else
        {
            btnProduk.setVisibility(View.INVISIBLE);
            btnLayanan.setVisibility(View.INVISIBLE);
            btnUkuran.setVisibility(View.INVISIBLE);
            btnJenis.setVisibility(View.INVISIBLE);
            btnSupplier.setVisibility(View.INVISIBLE);
        }
    }

    private void init() {
        btnProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), MenuProduk.class));
            }
        });

        btnLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnJenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnUkuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), MenuUkuran.class));
            }
        });

        btnSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
