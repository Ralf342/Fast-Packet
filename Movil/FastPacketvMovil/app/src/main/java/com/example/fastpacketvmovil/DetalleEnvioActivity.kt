package com.example.fastpacketvmovil

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalleEnvioActivity : AppCompatActivity() {
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
    //listener
    fun volver(view: View) {
        finish()
    }

    fun actualizarEstado(view: View) {
        Toast.makeText(this, "Estado actualizado", Toast.LENGTH_SHORT).show()
    }
}