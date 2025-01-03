package com.example.fastpacket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class recuperarContrasenia : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperar_contrasenia)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recuperarContrasenia)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val searchView = findViewById<SearchView>(R.id.svNumeroPersonal)
        val btnObtenerDatos = findViewById<Button>(R.id.btnObtenerDatos)

        btnObtenerDatos.setOnClickListener {
            val numeroDePersonal = searchView.query.toString()
            if (numeroDePersonal.isNotEmpty()) {
                obtenerDatos(numeroDePersonal)
            } else {
                Toast.makeText(this, "Por favor, ingresa un número de personal válido.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para manejar la obtención de datos
    private fun obtenerDatos(numeroDePersonal: String) {
        Toast.makeText(this, "Datos obtenidos para el número: $numeroDePersonal", Toast.LENGTH_SHORT).show()
        // Aquí puedes agregar tu lógica para realizar la acción de obtención de datos (como llamadas a APIs o consultas locales)
    }

    // Método para manejar el botón "Volver"
    fun volver3(view: View) {
        finish() // Finaliza la actividad actual para volver a la pantalla anterior
    }
}