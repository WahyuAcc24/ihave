package com.example.sony.tes.Guru;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sony.tes.Murid.BayarMuridActivity;
import com.example.sony.tes.Murid.HistoryMuridActivity;
import com.example.sony.tes.Murid.HomeMuridActivity;
import com.example.sony.tes.Murid.SettingMuridActivity;
import com.example.sony.tes.Murid.TransaksiMuridActivity;
import com.example.sony.tes.R;

/**
 * Created by SONY on 24/7/2018.
 */
public class DetailGuruActivity extends AppCompatActivity {

    Button sen9, sen10, sen11, sen12, sen13, sen14, sen15, sen16, sen17, sen18, sen19, sen20, masuk;
    AlertDialog ad;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    Toolbar toolbar;


    ImageView imageView, home, history, setting, transaksi;
    public static String urlGambar = "http://hardrockfm.com/wp-content/uploads/2016/06/raisa.jpg";
//
//    static String[][] spaceProbes={
//            {"SENIN","9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"},
//            {"SELASA","9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"},
//            {"RABU","9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"},
//            {"KAMIS","9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"},
//            {"JUMAT","9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"},
//            {"SABTU","9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"},
//            {"MINGGU","9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"},
//        };
//
//    static String[] spaceProbeHeaders={"Hari","9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_detailguru);

        touch();

        home = (ImageView) findViewById(R.id.imgMenuHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailGuruActivity.this, HomeMuridActivity.class);
                startActivity(i);
            }
        });

        history = (ImageView) findViewById(R.id.imgMenuHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailGuruActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailGuruActivity.this, SettingMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailGuruActivity.this, TransaksiMuridActivity.class);
                startActivity(i);
            }
        });
        imageView = (ImageView) findViewById(R.id.imgGuru);
        Glide.with(DetailGuruActivity.this)
                .load(urlGambar)
                .placeholder(R.drawable.clock_loading)
                .into(imageView);

        //  tombol back
        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        masuk = (Button) findViewById(R.id.btnOK);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });


    }

    private void showAlert(){

        dialog = new AlertDialog.Builder(DetailGuruActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_pembayaran, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(DetailGuruActivity.this, BayarMuridActivity.class);
                startActivity(i);
            }
        });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }



    public void touch() {
        sen9 = (Button) findViewById(R.id.senin9);
        sen9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen9.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen9.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen10 = (Button) findViewById(R.id.senin10);
        sen10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen10.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen10.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen11 = (Button) findViewById(R.id.senin11);
        sen11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen11.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen11.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen12 = (Button) findViewById(R.id.senin12);
        sen12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen12.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen12.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen13 = (Button) findViewById(R.id.senin13);
        sen13.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen13.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen13.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen14 = (Button) findViewById(R.id.senin14);
        sen14.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen14.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen14.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen15 = (Button) findViewById(R.id.senin15);
        sen15.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen15.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen15.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen16 = (Button) findViewById(R.id.senin16);
        sen16.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen16.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen16.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen17 = (Button) findViewById(R.id.senin17);
        sen17.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen17.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen17.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen18 = (Button) findViewById(R.id.senin18);
        sen18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen18.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen18.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen19 = (Button) findViewById(R.id.senin19);
        sen19.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen19.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen19.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

        sen20 = (Button) findViewById(R.id.senin20);
        sen20.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sen20.setBackground(getDrawable(R.drawable.button_bg));

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    sen20.setBackground(getDrawable(R.drawable.button_default));
                }

                return false;
            }
        });

    }
}
//    private void showAlert() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                this);
//
//        alertDialogBuilder.setTitle("Pembayaran");
//        alertDialogBuilder.setTitle("3 Agustus 2018");
//
//        alertDialogBuilder.setMessage("09:00 - 10:00")
//                .setMessage("Rp. 0")
//                .setMessage("Waktu1 jam")
//                .setCancelable(false)
//                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent i = new Intent(DetailGuruActivity.this, LoginMuridActivity.class);
//                        startActivity(i);
//                    }
//                })
//                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }



