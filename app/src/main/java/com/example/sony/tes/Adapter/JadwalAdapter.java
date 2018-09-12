package com.example.sony.tes.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sony.tes.Model.JadwalHistory;
import com.example.sony.tes.R;

import java.util.List;

/**
 * Created by SONY on 12/9/2018.
 */
public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.Holder> {

    private List<JadwalHistory> jadwals;

    public JadwalAdapter(List<JadwalHistory> jadwals) {
        this.jadwals = jadwals;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hour_course, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.txtDay.setText(jadwals.get(position).getDay());
        holder.txtHour.setText(jadwals.get(position).getHours());
    }

    @Override
    public int getItemCount() {
        return jadwals.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView txtDay, txtHour;

        public Holder(View itemView) {
            super(itemView);
            txtDay = (TextView) itemView.findViewById(R.id.txt_day);
            txtHour = (TextView) itemView.findViewById(R.id.txt_hour);
        }
    }

}
