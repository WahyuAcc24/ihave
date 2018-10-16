package com.example.sony.tes.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sony.tes.Model.Jadwal;
import com.example.sony.tes.R;
import com.example.sony.tes.util.TimeListener;

import java.util.List;

/**
 * Created by SONY on 6/9/2018.
 */
public class JamHomeAdapter extends RecyclerView.Adapter<JamHomeAdapter.Holder> {

    private List<Jadwal> jadwals;
    private Context context;

    private TimeListener listener;

    public JamHomeAdapter(Context context, List<Jadwal> jadwals) {
        this.context = context;
        this.jadwals = jadwals;
    }



    public void setListener(TimeListener listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hari, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final Jadwal jadwal = jadwals.get(position);
        holder.txtHari.setText(jadwal.getDay_name());
        for (final Integer eachTime: jadwal.getHours()) {
            Button bt = new Button(context);
            bt.setText(String.valueOf(eachTime));
            bt.setTextColor(Color.BLACK);
            bt.setBackgroundColor(Color.WHITE);
            bt.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            holder.viewgroupJam.addView(bt);
        }
    }

    @Override
    public int getItemCount() {
        return jadwals.size();
    }



    class Holder extends RecyclerView.ViewHolder {

        private TextView txtHari;
        private ViewGroup viewgroupJam;

        public Holder(View itemView) {
            super(itemView);
            txtHari = (TextView) itemView.findViewById(R.id.txt_hari);
            viewgroupJam = (LinearLayout) itemView.findViewById(R.id.viewgroup_jam);
        }
    }
}
