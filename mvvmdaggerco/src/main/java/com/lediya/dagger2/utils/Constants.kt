package com.lediya.dagger2.utils

import android.content.Context
import android.net.ConnectivityManager

object Constants {
    const val BASE_URL ="https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    const val TAG = "M2P"
    const val SPLASH_TIME_OUT:Long=3000 // 3 sec
    /*
   Check the internet activity in the application **/
    fun  isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }
}