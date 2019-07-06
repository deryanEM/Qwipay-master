//package com.example.baka57r.ezpy;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ListView;
////import android.widget.SearchView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.support.v7.widget.SearchView;
//
//
//import com.example.baka57r.ezpy.utils.TextView_Lato;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class LogPembeli extends AppCompatActivity {
//
//    ListView listview;
//    ArrayList<String> items;
//    String data1,data2,data3,data4,data5;
//    String hasil1,hasil2,hasil3,hasil4,hasil5,hasil6,hasil7,hasil8;
//    final String SHARED_PREFS = "sharedPrefs";
//    final String TEXT = "text";
//    final String SWITCH = "switch";
//    private Toolbar toolbar;
//    private ImageButton buttonFAQ;
//    private TextView_Lato tittle;
//
//    @SuppressLint("WrongViewCast")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_log_pembeli);
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar_ic);
//        tittle = (TextView_Lato) findViewById(R.id.titleSearch);
//        tittle.setText("Riwayat");
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
//
//        listview = findViewById(R.id.listview1);
//
//        items = new ArrayList<>();
//
//        Bundle bundle = getIntent().getExtras();
//        data1 = bundle.getString("param1"); //nama
//        data2 = bundle.getString("param2"); //token
//        data3 = bundle.getString("param3"); //role
//        data4 = bundle.getString("param4"); //email
//        data5 = bundle.getString("param5"); //id
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(TransaksiApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//
//        TransaksiApi api = retrofit.create(TransaksiApi.class);
//
//        Call<List<TransaksiEzpy>> call = api.getTransaksi(data1,"Bearer "+data2);
//
//        call.enqueue(new Callback<List<TransaksiEzpy>>() {
//            @Override
//            public void onResponse(Call<List<TransaksiEzpy>> call, Response<List<TransaksiEzpy>> response) {
//                List<TransaksiEzpy> transac = response.body();
//                String[] hasil1 = new String[transac.size()];
//                String[] hasil2 = new String[transac.size()];
//                String[] hasil3 = new String[transac.size()];
//                String[] hasil4 = new String[transac.size()];
//                String[] hasil5 = new String[transac.size()];
//                String[] hasil6 = new String[transac.size()];
//                String[] hasil7 = new String[transac.size()];
//                String[] hasil8 = new String[transac.size()];
//
//                for(int i = 0; i < (transac.size()); i++) {
//                    hasil1[i] = transac.get(i).get_id();
//                    hasil2[i] = transac.get(i).getPenjual();
//                    hasil3[i] = transac.get(i).getPembeli();
//                    hasil4[i] = transac.get(i).getTgl_transaksi();
//                    hasil5[i] = transac.get(i).getBulan_transaksi();
//                    hasil6[i] = transac.get(i).getTahun_transaksi();
//                    hasil7[i] = transac.get(i).getJumlah_transaksi();
//                    hasil8[i] = transac.get(i).get__v();
//                    items.add(hasil4[i]+"-"+hasil5[i]+"-"+hasil6[i]+" Saya : "+hasil3[i]+" Beli ke : "+hasil2[i]+", Sebesar : Rp "+hasil7[i]);
//                }
//
//                listview.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.tulisanwhite,items));
//            }
//
//            @Override
//            public void onFailure(Call<List<TransaksiEzpy>> call, Throwable t) {
//
//            }
//        });
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.tulisanwhite,items);
//        listview.setAdapter(adapter);
//
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                String text = listview.getItemAtPosition(i).toString();
//                Toast.makeText(LogPembeli.this,text, Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }
//
//    @Override
//    public void onBackPressed()
//    {
//        Intent dashboard = new Intent(LogPembeli.this, HomeUserActivity.class);
//        dashboard.putExtra("param1", data1);
//        dashboard.putExtra("param2", data2);
//        setResult(RESULT_OK, dashboard);
//        finish();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu,menu);
//
//
//        MenuItem searchItem = menu.findItem(R.id.item_search_log_penjual);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        if(searchView!=null)
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                ArrayList<String> templist = new ArrayList<>();
//                for(String temp:items)
//                {
//                    if(temp.toLowerCase().contains(s.toLowerCase()))
//                    {
//                        templist.add(temp);
//                    }
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LogPembeli.this,R.layout.tulisanwhite,templist);
//                listview.setAdapter(adapter);
//                return true;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        Intent dashboard = new Intent(LogPembeli.this,HomeUserActivity.class) ;
//        dashboard.putExtra("param1", data1);
//        dashboard.putExtra("param2", data2);
//        dashboard.putExtra("param3", data3);
//        dashboard.putExtra("param4", data4);
//        dashboard.putExtra("param5", data5);
//        startActivity(dashboard);
//        return true;
//    }
//
//
//
//
//}
