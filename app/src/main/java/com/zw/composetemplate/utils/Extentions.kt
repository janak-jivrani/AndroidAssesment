package com.zw.composetemplate.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.util.Log

fun Context.getIpAddress(): String {
    val wm = this.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val ip: String = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
    Log.e("TAG", "getIpAddress: "+ip)
    return ip
}