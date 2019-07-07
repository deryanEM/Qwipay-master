package com.example.baka57r.ezpy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baka57r.ezpy.utils.TextView_Lato;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScanPembeli extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    String data1,data2,data3,data4,data5;
    String hasil1,hasil2,hasil3,hasil4,hasil5,hasil6,hasil7,hasil8;
    private Toolbar toolbar;
    private TextView_Lato tittle;
    private ImageButton buttonFAQ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_pembeli);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        toolbar = (Toolbar) findViewById(R.id.toolbar_ic);
        tittle = (TextView_Lato) findViewById(R.id.titleSearch);
        tittle.setText("Scan QR");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        buttonFAQ = (ImageButton) findViewById(R.id.faq_button);
        buttonFAQ.setVisibility(View.GONE);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            toolbar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.headerkecil) );
        } else {
            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.headerkecil));
        }

        Bundle bundle = getIntent().getExtras();
        data1 = bundle.getString("param1"); //nama
        data2 = bundle.getString("param2"); //token
        data3 = bundle.getString("param3"); //role
        data4 = bundle.getString("param4"); //email
        data5 = bundle.getString("param5"); //id

        surfaceView = (SurfaceView) findViewById(R.id.kamera1);
        textView = (TextView) findViewById(R.id.text1);

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).build();

        if(ContextCompat.checkSelfPermission(ScanPembeli.this, Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED)
        {

        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    Toast.makeText(ScanPembeli.this, "PERLU BUAT SAVE IMAGE", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(ScanPembeli.this, Manifest.permission.CAMERA)) {
                    Toast.makeText(ScanPembeli.this, "PERLU BUAT SAVE IMAGE", Toast.LENGTH_SHORT).show();

                }
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
            else {
                ActivityCompat.requestPermissions(ScanPembeli.this, new String[]{Manifest.permission.CAMERA}, 1);

            }
        }


        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                try {
                    cameraSource.start(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size()!=0)
                {

                    textView.post(new Runnable() {
                        @Override
                        public void run() {

                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            textView.setText(qrCodes.valueAt(0).displayValue);
                            String test = qrCodes.valueAt(0).displayValue.toString();
                            final String[] splitStr = test.split("\\s+");
                            cameraSource.stop();
                            String dataPenjual = splitStr[0];
                            String dataNominal = splitStr[1];
                            Intent intent = new Intent(ScanPembeli.this,PayConfirmActivity.class) ;
                            intent.putExtra("dataPenjual", dataPenjual);
                            intent.putExtra("dataNominal", dataNominal);
                            intent.putExtra("dataPembeli", data1);
                            intent.putExtra("param1", data1);
                            intent.putExtra("param2", data2);
                            intent.putExtra("param3", data3);
                            intent.putExtra("param4", data4);
                            intent.putExtra("param5", data5);
                            startActivity(intent);

//                            AlertDialog.Builder alert1 = new AlertDialog.Builder(ScanPembeli.this);
//                            alert1.setMessage("Penjual "+splitStr[0]+" Nominal "+splitStr[1]+"?").setCancelable(false)
//                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i)
//                                        {
//
//                                        }
//                                    })
//                                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i)
//                                        {
//                                            dialogInterface.cancel();
//                                            try {
//                                                cameraSource.start(surfaceView.getHolder());
//                                                textView.setText("SCAN LAGI !!!");
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
//
//                                        }
//                                    });
//                            AlertDialog alert = alert1.create();
//                            alert.setTitle("KONFIRMASI");
//                            alert.show();



                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent dashboard = new Intent(ScanPembeli.this,HomeUserActivity.class) ;
        dashboard.putExtra("param1", data1);
        dashboard.putExtra("param2", data2);
        dashboard.putExtra("param3", data3);
        dashboard.putExtra("param4", data4);
        dashboard.putExtra("param5", data5);
        startActivity(dashboard);
        return true;

    }

}