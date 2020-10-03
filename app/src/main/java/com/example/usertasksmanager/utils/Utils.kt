package com.example.usertasksmanager.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Utils{
    companion object{
        var BASE_URL : String = "https://jsonplaceholder.typicode.com/"
        val CACHE_SIZE = (10 * 1024 * 1024).toLong()

        fun hasNetwork(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
    }

}