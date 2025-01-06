package com.example.fastpacketvmovil

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActualizarEstatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actualizar_estatus)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.actualizarEstatus)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//listener
    fun guardar(view: View) {
        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
    }

    fun volver(view: View) {
        finish()
    }
}