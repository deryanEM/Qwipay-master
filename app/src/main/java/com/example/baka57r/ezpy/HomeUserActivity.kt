package com.example.baka57r.ezpy

//import com.example.baka57r.ezpy.adapter.ViewNotifAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.AdapterView
import android.widget.Toast
import com.example.baka57r.ezpy.adapter.ViewNotifAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class HomeUserActivity : AppCompatActivity() {

    internal var images = intArrayOf(R.drawable.iconscan, R.drawable.icon_top_up,
            R.drawable.iconriwayat, R.drawable.icon_account)

    internal var title = arrayOf("Scan", "Isi saldo", "Riwayat", "QID")
    internal lateinit var value1: String
    internal lateinit var value3: String
    internal lateinit var value2: String
    internal lateinit var value4: String
    internal lateinit var value5: String

    internal lateinit var hasil1: String
    internal lateinit var hasil2: String
    internal lateinit var hasil3: String
    internal lateinit var hasil4: String
    internal lateinit var hasil5: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras

        value1 = bundle!!.getString("param1")
        value2 = bundle.getString("param2")
        value3 = bundle.getString("param3")
        value4 = bundle.getString("param4")
        value5 = bundle.getString("param5")

        notif_button.setOnClickListener {
            intent = Intent(this, BuyerTransactionActivity::class.java)
            intent.putExtra("param1", value1)
            intent.putExtra("param2", value2)
            intent.putExtra("param3", value3)
            intent.putExtra("param4", value4)
            intent.putExtra("param5", value5)
            startActivity(intent)
        }

        semua.setOnClickListener {
            intent = Intent(this, BuyerTransactionActivity::class.java)
            intent.putExtra("param1", value1)
            intent.putExtra("param2", value2)
            intent.putExtra("param3", value3)
            intent.putExtra("param4", value4)
            intent.putExtra("param5", value5)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder().baseUrl(SaldoApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(SaldoApi::class.java)

        val call = api.getSaldo(value4, "Bearer $value2")
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {

                    try {
                        val jsonRes = JSONObject(response.body()!!.string())

                        hasil1 = jsonRes.getString("_id")
                        hasil2 = jsonRes.getString("email")
                        hasil3 = jsonRes.getString("name")
                        hasil4 = jsonRes.getString("jumlah_uang")
                        hasil5 = jsonRes.getString("__v")

                        user_name.setText(hasil3)
                        user_saldo.setText(hasil4)

                    } catch (e: JSONException) {
                        val tost1 = Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG)
                        tost1.show()
                        e.printStackTrace()
                    } catch (e: IOException) {
                        val tost2 = Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG)
                        tost2.show()
                        e.printStackTrace()
                    }

                } else {
                    //loading.dismiss();
                    val tostt = Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG)
                    tostt.show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val tost = Toast.makeText(applicationContext, "Please check your connection", Toast.LENGTH_LONG)
                tost.show()
            }
        })

        val adapter = HomeAdapter(this, title, images)
        gridviewHome.adapter = adapter

        gridviewHome.setOnItemClickListener(AdapterView.OnItemClickListener { parent, v, position, id -> Toast.makeText(this@HomeUserActivity, "Image Position: $position", Toast.LENGTH_SHORT).show() })

        gridviewHome.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent: Intent
            when (position) {
                0 -> {
                    intent = Intent(this, ScanPembeli::class.java)
                    intent.putExtra("param1", value1)
                    intent.putExtra("param2", value2)
                    intent.putExtra("param3", value3)
                    intent.putExtra("param4", value4)
                    intent.putExtra("param5", value5)
                    startActivity(intent)
                }
                1 -> {
                    intent = Intent(this, InputTopup::class.java)
                    intent.putExtra("param1", value1)
                    intent.putExtra("param2", value2)
                    intent.putExtra("param3", value3)
                    intent.putExtra("param4", value4)
                    intent.putExtra("param5", value5)
                    startActivity(intent)
                }
                2 -> {
                    intent = Intent(this, BuyerTransactionActivity::class.java)
                    intent.putExtra("param1", value1)
                    intent.putExtra("param2", value2)
                    intent.putExtra("param3", value3)
                    intent.putExtra("param4", value4)
                    intent.putExtra("param5", value5)
                    startActivity(intent)
                }
                else -> {
                    intent = Intent(this, Profil::class.java)
                    intent.putExtra("param1", value1)
                    intent.putExtra("param2", value2)
                    intent.putExtra("param3", value3)
                    intent.putExtra("param4", value4)
                    intent.putExtra("param5", value5)
                    startActivity(intent)
                }

            }
        }

        val retrofit2 = Retrofit.Builder().baseUrl(NotifLimitApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val apiLog = retrofit2.create(NotifLimitApi::class.java)
        val call_apilog = apiLog.getNotifLimit(value3, value4, "Bearer $value2")

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
                                 penjual = data.getString("penjual")
                                 message = "Pengeluaran"
                            }
//                            val message = data.getString("message")
                            val pembeli = data.getString("pembeli")
                            val nominal = data.getString("nominal")
                            val waktu = data.getString("waktu")

                            list.add(NotifModel(type, message, pembeli, penjual, nominal, waktu))


                        }
//                        Toast.makeText(this@HomeUserActivity, "api $list", Toast.LENGTH_LONG).show()


                        listview_transaction.setLayoutManager(LinearLayoutManager(this@HomeUserActivity))
                        listview_transaction.setAdapter(ViewNotifAdapter(this@HomeUserActivity,list))

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

}