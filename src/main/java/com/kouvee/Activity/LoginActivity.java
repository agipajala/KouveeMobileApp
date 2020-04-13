package com.kouvee.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.kouvee.API.ApiClient;
import com.kouvee.API.Interface.ApiPegawai;
import com.kouvee.API.Response;
import com.kouvee.R;
import com.kouvee.SQLite.DatabaseHandler;
import com.kouvee.SQLite.UserDefaults;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout txtNIP, txtSandi;
    private Button btnMasuk;
    public static Bundle pgw;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        setAtribut();
        cekLogin();
        init();
    }

    private void cekLogin() {
        if(db.isEmpty() == false)
        {
            String status = db.getUser(1).getStatus();
            if(status.equals("isLogin")){
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                i.putExtra("status","home" );
                startActivity(i);
            }
        }
    }

    public void setAtribut(){
        txtNIP = (TextInputLayout) findViewById(R.id.txtNIP);
        txtSandi = (TextInputLayout) findViewById(R.id.txtKataSandi);

        btnMasuk = (Button) findViewById(R.id.btnMasuk);
        db = new DatabaseHandler(this);
    }

    private void init() {
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NIP = txtNIP.getEditText().getText().toString();
                String Sandi = txtSandi.getEditText().getText().toString();

                ApiPegawai apiService = ApiClient.getClient().create(ApiPegawai.class);
                Call<Response> pegawai = apiService.Login(NIP,Sandi);

                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("loading....");
                progressDialog.setTitle("Perijinan Masuk");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // show it
                progressDialog.show();

                pegawai.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        progressDialog.dismiss();
                        if(response.code()==200)
                        {
                            String nip, nama, jabatan;
                            nip = response.body().getPegawai().get(0).getNIP();
                            nama = response.body().getPegawai().get(0).getNamaPegawai();
                            jabatan = response.body().getPegawai().get(0).getJabatan();

                            UserDefaults user = new UserDefaults(1,nip,nama,jabatan,"isLogin");

                            if(db.isEmpty()==true)
                                db.addUser(user);
                            else
                                db.updateUser(user);

                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            i.putExtra("status","home" );
                            startActivity(i);
                        }
                        else if(response.code()==404)
                        {
                            Toast.makeText(getApplicationContext(), "NIP tidak terdaftar", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Kata Sandi Tidak Cocok", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
