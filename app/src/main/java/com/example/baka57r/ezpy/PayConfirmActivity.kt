package com.example.baka57r.ezpy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pay_confirm.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PayConfirmActivity: AppCompatActivity() {

    internal var data1: String? = null
    internal var data2:String? = null
    internal var data3:String? = null
    internal var data4:String? = null
    internal var data5:String? = null
    internal var hasil1: String? = null
    internal var hasil2:String? = null
    internal var hasil3:String? = null
    internal var hasil4:String? = null
    internal var hasil5:String? = null
    internal var hasil6:String? = null
    internal var hasil7:String? = null
    internal var hasil8:String? = null

    internal var dataPenjual: String? = null
    internal var dataNominal: String? = null
    internal var dataPembeli: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_confirm)

        val bundle = intent.extras
        dataPenjual = bundle!!.getString("dataPenjual")
        dataNominal = bundle.getString("dataNominal")
        dataPembeli = bundle.getString("dataPembeli")
        data1 = bundle.getString("param1") //nama
        data2 = bundle.getString("param2") //token
        data3 = bundle.getString("param3") //role
        data4 = bundle.getString("param4") //email
        data5 = bundle.getString("param5") //id

        nominal.setText(dataNominal)
        nama_penjual.setText(dataPenjual)
        nama_pembeli.setText(dataPembeli)

        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val currentDateandTime = sdf.format(Date())
        datetime.setText(currentDateandTime)


        konfirmasi.setOnClickListener(){
            val retrofit = Retrofit.Builder().baseUrl(BeliApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

            val api = retrofit.create(BeliApi::class.java)

            val call = api.getBeli(data4, dataPenjual, dataNominal, "Bearer $data2")
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        //loading.dismiss();
                        try {
                            val jsonRes = JSONObject(response.body()!!.string())
                            hasil1 = jsonRes.getString("_id")
                            hasil2 = jsonRes.getString("penjual")
                            hasil3 = jsonRes.getString("pembeli")
                            hasil4 = jsonRes.getString("tgl_transaksi")
                            hasil5 = jsonRes.getString("bulan_transaksi")
                            hasil6 = jsonRes.getString("tahun_transaksi")
                            hasil7 = jsonRes.getString("jumlah_transaksi")
                            hasil8 = jsonRes.getString("__v")

                            val tost = Toast.makeText(applicationContext, "Transaksi dengan Penjual : " + hasil2 + " sejumlah : Rp " + hasil7 +
                                    " pada tanggal " + hasil4 + "-" + hasil5 + "-" + hasil6 + " \nBERHASIL !!!", Toast.LENGTH_LONG)
                            tost.show()

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    } else {

                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    val tost = Toast.makeText(applicationContext, "Please check your connection", Toast.LENGTH_LONG)
                    tost.show()
                }
            })

            val dashboard = Intent(this@PayConfirmActivity, HomeUserActivity::class.java)
            dashboard.putExtra("param1", data1)
            dashboard.putExtra("param2", data2)
            dashboard.putExtra("param3", data3)
            dashboard.putExtra("param4", data4)
            dashboard.putExtra("param5", data5)

            startActivity(dashboard)
        }
    }
}