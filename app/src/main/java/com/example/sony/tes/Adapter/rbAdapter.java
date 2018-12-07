package com.example.sony.tes.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sony.tes.Model.Data;
import com.example.sony.tes.R;

import java.util.List;

/**
 * Created by SONY on 15/11/2018.
 */
public class rbAdapter extends RecyclerView.Adapter<rbAdapter.Holder> {

    private List<Data> datas;
    private Context context;
    private int lastSelectedPosition = -1;
    private ItemClickListenerRb listenerRb;

    public rbAdapter(Context context, List<Data> datas) {
        this.context = context;
        this.datas = datas;
    }


    public void setListenerRb (ItemClickListenerRb listenerRb) {
        this.listenerRb = listenerRb;
    }

    @Override
    public rbAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_radio, parent, false);
        rbAdapter.Holder holder = new rbAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {



        holder.txtMatpel.setText(datas.get(position).getName());
        holder.rbpel.setId(datas.get(position).getId());
        holder.rbpel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRb.onClickedRb(datas.get(position), position,v);
            }
        });

//        holder.rbpel.setChecked(lastSelectedPosition == position);



//        holder.btnPel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClicked(listmatpel.get(position), position,v);
//            }
//        });

    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        RadioButton rbpel;
        TextView txtMatpel;


        public Holder(View itemView) {
            super(itemView);
            txtMatpel = (TextView) itemView.findViewById(R.id.namaRb);
            rbpel = (RadioButton) itemView.findViewById(R.id.rb);


            rbpel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (v.isSelected()){
                                rbpel.setSelected(false);
                                rbpel.setChecked(false);
                    }else{
                                rbpel.setSelected(true);
                                rbpel.setChecked(true);
                    }

                    Toast.makeText(rbAdapter.this.context,
                            "selected offer is " + rbpel.getId(),
                            Toast.LENGTH_LONG).show();



                }
            });
//                    switch (v.getId()){
//
//                        case R.id.rb:
//                            if (rbpel.isSelected()){
//                                rbpel.setSelected(false);
//                                rbpel.setChecked(false);
//                            }else{
//                                rbpel.setSelected(true);
//                                rbpel.setChecked(true);
//                            }
//                            break;
//
//                    }
        }
    }


}

