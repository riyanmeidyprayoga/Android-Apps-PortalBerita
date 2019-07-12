package com.riyanmeidyprayoga.www.uas2019v2.pengaduan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riyanmeidyprayoga.www.uas2019v2.R;

import java.util.ArrayList;
import java.util.List;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.MyViewHolder> {
   private Context context;
   private List<CatatanModul> data=new ArrayList<>();

   public CatatanAdapter(Context context, List<CatatanModul> data){
       this.context = context;
       this.data = data;
   }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(context).inflate(R.layout.item_pengaduan, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvNama.setText(data.get(position).getNama());
        holder.tvNik.setText(data.get(position).getNik());
        holder.tvIsi.setText(data.get(position).getIsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(context, DetailPengaduanActivity.class);
                pindah.putExtra(DetailPengaduanActivity.KEY_ID, data.get(position).getId());
                context.startActivity(pindah);
            }
        });
   }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView tvNama, tvNik, tvIsi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvNik = itemView.findViewById(R.id.tvNik);
            tvIsi = itemView.findViewById(R.id.tvIsi);
        }
    }
}
