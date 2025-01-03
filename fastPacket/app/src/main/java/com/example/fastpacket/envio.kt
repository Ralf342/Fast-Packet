package com.example.fastpacket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class envio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_envio)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.envio)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun onRowClick(view: View) {
        Toast.makeText(this, "Fila clickeada", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, detalleEnvio::class.java)
        startActivity(intent)
    }
}