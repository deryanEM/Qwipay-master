package com.example.baka57r.ezpy.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.baka57r.ezpy.NotifModel
import com.example.baka57r.ezpy.R

/**
 * Created by Lely
 */
class ViewNotifAdapter(internal var context: Context, var datas: List<NotifModel>? = null) :
        RecyclerView.Adapter<ViewNotifAdapter.MasterBuyerTransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterBuyerTransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_list_transaksi, parent, false)
        return MasterBuyerTransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: MasterBuyerTransactionViewHolder, position: Int) {
        holder.data = datas?.get(position)
    }

    inner class MasterBuyerTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data: NotifModel? = null
            set(value) {

                var nominal = itemView.findViewById<TextView>(R.id.nominal)

                if(value?.message == "Pengeluaran"){
                    nominal.setTextColor(context.resources.getColor(R.color.red_700))
                }else{
                    nominal.setTextColor(context.resources.getColor(R.color.green_700))
                }

                itemView.findViewById<TextView>(R.id.tipe_transaksi).text = value?.penjual
                itemView.findViewById<TextView>(R.id.custHpTextview).text = value?.waktu
                itemView.findViewById<TextView>(R.id.nominal).text = value?.nominal
                itemView.findViewById<TextView>(R.id.in_out).text = value?.message

            }
    }
}