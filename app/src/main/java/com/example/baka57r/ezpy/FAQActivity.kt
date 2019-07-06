package com.example.baka57r.ezpy

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.widget.ExpandableListView
import kotlinx.android.synthetic.main.activity_faq.*
import java.util.*

class FAQActivity : AppCompatActivity() {
    internal lateinit var expListView: ExpandableListView
    internal lateinit var listAdapter: DetailFAQAdapter
    internal lateinit var listDataChild: HashMap<String, List<String>>
    private var toolbar: android.support.v7.widget.Toolbar? = null
    private var title: android.widget.TextView? = null
    private var faq: android.widget.ImageButton? = null
    private var cancel: android.widget.ImageButton? = null

    var listDataHeader = arrayOf("Apa saya harus menggunakan e-mail utama?", "Mengapa e-mail saya dianggap tidak valid?", "Apa saya harus memberikan nomor handphone?", "Mengapa no. handphone saya dianggap tidak valid?")

    var dataAnswer = arrayOf("Dalam proses registrasi, mohon gunakan E-mail aktif/paling sering digunakan. E-mail tersebut akan digunakan untuk masuk ke Qwipay", "Pastikan kamu menggunakan E-mail aktif dengan penulisan yang lengkap.\nContoh: Qwipay-help@qwipay.com", "Ya, Harus no Handphone dibutuhkan dalam proses register akun qwipay", "Pastikan kamu menggunakan nomor aktif dengan format penulisan +62xxx atau 08xxx")

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)


        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels

        close_button.setOnClickListener {
            startActivity(Intent(applicationContext, HalamanAwalActivity::class.java))
            finish()
        }
        lvExp.setIndicatorBounds(width - GetPixelFromDips(50f), width - GetPixelFromDips(10f))

        listDataChild = HashMap()

        for (i in listDataHeader.indices) {
            val answers = dataAnswer[i]
            val questions = listDataHeader[i]

            val restData = ArrayList<String>()
            restData.add(answers)

            listDataChild[questions] = restData
            listAdapter = DetailFAQAdapter(this@FAQActivity, Arrays.asList(*listDataHeader), listDataChild)
            lvExp.setAdapter(listAdapter)
        }
    }

    fun GetPixelFromDips(pixels: Float): Int {
        // Get the screen's density scale
        val scale = resources.displayMetrics.density
        // Convert the dps to pixels, based on density scale
        return (pixels * scale + 0.5f).toInt()
    }
}