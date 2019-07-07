package com.example.baka57r.ezpy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.baka57r.ezpy.utils.TextView_Lato;

public class TopUpPenjual extends AppCompatActivity {

    String angkatampung = "";
    String aaaa = "";
    EditText editText;
    String data1,data2,data3,data4,data5;
    String value1, value2;
    private Toolbar toolbar;
    private ImageButton buttonFAQ;
    private TextView_Lato tittle;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_topup);

//        toolbar = (Toolbar) findViewById(R.id.toolbar_ic);
//        tittle = (TextView_Lato) findViewById(R.id.titleSearch);
//        tittle.setText("Top Up");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        buttonFAQ = (ImageButton) findViewById(R.id.faq_button);
//        buttonFAQ.setVisibility(View.GONE);
//
//        final int sdk = android.os.Build.VERSION.SDK_INT;
//        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            toolbar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.headerkecil) );
//        } else {
//            toolbar.setBackground(ContextCompat.getDrawable(this, R.drawable.headerkecil));
//        }


        Bundle bundle = getIntent().getExtras();

        editText = (EditText)findViewById(R.id.TextInputTopup);
        editText.requestFocus();

        data1 = bundle.getString("param1"); //nama
        data2 = bundle.getString("param2"); //token
        data3 = bundle.getString("param3"); //role
        data4 = bundle.getString("param4"); //email
        data5 = bundle.getString("param5"); //id
    }

    public void insatu(View v)
    {
        angkatampung = angkatampung+"1";
        editText.setText(angkatampung);
    }
    public void indua(View v)
    {
        angkatampung = angkatampung+"2";
        editText.setText(angkatampung);
    }
    public void intiga(View v)
    {
        angkatampung = angkatampung+"3";
        editText.setText(angkatampung);
    }
    public void inempat(View v)
    {
        angkatampung = angkatampung+"4";
        editText.setText(angkatampung);
    }
    public void inlima(View v)
    {
        angkatampung = angkatampung+"5";
        editText.setText(angkatampung);
    }
    public void inenam(View v)
    {
        angkatampung = angkatampung+"6";
        editText.setText(angkatampung);
    }
    public void intujuh(View v)
    {
        angkatampung = angkatampung+"7";
        editText.setText(angkatampung);
    }
    public void indelapan(View v)
    {
        angkatampung = angkatampung+"8";
        editText.setText(angkatampung);
    }
    public void insembilan(View v)
    {
        angkatampung = angkatampung+"9";
        editText.setText(angkatampung);
    }
    public void insepuluh(View v)
    {
        angkatampung = angkatampung+"0";
        editText.setText(angkatampung);
    }
    public void hps(View v)
    {
        if (angkatampung.length() >1 ) {
            angkatampung = angkatampung.substring(0, angkatampung.length() - 1);
            editText.setText(angkatampung);
        }
        else if (angkatampung.length() <=1 ) {
            angkatampung = "";
            editText.setText("");
        }
    }

    public void isi_topup(View v)
    {
        String nominal = editText.getText().toString();

        Intent dashboard = new Intent(TopUpPenjual.this,ConfirmPenjualActivity.class) ;
        dashboard.putExtra("param1", data1);
        dashboard.putExtra("param2", data2);
        dashboard.putExtra("param3", data3);
        dashboard.putExtra("param4", data4);
        dashboard.putExtra("param5", data5);
        dashboard.putExtra("dataNominal", nominal);
        startActivity(dashboard);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent dashboard = new Intent(TopUpPenjual.this,HomePenjualActivity.class) ;
        dashboard.putExtra("param1", data1);
        dashboard.putExtra("param2", data2);
        dashboard.putExtra("param3", data3);
        dashboard.putExtra("param4", data4);
        dashboard.putExtra("param5", data5);
        startActivity(dashboard);
        return true;
    }

}
