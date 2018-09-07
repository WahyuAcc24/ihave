package com.example.sony.tes.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sony.tes.Model.Jadwal;
import com.example.sony.tes.R;

import java.util.List;

/**
 * Created by SONY on 6/9/2018.
 */
public class JamAdapter extends RecyclerView.Adapter<JamAdapter.Holder> {

    public static String urlFoto = "http://192.168.43.140/nice/images/";

    private List<Jadwal> jadwals;
    private ItemClickListenerHour listenerHour;

    public JamAdapter(List<Jadwal> jadwals) {
        this.jadwals = jadwals;
    }


    public void setListener(ItemClickListenerHour listener) {
        this.listenerHour = listener;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jam, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final Jadwal jadwal = jadwals.get(position);
        holder.btnJam.setText(String.valueOf(jadwal.getHours()));
        holder.btnJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerHour.onClickedHour(jadwal.getHours());

            }
        });
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

        Button btnJam;

        ImageView imgFoto;

        public Holder(View itemView) {
            super(itemView);

            btnJam = (Button) itemView.findViewById(R.id.jamBtn);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgGuru);

        }
    }
}

//        String time = ""; //contoh doang pake button yang sama, harusnya button btnJam di generate lagi sesuai banyaknya jam
//        for (Integer hour: jadwal.getHours()) {
//            time = time + ", " + hour;
//        }
