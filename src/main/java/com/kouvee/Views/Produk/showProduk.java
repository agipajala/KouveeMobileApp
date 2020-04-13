package com.kouvee.Views.Produk;

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
import com.kouvee.Activity.HomeActivity;
import com.kouvee.Activity.Produk.MenuProduk;
import com.kouvee.DAO.produkDAO;
import com.kouvee.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class showProduk extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private ProdukAdapter adapter;
    private List<produkDAO> listProduk;
    private String status;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_produk);
        setAdapter();
        setProduk();
    }

    private void setAdapter() {
        status = getIntent().getStringExtra("status");
        listProduk = new ArrayList<produkDAO>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ProdukAdapter(this, listProduk, status);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        judul =(TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setProduk()
    {
        if(status.equals("getAll"))
        {
            judul.setText("Daftar Produk");
            ApiProduk apiService = ApiClient.getClient().create(ApiProduk.class);
            Call<Response> produks = apiService.getAll();

            response(produks);
        }
        else
        {
            judul.setText("Daftar Produk Di Hapus");
            ApiProduk apiService = ApiClient.getClient().create(ApiProduk.class);
            Call<Response> produks = apiService.getSoftDelete();

            response(produks);
        }
    }

    private void response(Call<Response> produks) {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading....");
        if(status.equals("getAll"))
            progressDialog.setTitle("Menampilkan Daftar Produk");
        else
            progressDialog.setTitle("Menampilkan Daftar Produk Dihapus");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();

        produks.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                progressDialog.dismiss();
                if(response.body().getProduk().isEmpty())
                {
                    if(status.equals("getAll"))
                        Toast.makeText(getApplicationContext(), "Tidak ada produk",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Tidak ada produk yang dihapus",Toast.LENGTH_SHORT).show();
                }
                listProduk.addAll(response.body().getProduk());
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
        startActivity(new Intent(getApplicationContext(), MenuProduk.class));
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MenuProduk.class));
    }
}
