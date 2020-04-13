package com.kouvee.Views.Produk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kouvee.API.ApiClient;
import com.kouvee.Activity.Produk.detailProduk;
import com.kouvee.DAO.produkDAO;
import com.kouvee.R;
import com.kouvee.ZoomImage;

import java.util.ArrayList;
import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {

    private List<produkDAO> produkList;
    private List<produkDAO> produkListFiltered;
    private Context context;
    private String status;

    public ProdukAdapter(Context context, List<produkDAO> produkList, String status) {
        this.context=context;
        this.produkList = produkList;
        this.produkListFiltered = produkList;
        this.status = status;
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_produk_adapter, parent, false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukAdapter.ProdukViewHolder holder, int position) {
        final produkDAO produks = produkListFiltered.get(position);
        holder.namaProduk.setText       (produks.getNamaProduk());
        holder.harga.setText            ("Rp "+String.valueOf(produks.getHarga() +"0"));
        holder.stok.setText             ("Stok : "+String.valueOf(produks.getStok()));

        String url = ApiClient.BASE_URL +  "produk/" + produks.getIdProduk() + "/gambar";
        Glide.with(context)
                .load(url)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.gambar);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Bundle produk = new Bundle();

                produk.putString("idProduk", produks.getIdProduk());
                produk.putString("namaProduk",produks.getNamaProduk());
                produk.putDouble("harga",produks.getHarga());
                produk.putInt("stok",produks.getStok());
                produk.putInt("jumlahMinimal",produks.getJumlahMinimal());
                produk.putString("createdAt",produks.getCreated_at());
                produk.putString("updatedAt",produks.getUpdated_at());
                produk.putString("deletedAt",produks.getDeleted_at());
                produk.putString("idPegawaiLog",produks.getIdPegawaiLog());
                produk.putString("status",status);

                Intent i = new Intent(context, detailProduk.class);
                i.putExtras(produk);
                context.startActivity(i);
            }
        });

        holder.gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle produk = new Bundle();

                produk.putString("idProduk", produks.getIdProduk());
                produk.putString("namaProduk",produks.getNamaProduk());
                produk.putString("status", "produk");

                Intent i = new Intent(context, ZoomImage.class);
                i.putExtras(produk);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (produkListFiltered != null) ? produkListFiltered.size() : 0;
    }

    public class ProdukViewHolder extends RecyclerView.ViewHolder{
        private ImageView gambar;
        private TextView namaProduk, harga, stok;
        private Button btnDetail;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            namaProduk = (TextView) itemView.findViewById(R.id.txtNamaUkuran);
            harga = (TextView) itemView.findViewById(R.id.txtHarga);
            stok = (TextView) itemView.findViewById(R.id.txtStok);
            gambar = (ImageView) itemView.findViewById(R.id.ivGambarProduk);
            btnDetail = (Button) itemView.findViewById(R.id.btnDetail);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String userInput = charSequence.toString();
                System.out.printf(charSequence.toString());
                if (userInput.isEmpty()) {
                    produkListFiltered = produkList;
                }
                else {
                    List<produkDAO> filteredList = new ArrayList<>();
                    for (produkDAO produk : produkList) {
                        if(produk.getIdProduk().toLowerCase().contains(userInput) || produk.getNamaProduk().toLowerCase().contains(userInput))
                        {
                            filteredList.add(produk);
                            System.out.println(produk.getNamaProduk());
                        }
                    }
                    produkListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = produkListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                produkListFiltered = (ArrayList<produkDAO>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }
}
