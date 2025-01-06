package com.example.fastpacketvmovil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fastpacketvmovil.DetalleEnvioActivity
import com.example.fastpacketvmovil.GestionDatosActivity
import com.example.fastpacketvmovil.LoginActivity
import com.example.fastpacketvmovil.databinding.ActivityMainBinding
import com.example.fastpacketvmovil.poko.Colaborador
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var conductor: Colaborador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnMiPerfil.setOnClickListener {
            onMiPerfilClick()
        }

        binding.btnCerrarSesion.setOnClickListener {
            cerrarSesion()
        }
    }

    override fun onStart() {
        super.onStart()
        val jsonConductor = intent.getStringExtra("conductor")
        if (jsonConductor.isNullOrEmpty()) {
            Toast.makeText(this, "Error: Datos del conductor no disponibles", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val gson = Gson()
        conductor = gson.fromJson(jsonConductor, Colaborador::class.java)
        cargarDatos()
    }

    private fun cargarDatos() {

        //binding.tvNoGuia1.text = (conductor.)
    }

    private fun onMiPerfilClick() {
        Toast.makeText(this, "Redirigiendo a Mi perfil", Toast.LENGTH_SHORT).show()
        val gson = Gson()
        val stringConductor = gson.toJson(conductor)
        val intent = Intent(this@MainActivity, GestionDatosActivity::class.java)
        intent.putExtra("conductor", stringConductor)
        startActivity(intent)
    }

    private fun cerrarSesion() {
        Toast.makeText(this, "Redirigiendo a cerrar sesión", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onFilaClick(view: View) {
        Toast.makeText(this, "Fila clickeada", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetalleEnvioActivity::class.java)
        startActivity(intent)
        }
}