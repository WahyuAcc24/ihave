package com.example.sony.tes.Murid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sony.tes.R;

import io.isfaaghyth.rak.Rak;

public class FormPembayaranActivity extends AppCompatActivity {

    TextView txtInvoice, txtSaldo, txttgl;
//    public TopUp topUps;
    Button btnok, btnatm, btnibangking, btnteler, btnmb;

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

        btnatm = (Button) findViewById(R.id.btnAtm);
        btnatm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(FormPembayaranActivity.this)
                        .setTitle(R.string.Melalui_Atm)
                        .setMessage("Transfer -> Masukan Jumlah Pembayaran -> Masukan No rek IHave -> Konfirmasi")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });

        btnibangking = (Button) findViewById(R.id.btniBangking);
        btnibangking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(FormPembayaranActivity.this)
                        .setTitle(R.string.Internet_Bangking)
                        .setMessage("Login -> Pilih Transfer Dana -> Klik Transfer Ker Rek Bca dan isi data -> Klik Lanjutkan -> Kirim")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });

        btnteler = (Button) findViewById(R.id.btnTeller);
        btnteler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(FormPembayaranActivity.this)
                        .setTitle(R.string.Teller)
                        .setMessage("Isi formulir slip transfer Bca -> Menyerahkan formulir Slip dan uang ke Teller")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });

        btnmb = (Button) findViewById(R.id.btnMb);
        btnmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(FormPembayaranActivity.this)
                        .setTitle(R.string.Mb)
                        .setMessage("M-bca > m-transfer > Daftar Transfer(Antar Rekening) > Transfer(Antar Rekening) > Isi form > send")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });



        txtInvoice = (TextView) findViewById(R.id.txtinvoice);
        txtSaldo = (TextView) findViewById(R.id.txttotal);
        txttgl = (TextView) findViewById(R.id.txttgl);

        txtInvoice.setText((String) Rak.grab("nomor_invoice"));
        txtSaldo.setText(String.format("Rp. %d", (Integer) Rak.grab("total")));
        txttgl.setText(Rak.grab("due_date") + "WIB");
    }
    @Override
    public void onBackPressed() {
        finish();
    }


}
