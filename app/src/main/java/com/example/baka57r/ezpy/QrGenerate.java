package com.example.baka57r.ezpy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.baka57r.ezpy.utils.TextView_Lato;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrGenerate extends AppCompatActivity {

    String data1,data2,data3,data4,data5,data6;
    private Toolbar toolbar;
    private ImageButton buttonFAQ;
    private TextView_Lato tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generate);

        toolbar = (Toolbar) findViewById(R.id.toolbar_ic);
        tittle = (TextView_Lato) findViewById(R.id.titleSearch);
        tittle.setText("Buat Kode QR");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();

        data1 = bundle.getString("param1");
        data2 = bundle.getString("param2");
        data3 = bundle.getString("param3");
        data4 = bundle.getString("param4");
        data5 = bundle.getString("param5");
        data6 = bundle.getString("param6");

        String gabung = data4 + " " + data6;

        ImageView imageView = (ImageView) findViewById(R.id.qrnya);

        if (data6 != null) {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(gabung, BarcodeFormat.QR_CODE, 400, 400);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageView.setImageBitmap(bitmap);
            } catch (WriterException r) {
                r.printStackTrace();
            }
        }

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent dashboard = new Intent(QrGenerate.this,HomePenjualActivity.class) ;
        dashboard.putExtra("param1", data1);
        dashboard.putExtra("param2", data2);
        dashboard.putExtra("param3", data3);
        dashboard.putExtra("param4", data4);
        dashboard.putExtra("param5", data5);
        startActivity(dashboard);
        return true;

    }

    public void kembalikedash(View v)
    {
        Intent dashboard = new Intent(QrGenerate.this,HomePenjualActivity.class) ;
//        dashboard.putExtra("param1",data1);
//        dashboard.putExtra("param2",data2);
        startActivity(dashboard);
    }
}
