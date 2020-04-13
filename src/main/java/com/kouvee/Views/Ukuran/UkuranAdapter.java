package com.kouvee.Views.Ukuran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.kouvee.API.ApiClient;
import com.kouvee.API.Interface.ApiUkuran;
import com.kouvee.API.Response;
import com.kouvee.Activity.Ukuran.editUkuran;
import com.kouvee.DAO.ukuranDAO;
import com.kouvee.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UkuranAdapter extends RecyclerView.Adapter<UkuranAdapter.UkuranViewHolder> {

    private List<ukuranDAO> ukuranList;
    private List<ukuranDAO> ukuranListFiltered;
    private Context context;
    private String status;

    public UkuranAdapter(Context context, List<ukuranDAO> ukuranList, String status) {
        this.context=context;
        this.ukuranList = ukuranList;
        this.ukuranListFiltered = ukuranList;
        this.status = status;
    }

    @NonNull
    @Override
    public UkuranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_ukuran_adapter, parent, false);
        return new UkuranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UkuranViewHolder holder, int position) {
        final ukuranDAO ukurans = ukuranListFiltered.get(position);
        holder.idUkuran.setText         ("ID Ukuran            : "+ String.valueOf(ukurans.getIdUkuran()));
        holder.namaUkuran.setText       ("Nama Ukuran          : "+ ukurans.getNamaUkuran());
        holder.createdAt.setText        ("Tanggal Dibuat       : "+ ukurans.getCreated_at());
        holder.updatedAt.setText        ("Tanggal Diubah       : "+ ukurans.getUpdated_at());
        holder.deletedAt.setText        ("Tanggal Dihapus      : "+ ukurans.getDeleted_at());
        holder.idPegawaiLog.setText     ("NIP Log              : "+ ukurans.getIdPegawaiLog());

        holder.btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.equals("getAll"))
                {
                    Bundle ukuran = new Bundle();

                    ukuran.putInt("idUkuran", ukurans.getIdUkuran());
                    ukuran.putString("namaUkuran",ukurans.getNamaUkuran());
                    ukuran.putString("createdAt",ukurans.getCreated_at());
                    ukuran.putString("updatedAt",ukurans.getUpdated_at());
                    ukuran.putString("deletedAt",ukurans.getDeleted_at());
                    ukuran.putString("idPegawaiLog",ukurans.getIdPegawaiLog());
                    ukuran.putString("status",status);

                    Intent i = new Intent(context, editUkuran.class);
                    i.putExtras(ukuran);
                    context.startActivity(i);
                }
                else
                {
                    ApiUkuran apiService = ApiClient.getClient().create(ApiUkuran.class);
                    Call<Response> ukuran = apiService.restoreUkuran(ukurans.getIdUkuran(),ukurans.getIdPegawaiLog());

                    final ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("loading....");
                    progressDialog.setTitle("Memulihkan Data Ukuran");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    // show it
                    progressDialog.show();
                    ukuran.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            progressDialog.dismiss();
                            System.out.printf(String.valueOf(response.code()));
                            if(response.code() == 200)
                            {
                                Toast.makeText(context,"Data Ukuran Berhasil Dipulihkan", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(context, showUkuran.class);
                                i.putExtra("status",status );
                                context.startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);

                if(status.equals("getAll"))
                {
                    builder.setMessage("Anda Yakin Ingin Menghapus Ukuran ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ApiUkuran apiService = ApiClient.getClient().create(ApiUkuran.class);
                            Call<Response> ukuran = apiService.deleteUkuran(ukurans.getIdUkuran(),ukurans.getIdPegawaiLog());

                            final ProgressDialog progressDialog;
                            progressDialog = new ProgressDialog(context);
                            progressDialog.setMessage("loading....");
                            progressDialog.setTitle("Menghapus Data Ukuran");
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            // show it
                            progressDialog.show();
                            ukuran.enqueue(new Callback<Response>() {
                                @Override
                                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                    progressDialog.dismiss();
                                    if(response.code() == 200)
                                    {
                                        Toast.makeText(context,"Data Ukuran Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(context, showUkuran.class);
                                        i.putExtra("status",status );
                                        context.startActivity(i);
                                    }
                                    else
                                    {
                                        progressDialog.dismiss();
                                        Toast.makeText(context,"Data Ukuran Ini Tidak Dapat Dihapus", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Response> call, Throwable t) {
                                    Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            //finish();
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
                else
                {
                    builder.setMessage("Anda Yakin Ingin Menghapus Ukuran Secara Permanen ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ApiUkuran apiService = ApiClient.getClient().create(ApiUkuran.class);
                            Call<Response> ukuran = apiService.deleteUkuranPermanen(ukurans.getIdUkuran());

                            final ProgressDialog progressDialog;
                            progressDialog = new ProgressDialog(context);
                            progressDialog.setMessage("loading....");
                            progressDialog.setTitle("Menghapus Data Ukuran Permanen");
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            // show it
                            progressDialog.show();
                            ukuran.enqueue(new Callback<Response>() {
                                @Override
                                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                    progressDialog.dismiss();
                                    if(response.code() == 200)
                                    {
                                        Toast.makeText(context,"Data Ukuran Berhasil Dihapus Permanen.", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(context, showUkuran.class);
                                        i.putExtra("status",status );
                                        context.startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(context,"Data Ukuran Ini Tidak Dapat Dihapus Permanen.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Response> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            //finish();
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return (ukuranListFiltered != null) ? ukuranListFiltered.size() : 0;
    }

    public class UkuranViewHolder extends RecyclerView.ViewHolder {
        private TextView idUkuran, namaUkuran, createdAt, updatedAt, deletedAt, idPegawaiLog;
        private Button btnUbah, btnHapus;

        public UkuranViewHolder(@NonNull View itemView) {
            super(itemView);
            idUkuran = (TextView) itemView.findViewById(R.id.txtIdUkuran);
            namaUkuran = (TextView) itemView.findViewById(R.id.txtNamaUkuran);
            createdAt = (TextView) itemView.findViewById(R.id.txtCreatedAt);
            updatedAt = (TextView) itemView.findViewById(R.id.txtUpdatedAt);
            deletedAt = (TextView) itemView.findViewById(R.id.txtDeletedAt);
            idPegawaiLog = (TextView) itemView.findViewById(R.id.txtIdPegawaiLog);

            btnUbah = (Button) itemView.findViewById(R.id.btnUbah);
            btnHapus = (Button) itemView.findViewById(R.id.btnHapus);

            if(status.equals("getAll"))
                btnUbah.setText("UBAH");
            else
                btnUbah.setText("PULIHKAN");
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String userInput = charSequence.toString();
                System.out.printf(charSequence.toString());
                if (userInput.isEmpty()) {
                    ukuranListFiltered = ukuranList;
                } else {
                    List<ukuranDAO> filteredList = new ArrayList<>();
                    for (ukuranDAO ukuran : ukuranList) {
                        if (String.valueOf(ukuran.getIdUkuran()).toLowerCase().contains(userInput) || ukuran.getNamaUkuran().toLowerCase().contains(userInput)) {
                            filteredList.add(ukuran);
                            System.out.println(ukuran.getNamaUkuran());
                        }
                    }
                    ukuranListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ukuranListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ukuranListFiltered = (ArrayList<ukuranDAO>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }
}
