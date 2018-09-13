package com.example.sony.tes.Murid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.sony.tes.R;

public class FormPembayaranActivity extends AppCompatActivity {

    TextView txtInvoice, txtSaldo;
//    public TopUp topUps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaksi_murid);

        txtInvoice = (TextView) findViewById(R.id.txtinvoice);
        txtSaldo = (TextView) findViewById(R.id.txttotal);


//        topUps = new Gson().fromJson(getIntent().getStringExtra("data"), TopUp.class);

//        txtInvoice.setText(topUps.getInvoice());
//        txtSaldo.setText(topUps.getSubtotal());
    }

}
