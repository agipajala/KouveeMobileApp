package com.kouvee.Activity.Layanan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputLayout;
import com.kouvee.API.ApiClient;
import com.kouvee.API.Interface.ApiLayanan;
import com.kouvee.API.Response;
import com.kouvee.Activity.Produk.editProduk;
import com.kouvee.R;
import com.kouvee.SQLite.DatabaseHandler;
import com.kouvee.Views.Produk.showProduk;

import java.net.MalformedURLException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class editLayanan extends AppCompatActivity {
    private TextInputLayout txtJenisLayanan, txtHargaLayanan, txtIdUkuran;
    private Button btnSimpan;
    private DatabaseHandler db;
    private Bundle layanan;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produk);
        try {
            setAtribut();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        init();
    }
    private void setAtribut() throws MalformedURLException {
        txtJenisLayanan = (TextInputLayout) findViewById(R.id.txtJenisLayanan);
        txtHargaLayanan = (TextInputLayout) findViewById(R.id.txtHargaLayanan);
        txtIdUkuran = (TextInputLayout) findViewById(R.id.txtIdUkuran);
        layanan = getIntent().getExtras();

        txtJenisLayanan.getEditText().setText(layanan.getString("namaLayanan"));
        txtHargaLayanan.getEditText().setText(String.valueOf(layanan.getDouble("harga")));
        txtIdUkuran.getEditText().setText(String.valueOf(layanan.getDouble("idUkuran")));

        db = new DatabaseHandler(this);
        progressDialog = new ProgressDialog(editLayanan.this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView judul =(TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        judul.setText("Ubah Layanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void init() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaL, idPegawaiLogL;
                double hargaL;
                int idUkuranL;

                idPegawaiLogL = db.getUser(1).getNIP();
                namaL = txtJenisLayanan.getEditText().getText().toString();
                hargaL = Double.parseDouble(txtHargaLayanan.getEditText().toString());
                idUkuranL = Integer.parseInt(txtIdUkuran.getEditText().getText().toString());


                if(namaL.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else if(hargaL<1) {
                    Toast.makeText(getApplicationContext(), "Harga Tidak Boleh Lebih Kecil dari 0",Toast.LENGTH_SHORT).show();
                }
                else {
                    RequestBody namaLayanan =
                            RequestBody.create(MediaType.parse("multipart/form-data"),namaL);
                    RequestBody harga =
                            RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(hargaL));
                    RequestBody idUkuran =
                            RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(idUkuranL));
                    RequestBody NIP =
                            RequestBody.create(MediaType.parse("multipart/form-data"), idPegawaiLogL);




                    ApiLayanan apiService = ApiClient.getClient().create(ApiLayanan.class);
                    Call<Response> layanan = apiService.editLayanan(namaLayanan, harga, idUkuran, NIP);

                    progressDialog.setMessage("loading....");
                    progressDialog.setTitle("Mengubah Data Layanan");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                    progressDialog.show();

                    layanan.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            progressDialog.dismiss();
                            if(response.code() == 200) {
                                if(response.body().getStatus().equals("Error"))
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Tambah Data Produk Berhasil.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), showProduk.class);
                                    i.putExtra("status","getAll" );
                                    startActivity(i);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), menuLayanan.class));
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), menuLayanan.class));
    }
}
