package com.example.sony.tes.Video;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.sony.tes.R;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by SONY on 14/8/2018.
 */
public class VideoChatActivity extends AppCompatActivity implements Session.SessionListener, PublisherKit.PublisherListener {

    private static String API_KEY = "46171282";
    private static String SESSION_ID = "2_MX40NjE3MTI4Mn5-MTU0MzQ2NjMzMzQ1Mn5BSkFoSlp6WDdVZ0NhUDlPUlJoM0ZQN0V-fg";
    private static String TOKEN = "T1==cGFydG5lcl9pZD00NjE3MTI4MiZzaWc9ODhiNTk1MjQxYzIwYWE4MjdmZjQxMTMwZjc4ZDQwNGNhMzMwNGE1NjpzZXNzaW9uX2lkPTJfTVg0ME5qRTNNVEk0TW41LU1UVTBNelEyTmpNek16UTFNbjVCU2tGb1NscDZXRGRWWjBOaFVEbFBVbEpvTTBaUU4wVi1mZyZjcmVhdGVfdGltZT0xNTQzNDY2MzY2Jm5vbmNlPTAuNzU3NjM3NzI0MTUzNzMwOSZyb2xlPXB1Ymxpc2hlciZleHBpcmVfdGltZT0xNTQ2MDU4MzY0JmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9";
    private static final String LOG_TAG = VideoChatActivity.class.getSimpleName();
    private static final int RC_SETTINGS_SCREEN_PERM = 123;
    private static final int RC_VIDEO_APP_PERM = 124;
    private Session session;

    private Publisher publisher;
    private Subscriber subscriber;
    private FrameLayout pb, sb;


    private boolean isFlashOn;
    private boolean hasFlash;
    private Camera camera;
    Camera.Parameters params;

    ImageView flash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        requestPermissions();

        pb = (FrameLayout) findViewById(R.id.publisher_container);
        sb = (FrameLayout) findViewById(R.id.subscribe_container);
        flash = (ImageView) findViewById(R.id.flashOn);


        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            // device doesn't support flash
            // Show alert message and close the application
            AlertDialog alert = new AlertDialog.Builder(VideoChatActivity.this)
                    .create();
            alert.setTitle("Error");
            alert.setMessage("Sorry, your device doesn't support flash light!");
            alert.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                }
            });
            alert.show();
            return;
        }

        toggleButtonImage();


        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashOn) {
                    // turn off flash
                    turnOffFlash();
                } else {
                    // turn on flash
                    turnOnFlash();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @AfterPermissionGranted(RC_SETTINGS_SCREEN_PERM)
    private void requestPermissions() {
        String[] perm = {Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if (EasyPermissions.hasPermissions(this, perm)) {

            session = new Session.Builder(this, API_KEY, SESSION_ID).build();
            session.setSessionListener(this);
            session.connect(TOKEN);

        } else {
            EasyPermissions.requestPermissions(this, "Aplikasi ini butuh mengakses kamera Anda", RC_SETTINGS_SCREEN_PERM, perm);
        }

    }

    @Override
    public void onConnected(Session session) {

        publisher = new Publisher.Builder(this).build();
        publisher.setPublisherListener(this);

        pb.addView(publisher.getView());
        session.publish(publisher);

        toggleButtonImage();


        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashOn) {
                    // turn off flash
                    turnOffFlash();
                } else {
                    // turn on flash
                    turnOnFlash();
                }
            }
        });



    }

    @Override
    public void onDisconnected(Session session) {


    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {

        if (subscriber == null) {
            subscriber = new Subscriber.Builder(this, stream).build();

            sb.addView(subscriber.getView());
            session.subscribe(subscriber);

            toggleButtonImage();


            flash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFlashOn) {
                        // turn off flash
                        turnOffFlash();
                    } else {
                        // turn on flash
                        turnOnFlash();
                    }
                }
            });



        }

    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {

        if (subscriber != null) {
            subscriber = null;
            sb.removeAllViews();


        }

    }

//    public void ReleaseCamera(){
//        if (publisher != null) {
//            publisher.setPublishVideo(false);
//
//            BaseVideoCapturer bvc = publisher.getCapturer();
//            if (bvc != null) {
//                bvc.stopCapture();
//                bvc.destroy();
//            }
//        }
//    }


    @Override
    public void onError(Session session, OpentokError opentokError) {

    }

    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {

    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {

    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {

    }

    public void onBackPressed() {
        finish();
    }

    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;

            // changing button/switch image
            toggleButtonImage();
        }
    }

    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;

            // changing button/switch image
            toggleButtonImage();
        }

    }

    private void toggleButtonImage() {
        if (isFlashOn) {
            flash.setImageResource(R.drawable.flashon);
        } else {
            flash.setImageResource(R.drawable.flash);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // on pause turn off the flash
        turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // on resume turn on the flash
        if(hasFlash)
            turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params
//        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // on stop release the camera
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

}
//if(subscriber !=null){
//        subscriber = null;
//        sb.removeAllViews();
//        }
