package com.jazminbustillos.recordatoriodehidratacion

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class HidratacionWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        mostrarNotificacion()
        return Result.success()
    }

    private fun mostrarNotificacion() {
        val channelId = "hidratacion_channel"

        val manager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Recordatorio de Hidratación",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificaciones de hidratación"
                enableLights(true)
                enableVibration(true)
            }

            manager.createNotificationChannel(channel)
        }

        val imagen = BitmapFactory.decodeResource(
            applicationContext.resources,
            R.drawable.agua_icon
        )

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(imagen)
            .setContentTitle("💙 ¡Es hora de beber agua! 💙")
            .setContentText("💙 ¡Es hora de beber agua! 💙")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(imagen)
                    .bigLargeIcon(imagen)
                    .setBigContentTitle("💙 ¡Es hora de beber agua! 💙")
                    .setSummaryText("Mantente hidratado 💧")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
            .build()

        manager.notify(1001, notification)
    }
}