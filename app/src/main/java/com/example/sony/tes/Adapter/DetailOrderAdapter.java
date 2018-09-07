package com.example.sony.tes.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sony.tes.Model.Jadwal;
import com.example.sony.tes.R;

import java.util.List;

/**
 * Created by SONY on 6/9/2018.
 */
public class DetailOrderAdapter extends RecyclerView.Adapter<DetailOrderAdapter.Holder> {

    public static String urlFoto = "http://192.168.43.140/nice/images/";

    private List<Jadwal> jadwals;

    public DetailOrderAdapter(List<Jadwal> jadwals) {
        this.jadwals = jadwals;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hari, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Jadwal jadwal = jadwals.get(position);
        holder.txtHari.setText(jadwal.getDay_name());
//        holder.btnJam.setText(String.valueOf(jadwal.getHours()));

//
//        Glide.with(holder.imgFoto.getContext())
//                .load(urlFoto + votes.get(position).getImages())
//                .error(R.mipmap.nice_logo)
//                .placeholder(R.mipmap.nice_logo)
//                .into(holder.imgFoto);

    }

    @Override
    public int getItemCount() {
        return jadwals.size();
    }

    class Holder extends RecyclerView.ViewHolder {


        TextView txtHari;
        ImageView imgFoto;

        public Holder(View itemView) {
            super(itemView);
            txtHari = (TextView) itemView.findViewById(R.id.txtHari);

            imgFoto = (ImageView) itemView.findViewById(R.id.imgGuru);

        }
    }
}

//        String time = ""; //contoh doang pake button yang sama, harusnya button btnJam di generate lagi sesuai banyaknya jam
//        for (Integer hour: jadwal.getHours()) {
//            time = time + ", " + hour;
//        }
