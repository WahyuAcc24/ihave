package com.example.sony.tes.Murid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.sony.tes.R;

import io.isfaaghyth.rak.Rak;

public class FormPembayaranActivity extends AppCompatActivity {

    TextView txtInvoice, txtSaldo;
//    public TopUp topUps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaksi_murid);

        txtInvoice = (TextView) findViewById(R.id.txtinvoice);
        txtSaldo = (TextView) findViewById(R.id.txttotal);

        txtInvoice.setText((String) Rak.grab("nomor_invoice"));
        txtSaldo.setText(String.format("Rp. %d", (Integer) Rak.grab("total")));

        boolean isBayar = Rak.grab("bayar");
        if (isBayar) { //button ok nya di sembunyiin klau dah bayar
            findViewById(R.id.btnOkmrd).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.btnOkmrd).setVisibility(View.GONE);
        }
    }

}
