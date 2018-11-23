package com.example.sony.tes.Guru;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sony.tes.Model.DataPart;
import com.example.sony.tes.Murid.AppController;
import com.example.sony.tes.R;
import com.example.sony.tes.Server.server;
import com.example.sony.tes.util.VolleyMultipartRequest;
import com.vidyo.VidyoClient.Connector.Connector;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 7/8/2018.
 */
public class SettingGuruActivity extends AppCompatActivity {

    EditText txt_email, txt_pass, txt_nama, txt_hp, txt_jk, txt_tempat, txt_tgllahir;

    private Connector cn;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    Button daftar, ubahFoto, edit;
    ProgressDialog pDialog;
    String emailS, passwordS;
    ImageView user;
    Intent intent;
    Bitmap bitmap, decoded;


    Uri fileUri;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    int max_resolution_image = 800;
    ConnectivityManager conMgr;

    private String url = server.URL + "guru/update";
    private static final String TAG = SettingGuruActivity.class.getSimpleName();

    public final static String TAG_EMAIL = "email";
    public final static String TAG_PASSWORD = "password";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    public void showDate() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                txt_tgllahir.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_guru);

        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit = (Button) findViewById(R.id.btnLengkapi);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingGuruActivity.this, LengkapiDataGuruActivity.class);
                startActivity(i);
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), " Silahkan Cek Lagi Koneksi Anda ",
                        Toast.LENGTH_LONG).show();
            }
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        daftar = (Button) findViewById(R.id.buttonDaftarEdtGuru);
        ubahFoto = (Button) findViewById(R.id.ubahFotoProfilEdtGuru);
        txt_email = (EditText) findViewById(R.id.txtEmailEdtGuru);
        txt_hp = (EditText) findViewById(R.id.txtPhoneEdtGuru);
        txt_nama = (EditText) findViewById(R.id.txtUsernameEdtGuru);
        txt_jk = (EditText) findViewById(R.id.txtPilihJkEdtGuru);
        txt_pass = (EditText) findViewById(R.id.txtPasswordEdtGuru);
        txt_tempat = (EditText) findViewById(R.id.txtBirthplaceEdtGuru);
        txt_tgllahir = (EditText) findViewById(R.id.txtBirthdayEdtGuru);
        user = (ImageView) findViewById(R.id.imgUserEdtGuru);


        txt_nama.setText((String) Rak.grab("fullname"));
        txt_email.setText((String) Rak.grab("email"));
        txt_tgllahir.setText((String) Rak.grab("birthdate"));
        txt_tempat.setText((String) Rak.grab("birthplace"));
        txt_hp.setText((String) Rak.grab("phone"));

        ubahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilGaleri();
            }
        });

        txt_tgllahir.setFocusable(false);
        txt_tgllahir.setClickable(true);

        txt_tgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Id = Rak.grab("id_guru");
                String email = txt_email.getText().toString();
                String password = txt_pass.getText().toString();
                String fullname = txt_nama.getText().toString();
                String Hp = txt_hp.getText().toString();
                String jk = txt_jk.getText().toString();
                String TmptLahir = txt_tempat.getText().toString();
                String tglLahir = txt_tgllahir.getText().toString();

                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        edit(Id, email, password, fullname, Hp, jk, TmptLahir, tglLahir);

                    } else {
                        Toast.makeText(getApplicationContext(), "cek internet anda", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    public void tampilGaleri() {

        user.setImageResource(0);
        final CharSequence[] items = {"Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingGuruActivity.this);
        builder.setTitle("Add Photo!");
        builder.setIcon(R.mipmap.logo_ihave);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Choose from Library")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
                    bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    Log.e("TAG", e.getMessage());
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(SettingGuruActivity.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Untuk menampilkan bitmap pada ImageView
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        user.setImageBitmap(decoded);
    }

    // Untuk resize bitmap
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_DeKa_" + timeStamp + ".jpg");

        return mediaFile;
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void edit(final String idguru, final String email, final String password, final String fullname, final String Hp, final String TmptLahir, final String jk, final String tglLahir) {

        VolleyMultipartRequest req = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Log.d("TAG", response.toString());
                try {
                    Rak.entry("email", email);
                    Rak.entry("password", password);
                    Rak.entry("fullname", fullname);
                    Rak.entry("birthdate", tglLahir);
                    Rak.entry("birthplace", TmptLahir);
                    Rak.entry("phone", Hp);
                    Rak.entry("gender", jk);

                    txt_email.setText("");
                    txt_nama.setText("");
                    txt_pass.setText("");
                    txt_tempat.setText("");
                    txt_hp.setText("");
                    txt_jk.setText("");
                    txt_tgllahir.setText("");


                    Log.d("TAG", response.toString());
                    hideDialog();
                    Toast.makeText(getApplicationContext(), "Berhasil Ubah Porfil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SettingGuruActivity.this, HomeGuruActivity.class));
                    finish();

                } catch (Exception e) {
                    Log.e("haha", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", idguru);
                params.put("email", email);
                params.put("password", password);
                params.put("fullname", fullname);
                params.put("birthdate", tglLahir);
                params.put("birthplace", TmptLahir);
                params.put("phone", Hp);
                params.put("gender", jk);
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                if (bitmap != null) {
                    params.put("images", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                }
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(req, tag_json_obj);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}


//        home = (ImageView) findViewById(R.id.imgMenuHome);
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SettingGuruActivity.this, HomeGuruActivity.class);
//                startActivity(i);
//            }
//        });
//
//        History = (ImageView) findViewById(R.id.imgMenuHistory);
//        History.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SettingGuruActivity.this, HistoryGuruActivity.class);
//                startActivity(i);
//            }
//        });



