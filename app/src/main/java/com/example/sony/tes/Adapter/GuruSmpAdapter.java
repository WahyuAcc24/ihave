package com.example.sony.tes.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sony.tes.Model.Guru;
import com.example.sony.tes.Model.Matpel;
import com.example.sony.tes.R;

/**
 * Created by SONY on 28/8/2018.
 */
public class GuruSmpAdapter extends RecyclerView.Adapter<GuruSmpAdapter.Holder> {

    private Guru[] guru;
    private Matpel pel;
    private ItemClickListenerBebas listener;
    private Context context;

    public GuruSmpAdapter(Guru[] guru, Context context) {
        this.guru =  guru;
        this.context = context;

    }

    public void setListener(ItemClickListenerBebas listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guru_smp, parent, false));

    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        holder.btnGuru.setText(guru[position].getFullname());
        holder.btnGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClicked(guru[position],position,v);
            }
        });

    }
    @Override
    public int getItemCount() {
        return guru.length;
    }

    class Holder extends RecyclerView.ViewHolder {

        Button btnGuru;
        TextView txtPelajaran;

        public Holder(View itemView) {
            super(itemView);
            btnGuru = (Button) itemView.findViewById(R.id.grid_item_guru_smp);

        }
    }

}
