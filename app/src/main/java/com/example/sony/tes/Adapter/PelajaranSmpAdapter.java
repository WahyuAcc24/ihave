package com.example.sony.tes.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sony.tes.Model.pel;
import com.example.sony.tes.R;

import java.util.List;

/**
 * Created by SONY on 28/8/2018.
 */
public class PelajaranSmpAdapter extends RecyclerView.Adapter<PelajaranSmpAdapter.Holder> {


    private List<pel> pels;
    private ItemClickListenerPelajaran listener;

    public PelajaranSmpAdapter(List<pel> pels) {
        this.pels = pels;

    }



    public void setListener(ItemClickListenerPelajaran listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_smp, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.btnPel.setText(pels.get(position).getName());
        pels.get(position).getCategory_id();
        holder.btnPel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClicked(pels.get(position), position,v);
            }
        });

    }
    @Override
    public int getItemCount() {
        return pels.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        Button btnPel;

        public Holder(View itemView) {
            super(itemView);
            btnPel = (Button) itemView.findViewById(R.id.item_course_smp);

        }
    }

}
