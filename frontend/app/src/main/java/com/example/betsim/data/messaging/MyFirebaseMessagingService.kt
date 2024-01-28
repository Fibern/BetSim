package com.example.betsim.data.messaging

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.betsim.R
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

       val notificationBuilder = NotificationCompat.Builder(this, "my_channel_id")
                .setSmallIcon(R.drawable.ic_casino_chip)
                .setColor(ContextCompat.getColor(this, R.color.iconBackground))
                .setContentTitle(message.notification?.title)
                .setContentText(message.notification?.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notificationBuilder.build())

    }

    override fun onNewToken(token: String) {
        helper.saveDeviceToken(token)
    }


}
