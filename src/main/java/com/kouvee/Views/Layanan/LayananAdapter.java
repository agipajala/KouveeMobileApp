package com.kouvee.Views.Layanan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kouvee.API.ApiClient;
import com.kouvee.Activity.Layanan.detailLayanan;
import com.kouvee.DAO.layananDAO;
import com.kouvee.R;
import com.kouvee.Views.Produk.ProdukAdapter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.LayananViewHolder>{
    private List<layananDAO> layananList;
    private List<layananDAO> layananListFiltered;
    private Context context;
    private String status;

    public LayananAdapter(Context context, List<layananDAO> layananList, String status) {
        this.context = context;
        this.layananList = layananList;
        this.layananListFiltered = layananList;
        this.status = status;
    }

    @NonNull
    @Override
    public LayananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_layanan_adapter, parent, false);
        return new LayananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LayananAdapter.LayananViewHolder holder, int position) {
        final layananDAO layanans = layananListFiltered.get(position);
        holder.namaLayanan.setText      (layanans.getNamaLayanan());
        holder.harga.setText            ("Rp "+String.valueOf(layanans.getHarga() +"0"));

        String url = ApiClient.BASE_URL + "layanan/" + layanans.getIdLayanan();
        Glide.with(context)
                .load(url)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);

        holder.btnDetailLayanan.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view){
               Bundle layanan = new Bundle();

               layanan.putString("idLayanan", layanans.getIdLayanan());
               layanan.putString("namaLayanan", layanans.getNamaLayanan());
               layanan.putDouble("harga", layanans.getHarga());
               layanan.putInt("IdUkuran", layanans.getIdUkuran());
               layanan.putString("createdAt", layanans.getCreated_at());
               layanan.putString("updatedAt", layanans.getUpdated_at());
               layanan.putString("deletedAt", layanans.getDeleted_at());
               layanan.putString("idPegawaiLog", layanans.getIdPegawaiLog());
               layanan.putString("status",status);

               Intent i = new Intent(context, detailLayanan.class);
               i.putExtras(layanan);
               context.startActivity(i);

           }
        });
    }

    @Override
    public int getItemCount() {
        return (layananListFiltered != null) ? layananListFiltered.size() : 0;
    }

    public class LayananViewHolder extends  RecyclerView.ViewHolder {
        private TextView namaLayanan, harga;
        private Button btnDetailLayanan;

        public LayananViewHolder(@NonNull View itemView) {
            super(itemView);
            namaLayanan = (TextView) itemView.findViewById(R.id.txtNamaLayanan);
            harga = (TextView) itemView.findViewById(R.id.txtHargaLayanan);
            btnDetailLayanan = (Button) itemView.findViewById(R.id.btnDetailLayanan);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String userInput = charSequence.toString();
                System.out.printf(charSequence.toString());
                if (userInput.isEmpty()) {
                    layananListFiltered = layananList;
                }
                else {
                    List<layananDAO> filteredList = new ArrayList<>();
                    for(layananDAO layanan : layananList) {
                        if(layanan.getIdLayanan().toLowerCase().contains(userInput) || layanan.getNamaLayanan().toLowerCase().contains(userInput)) {
                            filteredList.add(layanan);
                            System.out.println(layanan.getNamaLayanan());
                        }
                    }
                    layananListFiltered = layananList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = layananListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                layananListFiltered = (ArrayList<layananDAO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
