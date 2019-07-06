package com.example.baka57r.ezpy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.baka57r.ezpy.utils.TextView_Lato;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InputTopup extends AppCompatActivity {

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

        toolbar = (Toolbar) findViewById(R.id.toolbar_ic);
        tittle = (TextView_Lato) findViewById(R.id.titleSearch);
        tittle.setText("Top Up");
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

        editText = (EditText)findViewById(R.id.TextInputTopup);

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

        Retrofit retrofit = new Retrofit.Builder().baseUrl(TopUp.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        TopUp api = retrofit.create(TopUp.class);

        String nominal = editText.getText().toString();

        Call<ResponseBody> call = api.getTopup(data4, nominal,"Bearer "+data2);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Toast tost = Toast.makeText(getApplicationContext(),"success :" +response ,Toast.LENGTH_LONG);
//                tost.show();
                if(response.isSuccessful()){
                    //loading.dismiss();

                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast tost = Toast.makeText(getApplicationContext(),"Please check your connection" ,Toast.LENGTH_LONG);
                tost.show();
            }

        });

        Intent dashboard = new Intent(InputTopup.this,HomeUserActivity.class) ;
        dashboard.putExtra("param1", data1);
        dashboard.putExtra("param2", data2);
        dashboard.putExtra("param3", data3);
        dashboard.putExtra("param4", data4);
        dashboard.putExtra("param5", data5);
        startActivity(dashboard);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent dashboard = new Intent(InputTopup.this,HomeUserActivity.class) ;
        dashboard.putExtra("param1", data1);
        dashboard.putExtra("param2", data2);
        dashboard.putExtra("param3", data3);
        dashboard.putExtra("param4", data4);
        dashboard.putExtra("param5", data5);
        startActivity(dashboard);
        return true;
    }

}
