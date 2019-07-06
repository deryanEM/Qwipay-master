package com.example.baka57r.ezpy

//import android.widget.SearchView;


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.example.baka57r.ezpy.adapter.ViewNotifAdapter
import com.example.baka57r.ezpy.utils.TextView_Lato
import kotlinx.android.synthetic.main.activity_home.*
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*

class SellerTransactionActivty : AppCompatActivity() {

    internal var data1: String? = null
    internal var data2: String? = null
    internal var data3: String? = null
    internal var data4: String? = null
    internal var data5: String? = null
    internal var hasil1: String? = null
    internal var hasil2: String? = null
    internal var hasil3: String? = null
    internal var hasil4: String? = null
    internal var hasil5: String? = null
    internal var hasil6: String? = null
    internal var hasil7: String? = null
    internal var hasil8: String? = null
    internal val SHARED_PREFS = "sharedPrefs"
    internal val TEXT = "text"
    internal val SWITCH = "switch"
    private var toolbar: Toolbar? = null
    private var buttonFAQ: ImageButton? = null
    private var tittle: TextView_Lato? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_pembeli)

        toolbar = findViewById<View>(R.id.toolbar_ic) as Toolbar
        tittle = findViewById<View>(R.id.titleSearch) as TextView_Lato
        tittle!!.text = "Riwayat"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        buttonFAQ = findViewById<View>(R.id.faq_button) as ImageButton
        buttonFAQ!!.visibility = View.GONE

        val sdk = android.os.Build.VERSION.SDK_INT
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            toolbar!!.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.headerkecil))
        } else {
            toolbar!!.background = ContextCompat.getDrawable(this, R.drawable.headerkecil)
        }

        val bundle = intent.extras
        data1 = bundle!!.getString("param1") //nama
        data2 = bundle.getString("param2") //token
        data3 = bundle.getString("param3") //role
        data4 = bundle.getString("param4") //email
        data5 = bundle.getString("param5") //id

        val retrofit2 = Retrofit.Builder().baseUrl(NotifApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val apiLog = retrofit2.create(NotifApi::class.java)
        val call_apilog = apiLog.getNotif(data3, data4, "Bearer $data2")

        call_apilog.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response!!.isSuccessful) {
//                    val tost40 = Toast.makeText(applicationContext, response.body()!!.string(), Toast.LENGTH_LONG)
//                    tost40.show()
                    try {
//                        val tost40 = Toast.makeText(applicationContext, response.body()!!.string(), Toast.LENGTH_LONG)
//                    tost40.show()
                        val jsonarray = JSONArray(response.body()!!.string())
//                        val jo = JSONObject()
                        val list = ArrayList<NotifModel>()
                        for (i in 0 until jsonarray.length()) {
                            val jsonobject = jsonarray.getJSONObject(i)
                            val type = jsonobject.getString("type")
                            val data = jsonobject.getJSONObject("content")
                            var penjual : String = ""
                            var message : String = ""
                            if(type == "Top Up"){
                                penjual = "Top Up"
                                message = "Pemasukan"
                            } else {
                                penjual = data.getString("pembeli")
                                message = "Pemasukan"
                            }
//                            val message = data.getString("message")
                            val pembeli = data.getString("penjual")
                            val nominal = data.getString("nominal")
                            val waktu = data.getString("waktu")

                            list.add(NotifModel(type, message, pembeli, penjual, nominal, waktu))


                        }
                        //Toast.makeText(this@SellerTransactionActivty, "api $list", Toast.LENGTH_LONG).show()


                        listview_transaction.setLayoutManager(LinearLayoutManager(this@SellerTransactionActivty))
                        listview_transaction.setAdapter(ViewNotifAdapter(this@SellerTransactionActivty,list))

                    }  catch (e: IOException) {
                        val tost2 = Toast.makeText(applicationContext, "Error2", Toast.LENGTH_LONG)
                        tost2.show()
                        e.printStackTrace()
                    }

                } else {
                    //loading.dismiss();
                    val tostt = Toast.makeText(applicationContext, "Error3", Toast.LENGTH_LONG)
                    tostt.show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                t!!.printStackTrace()
                val tost = Toast.makeText(applicationContext, "Please check your connection", Toast.LENGTH_LONG)
                tost.show()
            }

        })
    }

    override fun onBackPressed() {
        val dashboard = Intent(this@SellerTransactionActivty, HomePenjualActivity::class.java)
        dashboard.putExtra("param1", data1)
        dashboard.putExtra("param2", data2)
//        setResult(this@BuyerTransactionActivity.RESULT_OK, dashboard)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val dashboard = Intent(this@SellerTransactionActivty, HomePenjualActivity::class.java)
        dashboard.putExtra("param1", data1)
        dashboard.putExtra("param2", data2)
        dashboard.putExtra("param3", data3)
        dashboard.putExtra("param4", data4)
        dashboard.putExtra("param5", data5)
        startActivity(dashboard)
        return true
    }


}
