package com.example.fastpacketvmovil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fastpacketvmovil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var conductor: Conductor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart(){
        super.onStart()

        fun onFilaClick(view: View) {
            Toast.makeText(this, "Fila clickeada", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DetalleEnvioActivity::class.java)
            startActivity(intent)
        }

        fun cargarDatos(){
            binding.tvNoGuia1.setText(conductor.)
        }


    }



    fun onCerrarSesionClick(View: View){

    }

    fun onMiPerfilClick(view: View) {
        Toast.makeText(this, "Redirigiendo a Mi perfil", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, GestionDatosActivity::class.java)
        startActivity(intent)
    }


}