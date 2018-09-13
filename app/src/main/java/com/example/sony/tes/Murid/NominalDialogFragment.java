package com.example.sony.tes.Murid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sony.tes.R;

/**
 * Created by SONY on 13/9/2018.
 */
public class NominalDialogFragment extends BottomSheetDialogFragment {

    private EditText edtNominal;

    public static NominalDialogFragment getInstance() {
        return new NominalDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.buttom_sheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtNominal = (EditText) view.findViewById(R.id.edt_nominal);
        view.findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request API untuk bayar
            }
        });
    }
}
