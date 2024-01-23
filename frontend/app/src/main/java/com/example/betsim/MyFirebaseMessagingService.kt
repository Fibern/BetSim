package com.example.betsim

import android.util.Log
import com.example.betsim.data.local.SecurePreferencesHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService  : FirebaseMessagingService(){

    @Inject
    lateinit var helper: SecurePreferencesHelper

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i("Jd", message.toString())
    }

    override fun onNewToken(token: String) {
        helper.saveDeviceToken(token)
    }


}
