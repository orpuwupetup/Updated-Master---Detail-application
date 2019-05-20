package com.orpuwupetup.numberslight.utils.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkConnectionChecker(private val context: Context) {

    fun isNetworkConnected() : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}