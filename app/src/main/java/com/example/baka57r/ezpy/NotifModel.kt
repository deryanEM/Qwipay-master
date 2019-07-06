package com.example.baka57r.ezpy

import com.google.gson.annotations.SerializedName

data class NotifModel(
        @SerializedName("type")
        val type: String = "",
        @SerializedName("message")
        val message: String = "",
        @SerializedName("pembeli")
        val pembeli: String = "",
        @SerializedName("penjual")
        val penjual: String = "",
        @SerializedName("nominal")
        val nominal: String = "",
        @SerializedName("waktu")
        val waktu: String = "")