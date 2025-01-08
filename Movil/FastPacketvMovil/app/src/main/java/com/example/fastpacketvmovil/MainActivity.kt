package com.example.fastpacketvmovil

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fastpacketvmovil.LoginActivity
import com.example.fastpacketvmovil.adapter.EnvioAdapter
import com.example.fastpacketvmovil.databinding.ActivityMainBinding
import com.example.fastpacketvmovil.interfaces.ListenerRecycleEnvios
import com.example.fastpacketvmovil.poko.Cliente
import com.example.fastpacketvmovil.poko.Colaborador
import com.example.fastpacketvmovil.poko.Envio
import com.example.fastpacketvmovil.poko.Estatus
import com.example.fastpacketvmovil.util.Constantes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion

class MainActivity : AppCompatActivity(), ListenerRecycleEnvios {

    private lateinit var binding: ActivityMainBinding
    private lateinit var conductor: Colaborador
    private lateinit var cliente: Cliente
    private lateinit var estatus: Estatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configurarRecyclerEnvio()

        binding.btnMiPerfil.setOnClickListener {
            onMiPerfilClick()
        }

        binding.btnCerrarSesion.setOnClickListener {
            cerrarSesion()
        }

    }

    override fun onStart() {
        super.onStart()
        try {
            val jsonConductor = intent.getStringExtra("conductor")
            if (jsonConductor.isNullOrEmpty()) {
                Log.e("ERROR", "JSON del conductor es nulo o vacío")
                Toast.makeText(this, "Error: Datos del conductor no disponibles", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

            val gson = Gson()
            conductor = gson.fromJson(jsonConductor, Colaborador::class.java)
            Log.d("CONDUCTOR", "Conductor inicializado: $conductor")
            obtenerEnvios()
        } catch (e: Exception) {
            Log.e("ERROR", "Error en onStart: ${e.message}")
            e.printStackTrace()
            finish()
        }
    }

    /*@SuppressLint("SetTextI18n")
    private fun cargarDatos() {
        binding.tvNoGuia1.text = "${envio.numeroDeGuia} - ${cliente.calle}, ${cliente.colonia}, ${cliente.codigoPostal} - ${estatus.estatus}"
        binding.tvNoGuia2.text = "${envio.numeroDeGuia} - ${cliente.calle}, ${cliente.colonia}, ${cliente.codigoPostal} - ${estatus.estatus}"
        binding.tvNoGuia3.text = "${envio.numeroDeGuia} - ${cliente.calle}, ${cliente.colonia}, ${cliente.codigoPostal} - ${estatus.estatus}"
    }*/

    private fun configurarRecyclerEnvio(){
        binding.rvEnvios.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvEnvios.setHasFixedSize(true)
    }


    private fun onMiPerfilClick() {
        Toast.makeText(this, "Redirigiendo a Mi perfil", Toast.LENGTH_SHORT).show()
        val gson = Gson()
        val stringConductor = gson.toJson(conductor)
        val intent = Intent(this@MainActivity, GestionDatosActivity::class.java)
        intent.putExtra("conductor", stringConductor)
        startActivity(intent)
    }

    fun obtenerEnvios(){
        Ion.getDefault(this@MainActivity).conscryptMiddleware.enable(false)

        Ion.with(this@MainActivity)
            .load("GET","${Constantes().URL_WS}/envio/obtenerEnvios")
            .asString()
            .setCallback { e, result ->
                if (e == null){
                    Log.d("RESPONSE", "Respuesta exitosa: $result") // Agregar este log
                    serializarEnvios(result)
                }else{
                    Log.e("ERROR", "Error en la petición: ${e.message}") // Agregar este log
                    Toast.makeText(this@MainActivity,"error",Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun serializarEnvios(json: String){
        try {
            val gson = Gson()
            val type = object : TypeToken<List<Envio>>() {}.type
            val listaEnvios: List<Envio> = gson.fromJson(json, type)
            Log.d("ENVIOS", "Número de envíos recibidos: ${listaEnvios.size}") // Agregar este log
            if(listaEnvios.isNotEmpty()) {
                cargarEnvios(listaEnvios)
            } else {
                Log.d("ENVIOS", "La lista de envíos está vacía")
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Error al serializar JSON: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun cargarEnvios(envios: List<Envio>){
        try {
            binding.rvEnvios.adapter = EnvioAdapter(envios, this@MainActivity)
        } catch (e: Exception) {
            Log.e("ERROR", "Error al cargar el adapter: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun cerrarSesion() {
        Toast.makeText(this, "Redirigiendo a cerrar sesión", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onFilaClick() {
        Toast.makeText(this, "Fila clickeada", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetalleEnvioActivity::class.java)
        startActivity(intent)
    }

    override fun clickDetalleEnvio(
        envio: Envio,
        position: Int
    ) {
        Log.i("Info", envio.toString())
        val gson = Gson()
        val intent = Intent(this@MainActivity, DetalleEnvioActivity::class.java)
        val datosEnvio = gson.toJson(envio)
        val datosColaborador = gson.toJson(conductor)
        intent.putExtra("envio", datosEnvio)
        intent.putExtra("colaborador", datosColaborador)
        startActivity(intent)
    }
}