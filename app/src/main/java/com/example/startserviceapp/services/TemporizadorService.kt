package com.example.startserviceapp.services

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class TemporizadorService: Service() {

    companion object{
        private  const val TAG = "TemporizadorService" //La usamos para escribir en el log y así
        //poder ver los momentos en el que se llaman los callbacks (métodos abajo)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "ON CREATE")
    }

    override fun onBind(p0: Intent?): IBinder? {
        //No se va a llamar nunca porque estamos creando servicio started, retornamos null entonces
        //En Bound Service, lo asociamos a la activity (intent) que lo invoca
        Log.d(TAG, "ON BIND")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //Acá se indica el servicio que iniciamos.
        //startId es el ID que representa a la instancia que se va a correr, es incremental
        //automáticamente. Sirve para luego, por ejemplo, finalizar esta instancia en particular.
        Log.d(TAG, "ON START COMMAND")

        //Esto se agrega para ejecuciones en SO anteriores a Android Oreo. Primero se inicia en
        //background y apenas entra acá, se pasa a foreground. Post Android Oreo, basta con llamar a
        //startForegroundService(intent) como está en el MainActivity.
        startForeground(1, getNotification())
        //Creamos con clase anónima de Runnable, un thread que va a correr en el main thread
        Thread(
            Runnable {
                Log.d(TAG, "TAREA INICIADA")
                Thread.sleep(3000)
                Log.d(TAG, "TAREA FINALIZADA")
                stopSelf(startId)
            }
        ).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "ON DESTROY")
        super.onDestroy()
    }

    private fun getNotification(): Notification{
        val notificationBuilder = NotificationCompat.Builder(this, "channelId")
        return  notificationBuilder.build()
    }
}