package com.example.sony.tes.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sony.tes.Model.History;
import com.example.sony.tes.R;

import java.util.List;

/**
 * Created by SONY on 12/9/2018.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder> {

    public static String urlFoto = "http://t-hisyam.net/demo/whyri/vote/images/";

    private List<History> historis;
    private ItemClickListener<History> listener;

    public HistoryAdapter(List<History> historis) {
        this.historis = historis;
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_murid, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.txtNama.setText(historis.get(position).getInvoice());
        holder.txtTgl.setText(historis.get(position).getOrder_date());
        holder.txtStatus.setText(historis.get(position).getStatus());

        holder.itemVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClicked(historis.get(position), position, v);
            }
        });

//        Glide.with(holder.imgFoto.getContext())
//                .load(urlFoto + votes.get(position).getImages())
//                .error(R.mipmap.logo_vote)
//                .placeholder(R.mipmap.logo_vote)
//                .into(holder.imgFoto);
//
    }

    @Override
    public int getItemCount() {
        return historis.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView txtNama,  txtMp, txtTgl, txtJam, txtTotal, txtStatus;
        private LinearLayout itemVote;

        public Holder(View itemView) {
            super(itemView);
            itemVote = (LinearLayout) itemView.findViewById(R.id.linearHistory);
//            imgFoto = (ImageView) itemView.findViewById(R.id.img_foto);
            txtNama = (TextView) itemView.findViewById(R.id.nama);
            txtMp = (TextView) itemView.findViewById(R.id.mp);
            txtTgl = (TextView) itemView.findViewById(R.id.tgl);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);


        }
    }

}
