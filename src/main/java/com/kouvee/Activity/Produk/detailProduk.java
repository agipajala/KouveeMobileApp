package com.kouvee.Activity.Produk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kouvee.API.ApiClient;
import com.kouvee.API.Interface.ApiProduk;
import com.kouvee.API.Response;
import com.kouvee.R;
import com.kouvee.Views.Produk.showProduk;
import com.kouvee.ZoomImage;
import com.zolad.zoominimageview.ZoomInImageView;

import retrofit2.Call;
import retrofit2.Callback;

public class detailProduk extends AppCompatActivity {
    private TextView dataID, dataNama, dataHarga, dataStok, dataJumlahMinim, dataDiBuat, dataDiEdit, dataDiHapus, dataNIP;
    private ZoomInImageView dataImage;
    private Button btnUbah, btnHapus, btnHapusPermanen, btnRestore;
    private Bundle produk;
    private String status;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        setAtribut();
        init();
    }

    private void setAtribut() {
        dataID = (TextView) findViewById(R.id.dataIDProduk);
        dataNama = (TextView) findViewById(R.id.txtNamaUkuran);
        dataHarga = (TextView) findViewById(R.id.txtHarga);
        dataStok = (TextView) findViewById(R.id.txtStok);
        dataJumlahMinim = (TextView) findViewById(R.id.txtJumlahMinimal);
        dataDiBuat = (TextView) findViewById(R.id.dataDibuat);
        dataDiEdit = (TextView) findViewById(R.id.dataDiedit);
        dataDiHapus = (TextView) findViewById(R.id.dataDihapus);
        dataNIP = (TextView) findViewById(R.id.dataNIP);
        dataImage = (ZoomInImageView) findViewById(R.id.dataGambarProduk);
        btnUbah = (Button) findViewById(R.id.btnUbah);
        btnHapus = (Button) findViewById(R.id.btnHapusSoft);
        btnRestore = (Button) findViewById(R.id.btnRestore);
        btnHapusPermanen = (Button) findViewById(R.id.btnHapusPermanen);

        produk = getIntent().getExtras();
        dataID.setText(produk.getString("idProduk"));
        dataNama.setText(produk.getString("namaProduk"));
        dataHarga.setText("Rp " + String.valueOf(produk.getDouble("harga"))+ "0");
        dataStok.setText(String.valueOf(produk.getInt("stok")));
        dataJumlahMinim.setText(String.valueOf(produk.getInt("jumlahMinimal")));
        dataDiBuat.setText(produk.getString("createdAt"));
        dataDiEdit.setText(produk.getString("updatedAt"));
        dataDiHapus.setText(produk.getString("deletedAt"));
        dataNIP.setText(produk.getString("idPegawaiLog"));
        status = produk.getString("status");

        Glide.with(this)
                .load(ApiClient.BASE_URL + "produk/"+ produk.getString("idProduk")+"/gambar")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(dataImage);

        if(status.equals("getAll"))
        {
            dataDiHapus.setText("-");
            btnHapusPermanen.setVisibility(View.INVISIBLE);
            btnRestore.setVisibility(View.INVISIBLE);
        }
        else
        {
            btnHapus.setVisibility(View.INVISIBLE);
            btnUbah.setVisibility(View.INVISIBLE);
        }

        builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView judul =(TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        judul.setText("Detail Produk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        String idProduk = produk.getString("idProduk");
        String NIPLog = produk.getString("idPegawaiLog");

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), editProduk.class);
                i.putExtras(produk);
                startActivity(i);
            }
        });

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiProduk apiService = ApiClient.getClient().create(ApiProduk.class);
                Call<Response> produk = apiService.restoreProduk(idProduk,NIPLog);

                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(detailProduk.this);
                progressDialog.setMessage("loading....");
                progressDialog.setTitle("Menghapus Data Produk");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // show it
                progressDialog.show();
                produk.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        progressDialog.dismiss();
                        if(response.code() == 200)
                        {
                            Toast.makeText(getApplicationContext(),"Data Produk Berhasil Dipulihkan", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), showProduk.class);
                            i.putExtra("status",status );
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               builder.setMessage("Anda Yakin Ingin Menghapus Produk ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiProduk apiService = ApiClient.getClient().create(ApiProduk.class);
                        Call<Response> produk = apiService.deleteProduk(idProduk,NIPLog);

                        final ProgressDialog progressDialog;
                        progressDialog = new ProgressDialog(detailProduk.this);
                        progressDialog.setMessage("loading....");
                        progressDialog.setTitle("Menghapus Data Produk");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        // show it
                        progressDialog.show();
                        produk.enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                progressDialog.dismiss();
                                if(response.code() == 200)
                                {
                                    Toast.makeText(getApplicationContext(),"Data Produk Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), showProduk.class);
                                    i.putExtra("status",status );
                                    startActivity(i);
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Data Produk Ini Tidak Dapat Dihapus.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        ApiProduk apiService = ApiClient.getClient().create(ApiProduk.class);
                        Call<Response> produk = apiService.deleteProdukPermanen(idProduk);

                        final ProgressDialog progressDialog;
                        progressDialog = new ProgressDialog(detailProduk.this);
                        progressDialog.setMessage("loading....");
                        progressDialog.setTitle("Menghapus Data Produk Permanen");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        // show it
                        progressDialog.show();
                        produk.enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                progressDialog.dismiss();
                                if(response.code() == 200)
                                {
                                    Toast.makeText(getApplicationContext(),"Data Produk Berhasil Dihapus Permanen.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), showProduk.class);
                                    i.putExtra("status",status );
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Data Produk Ini Tidak Dapat Dihapus Permanen.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
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

        dataImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(detailProduk.this, ZoomImage.class);
                i.putExtras(produk);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(getApplicationContext(), showProduk.class);
        i.putExtra("status",status );
        startActivity(i);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), showProduk.class);
        i.putExtra("status",status );
        startActivity(i);
    }
}
