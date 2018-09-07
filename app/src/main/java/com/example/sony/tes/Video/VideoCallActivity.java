package com.example.sony.tes.Video;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.sony.tes.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.vidyo.VidyoClient.Connector.Connector;
import com.vidyo.VidyoClient.Connector.ConnectorPkg;


public class VideoCallActivity extends AppCompatActivity implements Connector.IConnect {

    private Connector vc;
    private FrameLayout videoFrame;
    Button start,konek,gakKonek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(listener)
                .check();

        setContentView(R.layout.video_call);

        ConnectorPkg.setApplicationUIContext(this);
        ConnectorPkg.initialize();
        videoFrame = (FrameLayout) findViewById(R.id.videoFrameCall);



        start = (Button) findViewById(R.id.btnStart);
        konek = (Button) findViewById(R.id.btnKonek);
        gakKonek = (Button) findViewById(R.id.btnDiskonek);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 15, "", "", 0);
                vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());

            }


        });

        konek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = "cHJvdmlzaW9uAGloYXZlQGY1MTRlNC52aWR5by5pbwA2MzcwMTMwMDc4MwAANjdkYTg5N2QwYWRkZmMxMGM2MzljNDc0MDA2ODQ5Mzg2OTI2MjYxZTliMGUzNWRlYjY3ZTNhZGJhZTIwM2UzOGViMjBmOTJkZDZmNzA1NDk4OWZmOWRmZTA4OTgxMzRm";
                vc.connect("prod.vidyo.io", token, "DemoUser", "DemoRoom", VideoCallActivity.this);

            }
        });

        gakKonek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vc.disconnect();
            }
        });

    }



// LISTERNER BUAT DEXTER
    PermissionListener listener = new PermissionListener() {
        @Override
        public void onPermissionGranted(PermissionGrantedResponse response) {

        }

        @Override
        public void onPermissionDenied(PermissionDeniedResponse response) {

        }

        @Override
        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
            token.continuePermissionRequest();
        }
    };



    public void onSuccess() {}

    public void onFailure(Connector.ConnectorFailReason reason) {}

    public void onDisconnected(Connector.ConnectorDisconnectReason reason) {}
}
