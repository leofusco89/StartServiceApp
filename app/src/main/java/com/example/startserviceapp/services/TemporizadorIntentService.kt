package com.example.startserviceapp.services

import android.app.IntentService
import android.content.Intent
import android.util.Log

class TemporizadorIntentService: IntentService("TemporizadorIntentService") {

    companion object{
        private  const val TAG = "TemporIntentService" //La usamos para escribir en el log y así
        //poder ver los momentos en el que se llaman los callbacks (métodos abajo)
    }

    override fun onHandleIntent(p0: Intent?) {
        //Intent es el que inicia esta solicitud de servicio
        Log.d(TAG, "TAREA INICIADA")
        Thread.sleep(3000)
        Log.d(TAG, "TAREA FINALIZADA")
    }
}