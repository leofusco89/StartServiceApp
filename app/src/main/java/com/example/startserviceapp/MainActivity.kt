package com.example.startserviceapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.startserviceapp.services.TemporizadorService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asociamos los botones a TemporizadorService en foreground o background
        btn_foreground.setOnClickListener {
            val intent = Intent(this, TemporizadorService::class.java)
            //Indicamos un valor de referencia para saber que es la ejecución background
            intent.putExtra("Foreground", true)

            //Servicio en foreground
            //Verificamos que la versión sea mayor a Android Oreo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }else{
                //Si no podemos, lo mandamos en background
                startService(intent)
            }
        }

        btn_background.setOnClickListener {
            val intent = Intent(this, TemporizadorService::class.java)
            //Indicamos un valor de referencia para saber que es la ejecución foreground
            intent.putExtra("Foreground", false)

            //Servicio en background
            startService(intent)
        }
    }
}
