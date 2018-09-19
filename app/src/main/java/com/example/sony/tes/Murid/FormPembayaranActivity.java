package com.example.sony.tes.Murid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sony.tes.R;

import io.isfaaghyth.rak.Rak;

public class FormPembayaranActivity extends AppCompatActivity {

    TextView txtInvoice, txtSaldo;
//    public TopUp topUps;
    Button btnok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaksi_murid);
        Rak.initialize(this);

        btnok = (Button) findViewById(R.id.btnOkmrd);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rak.entry("bayar", false);
                Rak.entry("nomor_invoice", Rak.grab("nomor_invoice"));
                Intent i = new Intent(FormPembayaranActivity.this, BayarMuridActivity.class);
                startActivity(i);
            }
        });
        txtInvoice = (TextView) findViewById(R.id.txtinvoice);
        txtSaldo = (TextView) findViewById(R.id.txttotal);

        txtInvoice.setText((String) Rak.grab("nomor_invoice"));
        txtSaldo.setText(String.format("Rp. %d", (Integer) Rak.grab("total")));
    }
}
