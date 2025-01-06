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
<<<<<<< HEAD
import com.example.fastpacketvmovil.poko.Colaborador
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var conductor: Colaborador
=======

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var conductor: Conductor
>>>>>>> e7f82969ade809d737070544c71c020f002c294f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        val jsonConductor = intent.getStringExtra("conductor")
        if (jsonConductor.isNullOrEmpty()) {
            Toast.makeText(this, "Error: Datos del conductor no disponibles", Toast.LENGTH_SHORT).show()
            finish() // Finaliza la actividad si no hay datos
            return
        }

        val gson = Gson()
        conductor = gson.fromJson(jsonConductor, Colaborador::class.java)
        binding.btnMiPerfil.setOnClickListener {
            onMiPerfilClick()
        }
        binding.btnCerrarSesion.setOnClickListener {
            cerrarSesion()
        }
    }

<<<<<<< HEAD

    fun onMiPerfilClick() {
        Toast.makeText(this, "Redirigiendo a Mi perfil", Toast.LENGTH_SHORT).show()
        val gson = Gson()
        val stringConductor = gson.toJson(conductor)
        val intent = Intent(this@MainActivity, GestionDatosActivity::class.java)
        intent.putExtra("conductor",stringConductor)
        startActivity(intent)
    }

    fun cerrarSesion() {
        Toast.makeText(this, "Redirigiendo a cerrar sesion", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
=======
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
>>>>>>> e7f82969ade809d737070544c71c020f002c294f
        startActivity(intent)
    }


}