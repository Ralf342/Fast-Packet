package com.example.fastpacket

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // listeners para los TextView
        findViewById<TextView>(R.id.tvNoTengoCuenta).setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.tvOlvideContrasena).setOnClickListener {
            val intent = Intent(this, recuperarContrasenia::class.java)
            startActivity(intent)
        }
    }
}