package com.example.fastpacketvmovil

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GestionDatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gestion_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gestionDatos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//listener
    fun actualizarDatos(view: View) {
        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
    }

    fun volver(view: View) {
        finish()
    }
}