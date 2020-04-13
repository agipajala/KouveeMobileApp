package com.kouvee.Views.Layanan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kouvee.API.ApiClient;
import com.kouvee.API.Interface.ApiProduk;
import com.kouvee.API.Response;
import com.kouvee.Activity.Layanan.menuLayanan;
import com.kouvee.DAO.layananDAO;
import com.kouvee.R;
import com.kouvee.Views.Layanan.LayananAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class showLayanan extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private LayananAdapter adapter;
    private List<layananDAO> listLayanan;
    private String status;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_layanan);
        setAdapter();
        setLayanan();
    }

    private void setAdapter() {
        status = getIntent().getStringExtra("status");
        listLayanan = new ArrayList<layananDAO>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new LayananAdapter(this, listLayanan, status);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        judul =(TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setLayanan()
    {
        if(status.equals("getAll"))
        {
            judul.setText("Daftar Layanan");
            ApiProduk apiService = ApiClient.getClient().create(ApiProduk.class);
            Call<Response> layanans = apiService.getAll();

            response(layanans);
        }
        else
        {
            judul.setText("Daftar Layanan Di Hapus");
            ApiProduk apiService = ApiClient.getClient().create(ApiProduk.class);
            Call<Response> layanans = apiService.getSoftDelete();

            response(layanans);
        }
    }

    private void response(Call<Response> layanans) {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading....");
        if(status.equals("getAll"))
            progressDialog.setTitle("Menampilkan Daftar Layanan");
        else
            progressDialog.setTitle("Menampilkan Daftar Layanan Dihapus");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.show();

        layanans.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                progressDialog.dismiss();
                if(response.body().getLayanan().isEmpty())
                {
                    if(status.equals("getAll"))
                        Toast.makeText(getApplicationContext(), "Tidak ada layanan",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Tidak ada layanan yang dihapus",Toast.LENGTH_SHORT).show();
                }
                listLayanan.addAll(response.body().getLayanan());
                adapter.notifyDataSetChanged();
    }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);

        MenuItem menuItem = menu.findItem(R.id.txtSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        String userInput = query.toLowerCase();
        adapter.getFilter().filter(userInput);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        adapter.getFilter().filter(userInput);
        return true;
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

