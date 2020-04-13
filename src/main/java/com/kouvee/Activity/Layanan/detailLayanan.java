package com.kouvee.Activity.Layanan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kouvee.API.ApiClient;
import com.kouvee.API.Interface.ApiLayanan;
import com.kouvee.API.Interface.ApiProduk;
import com.kouvee.API.Response;
import com.kouvee.Activity.Produk.detailProduk;
import com.kouvee.Activity.Produk.editProduk;
import com.kouvee.R;
import com.kouvee.Views.Layanan.showLayanan;
import com.kouvee.Views.Produk.showProduk;

import javax.security.auth.callback.Callback;

import retrofit2.Call;

public class detailLayanan extends AppCompatActivity {
    private TextView dataId, dataJenis, dataHarga, dataIdUkuran, dataDiBuat, dataDiEdit, dataDiHapus, dataNIP;
    private Button btnUbah, btnHapus, btnHapusPermanen, btnRestore;
    private Bundle layanan;
    private String status;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);
        setAtribut();
        init();
    }

    private void setAtribut() {
        dataId = (TextView) findViewById(R.id.dataIdLayanan);
        dataJenis = (TextView) findViewById(R.id.txtJenisLayanan);
        dataHarga = (TextView) findViewById(R.id.txtHargaLayanan);
        dataIdUkuran = (TextView) findViewById(R.id.txtIdUkuran);
        dataDiBuat = (TextView) findViewById(R.id.dataDibuat);
        dataDiEdit = (TextView) findViewById(R.id.dataDiedit);
        dataDiHapus = (TextView) findViewById(R.id.dataDihapus);
        dataNIP = (TextView) findViewById(R.id.dataNIP);
        btnUbah = (Button) findViewById(R.id.btnUbah);
        btnHapus = (Button) findViewById(R.id.btnHapusSoft);
        btnRestore = (Button) findViewById(R.id.btnRestore);
        btnHapusPermanen = (Button) findViewById(R.id.btnHapusPermanen);

        layanan = getIntent().getExtras();
        dataId.setText(layanan.getString("idLayanan"));
        dataJenis.setText(layanan.getString("jenisLayanan"));
        dataHarga.setText("Rp " + String.valueOf(layanan.getDouble("harga")) + "0");
        dataIdUkuran.setText(String.valueOf(layanan.getInt("idUkuran")));
        dataDiBuat.setText(layanan.getString("createdAt"));
        dataDiEdit.setText(layanan.getString("updatedAt"));
        dataDiHapus.setText(layanan.getString("deletedAt"));
        dataNIP.setText(layanan.getString("idPegawaiLog"));
        status = layanan.getString("status");

        if (status.equals("getAll")) {
            dataDiHapus.setText("-");
            btnHapusPermanen.setVisibility(View.INVISIBLE);
            btnRestore.setVisibility(View.INVISIBLE);
        } else {
            btnHapus.setVisibility(View.INVISIBLE);
            btnUbah.setVisibility(View.INVISIBLE);
        }
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView judul = (TextView) findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        judul.setText("Detail Layanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        String idLayanan = layanan.getString("idLayanan");
        String NIPLog = layanan.getString("idPegawaiLog");

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), editProduk.class);
                i.putExtras(layanan);
                startActivity(i);
            }
        });

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiLayanan apiService = ApiClient.getClient().create(ApiLayanan.class);
                Call<Response> layanan = apiService.restoreLayanan(idLayanan, NIPLog);

                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(detailLayanan.this);
                progressDialog.setMessage("loading....");
                progressDialog.setTitle("Menghapus Data Layanan");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // show it
                progressDialog.show();
                layanan.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        progressDialog.dismiss();
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Data Layanan Berhasil Dipulihkan", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), showLayanan.class);
                            i.putExtra("status", status);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Anda Yakin Ingin Menghapus Layanan ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiLayanan apiService = ApiClient.getClient().create(ApiLayanan.class);
                        Call<Response> layanan = apiService.deleteLayanan(idLayanan, NIPLog);

                        final ProgressDialog progressDialog;
                        progressDialog = new ProgressDialog(detailLayanan.this);
                        progressDialog.setMessage("loading....");
                        progressDialog.setTitle("Menghapus Data Produk");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        // show it
                        progressDialog.show();
                        layanan.enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                progressDialog.dismiss();
                                if (response.code() == 200) {
                                    Toast.makeText(getApplicationContext(), "Data Layanan Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), showLayanan.class);
                                    i.putExtra("status", status);
                                    startActivity(i);
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Data Layanan Ini Tidak Dapat Dihapus.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        btnHapusPermanen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Anda Yakin Ingin Menghapus Produk Secara Permanen ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiLayanan apiService = ApiClient.getClient().create(ApiLayanan.class);
                        Call<Response> layanan = apiService.deleteLayananPermanen(idLayanan);

                        final ProgressDialog progressDialog;
                        progressDialog = new ProgressDialog(detailLayanan.this);
                        progressDialog.setMessage("loading....");
                        progressDialog.setTitle("Menghapus Data Layanan Permanen");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        // show it
                        progressDialog.show();
                        layanan.enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                progressDialog.dismiss();
                                if (response.code() == 200) {
                                    Toast.makeText(getApplicationContext(), "Data Layanan Berhasil Dihapus Permanen.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), showLayanan.class);
                                    i.putExtra("status", status);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Data Layanan Ini Tidak Dapat Dihapus Permanen.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(getApplicationContext(), showLayanan.class);
        i.putExtra("status",status );
        startActivity(i);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), showLayanan.class);
        i.putExtra("status",status );
        startActivity(i);
    }
}
