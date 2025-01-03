package com.example.fastpacket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

    class detalleEnvio : AppCompatActivity() {
        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_detalle_envio)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detalleEnvio)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
        fun actualizarEstado(view: View) {
            val intent = Intent(this, actualizarStatus::class.java)
            startActivity(intent)
        }
        fun volver(view: View) {
            finish()
        }
    }