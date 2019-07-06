package com.example.baka57r.ezpy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ProfilPenjual : AppCompatActivity() {

    internal var data1: String? = null
    internal var data2: String? = null
    internal var data3: String? = null
    internal var data4: String? = null
    internal var data5: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val bundle = intent.extras
        data1 = bundle!!.getString("param1")
        data2 = bundle.getString("param2")
        data3 = bundle.getString("param3")
        data4 = bundle.getString("param4")
        data5 = bundle.getString("param5")

        qid.setText(data5)
        nama_user.setText(data1)
        email_user.setText(data4)

        titleSearch!!.text = "QID"
        titleSearch!!.setTextColor(Color.WHITE)
        setSupportActionBar(toolbar_ic)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        faq_button.setVisibility(View.GONE)

        toolbar_ic.setNavigationOnClickListener(View.OnClickListener {
           onBackPressed()
        })

        val sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            toolbar_ic.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.headerkecil) )
        } else {
            toolbar_ic.setBackground(ContextCompat.getDrawable(this, R.drawable.headerkecil))
        }

    }

    fun logout(v: View) {
        val retrofit = Retrofit.Builder().baseUrl(LogoutApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(LogoutApi::class.java)

        val call = api.getOut("Bearer " + data2!!)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    try {
                        val jsonRes = JSONObject(response.body()!!.string())

                        val editor = getSharedPreferences("DataUser", Context.MODE_PRIVATE).edit()
                        editor.clear().apply()

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

        val dashboard = Intent(this@ProfilPenjual, LoginActivity::class.java)
        startActivity(dashboard)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
            val dashboard = Intent(this@ProfilPenjual, HomePenjualActivity::class.java)
            dashboard.putExtra("param1", data1)
            dashboard.putExtra("param2", data2)
            dashboard.putExtra("param3", data3)
            dashboard.putExtra("param4", data4)
            dashboard.putExtra("param5", data5)
            startActivity(dashboard)
            return true
        }

        override fun onBackPressed() {
            val dashboard = Intent(this@ProfilPenjual, HomePenjualActivity::class.java)
            dashboard.putExtra("param1", data1)
            dashboard.putExtra("param2", data2)
            dashboard.putExtra("param3", data3)
            dashboard.putExtra("param4", data4)
            dashboard.putExtra("param5", data5)
            //startActivity(dashboard)
            setResult(Activity.RESULT_OK, dashboard)
            finish()
        }
    }