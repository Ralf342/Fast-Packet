package com.example.fastpacketvmovil

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fastpacketvmovil.GestionDatosActivity
import com.example.fastpacketvmovil.MainActivity
import com.example.fastpacketvmovil.databinding.ActivityDetalleEnvioBinding
import com.example.fastpacketvmovil.databinding.ActivityMainBinding
import com.example.fastpacketvmovil.poko.Cliente
import com.example.fastpacketvmovil.poko.Colaborador
import com.example.fastpacketvmovil.poko.Envio
import com.example.fastpacketvmovil.poko.Estatus
import com.example.fastpacketvmovil.poko.Paquete
import com.example.fastpacketvmovil.util.Constantes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion


class DetalleEnvioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleEnvioBinding
    private lateinit var paquete: Paquete
    private lateinit var envio: Envio
    private lateinit var cliente: Cliente
    //p ver si los datos se reciben
    private var datosCompletos = false


    private var paqueteRecibido = false
    private var clienteRecibido = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleEnvioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("DetalleEnvioActivity", "metodoOnCreate: Iniciando serialización de envio")
        serializarEnvio()
    }

    override fun onStart() {
        super.onStart()
        Log.d("DetalleEnvioActivity", "onStart: Estableciendo listeners de botones")
        binding.btnRegresar.setOnClickListener {
            regresarMain()
        }
        binding.btnActualizarEstado.setOnClickListener {
            irActualizarEstatus()
        }
    }

    private fun serializarEnvio() {
        Log.d("DetalleEnvioActivity", "serializarEnvio: Obteniendo datos de la intención")
        val gson = Gson()
        envio = gson.fromJson(intent.getStringExtra("envio"), Envio::class.java)
        Log.d("DetalleEnvioActivity", "serializarEnvio: Datos de envio recibidos: $envio")
        obtenerPaquetesEnvio()
        obtenerCliente()
    }

    private fun serializarPaquete(result: String) {
        Log.d("DetalleEnvioActivity", "serializarPaquete: Datos del paquete recibidos")
        val gson = Gson()
        try {
            val listaPaquetes: List<Paquete> = gson.fromJson(result, object : TypeToken<List<Paquete>>() {}.type)
            Log.d("DetalleEnvioActivity", "Paquetes recibidos: $listaPaquetes")

            if (listaPaquetes.isNotEmpty()) {
                paquete = listaPaquetes[0]
                Log.d("DetalleEnvioActivity", "Primer paquete deserializado: $paquete")
                paqueteRecibido = true
                verificarYCargarDatos()
            } else {
                Log.e("DetalleEnvioActivity", "La lista de paquetes está vacía.")
                runOnUiThread {
                    Toast.makeText(this, "No se encontraron paquetes para este envío", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Log.e("DetalleEnvioActivity", "Error al deserializar paquetes: ${e.message}")
            runOnUiThread {
                Toast.makeText(this, "Error al procesar los datos del paquete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun serializarCliente(result: String) {
        Log.d("DetalleEnvioActivity", "serializarCliente: Deserializando cliente")
        val gson = Gson()
        try {
            if (result.trim().startsWith("[")) {
                val listaClientes: List<Cliente> = gson.fromJson(result, object : TypeToken<List<Cliente>>() {}.type)
                Log.d("DetalleEnvioActivity", "Clientes recibidos: $listaClientes")

                if (listaClientes.isNotEmpty()) {
                    cliente = listaClientes[0]
                    Log.d("DetalleEnvioActivity", "Primer cliente deserializado: $cliente")
                } else {
                    Log.e("DetalleEnvioActivity", "La lista de clientes está vacía.")
                    runOnUiThread {
                        Toast.makeText(this, "No se encontraron clientes para este envío", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                cliente = gson.fromJson(result, Cliente::class.java)
                Log.d("DetalleEnvioActivity", "Cliente único recibido: $cliente")
            }
            clienteRecibido = true
            verificarYCargarDatos()
        } catch (e: Exception) {
            Log.e("DetalleEnvioActivity", "Error al deserializar cliente: ${e.message}")
            runOnUiThread {
                Toast.makeText(this, "Error al procesar los datos del cliente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verificarYCargarDatos() {
        Log.d("DetalleEnvioActivity", "verificarYCargarDatos: Verificando si se recibieron todos los datos")
        if (paqueteRecibido && clienteRecibido) {
            Log.d("DetalleEnvioActivity", "verificarYCargarDatos: Todos los datos recibidos, cargando datos...")
            runOnUiThread {
                try {
                    cargarDatos()
                } catch (e: Exception) {
                    Log.e("Error", "Error al cargar datos: ${e.message}")
                    Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun cargarDatos() {
        try {
            Log.d("DetalleEnvioActivity", "cargarDatos: Cargando los datos en la UI")
            binding.tvOrigen.text = "${envio.calleOrigen ?: "Calle Desconocida"}, ${envio.coloniaOrigen ?: "Colonia Desconocida"}, ${envio.codigoPostalOrigen ?: "C.P. Desconocido"}"
            binding.tvDestino.text = "${cliente.calle ?: "Calle Desconocida"}, ${cliente.colonia ?: "Colonia Desconocida"}, ${cliente.codigoPostal ?: "C.P. Desconocido"}"
            binding.tvNoPaquetes.text = "${paquete.numeroDeGuia ?: "Desconocido"} - ${paquete.descripcion ?: "Descripción no disponible"}"
            binding.tvDestinatario.text = "${cliente.nombre ?: "Nombre Desconocido"} ${cliente.apellidoPaterno ?: "Apellido Paterno Desconocido"} ${cliente.apellidoMaterno ?: "Apellido Materno Desconocido"}"
            binding.tvCorreo.text = cliente.correo ?: "Correo no disponible"
            binding.tvTelefono.text = cliente.telefono ?: "Teléfono no disponible"
            binding.tvStatus.text = envio.estatus ?: "Estatus no disponible"
        } catch (e: Exception) {
            Log.e("Error", "Error en cargarDatos: ${e.message}")
            Toast.makeText(this, "Error al mostrar los datos", Toast.LENGTH_SHORT).show()
        }
    }

    /*override fun onStart() {
        super.onStart()
        Log.d("DetalleEnvioActivity", "onStart: Estableciendo listeners de botones")
        binding.btnRegresar.setOnClickListener {
            regresarMain()
        }
        binding.btnActualizarEstado.setOnClickListener {
            irActualizarEstatus()
        }
    }*/

    private fun obtenerCliente() {
        Ion.getDefault(this).conscryptMiddleware.enable(false)
        Log.d("DetalleEnvioActivity", "obtenerCliente: Realizando solicitud al servidor para obtener cliente")
        Ion.with(this)
            .load("GET", "${Constantes().URL_WS}/cliente/buscarClientePorCorreo/${envio.correo}")
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null) {
                    Log.d("DetalleEnvioActivity", "obtenerCliente: Cliente recibido: $result")
                    serializarCliente(result)
                } else {
                    Log.e("Error", "Error al obtener cliente: ${e?.message}")
                    runOnUiThread {
                        Toast.makeText(this, "Error al obtener datos del cliente", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    private fun obtenerPaquetesEnvio() {
        Log.d("DetalleEnvioActivity", "obtenerPaquetesEnvio: Realizando solicitud al servidor para obtener paquete")
        Ion.getDefault(this).conscryptMiddleware.enable(false)
        Ion.with(this)
            .load("GET", "${Constantes().URL_WS}/paquete/consultarPaquetePorEnvio/${envio.numeroDeGuia}")
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null) {
                    Log.d("DetalleEnvioActivity", "obtenerPaquetesEnvio: Paquete recibido: $result")
                    serializarPaquete(result)
                } else {
                    Log.e("Error", "Error al obtener paquete: ${e?.message}")
                    runOnUiThread {
                        Toast.makeText(this, "Error al obtener datos del paquete", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    private fun irActualizarEstatus() {
        Log.d("DetalleEnvioActivity", "irActualizarEstatus: Redirigiendo a la actividad de actualización de estatus")

        // para el error al presionar el btn de actualizar es el m de abajo
        val gson = Gson()
        val estatusJson = gson.toJson(Estatus(
            idEstatus = envio.idEstatus,
            estatus = envio.estatus ?: ""
        ))
        val intent = Intent(this, ActualizarEstatusActivity::class.java)
        intent.putExtra("estatus", estatusJson)
        startActivity(intent)
    }

    private fun regresarMain() {
        Log.d("DetalleEnvioActivity", "regresarMain: Redirigiendo a la pantalla principal")
        Toast.makeText(this, "Redirigiendo a la pantalla principal", Toast.LENGTH_SHORT).show()
        finish()
    }
}
