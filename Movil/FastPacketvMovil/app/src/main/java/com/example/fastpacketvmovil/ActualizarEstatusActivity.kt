package com.example.fastpacketvmovil

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fastpacketvmovil.databinding.ActivityActualizarEstatusBinding
import com.example.fastpacketvmovil.poko.Envio
import com.example.fastpacketvmovil.poko.Estatus
import com.example.fastpacketvmovil.poko.Mensaje
import com.example.fastpacketvmovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class ActualizarEstatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarEstatusBinding
    private lateinit var estatus: Estatus
    private lateinit var envio: Envio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarEstatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("ActualizarEstatusActivity", "onCreate: Iniciando actividad")
        obtenerDatos()
        serializarEnvio(intent.getStringExtra("envio"))
    }

    override fun onStart() {
        super.onStart()
        Log.d("ActualizarEstatusActivity", "onStart: Configurando listeners")
        binding.btnGuardar.setOnClickListener {
            if (sonCamposValidos()) {
                val nuevoEstatus = obtenerEstatusSeleccionado() // esto  devuelve un Pair
                editarEstatus(nuevoEstatus) // Pasamos el Pair directamente
            }
        }


        binding.btnVolver.setOnClickListener {
            volver()
        }
    }

    private fun obtenerDatos() {
        try {
            val estatusJson = intent.getStringExtra("estatus")
            Log.d("ActualizarEstatusActivity", "obtenerDatos: Estatus recibido: $estatusJson")

            if (estatusJson != null) {
                val gson = Gson()
                estatus = gson.fromJson(estatusJson, Estatus::class.java)
                cargarDatos()
            } else {
                Log.e("ActualizarEstatusActivity", "obtenerDatos: No se recibieron datos de estatus")
                mostrarError("No se recibieron datos del estatus")
            }
        } catch (e: Exception) {
            Log.e("ActualizarEstatusActivity", "obtenerDatos: Error: ${e.message}")
            mostrarError("Error al procesar los datos")
        }
    }

    private fun cargarDatos() {
        Log.d("ActualizarEstatusActivity", "cargarDatos: Configurando UI con estatus: ${estatus.estatus}")
        // Establecer los textos fijos
        binding.rbEnTransito.text = "En Tránsito"
        binding.rbDetenido.text = "Detenido"
        binding.rbEntregado.text = "Entregado"
        binding.rbPendienteEntrega.text = "Pendiente Entrega"
        binding.rbCancelado.text = "Cancelado"

        // Marcar el radio button correspondiente
        when (estatus.estatus?.toLowerCase()) {
            "en tránsito" -> binding.rbEnTransito.isChecked = true
            "detenido" -> binding.rbDetenido.isChecked = true
            "entregado" -> binding.rbEntregado.isChecked = true
            "pendiente entrega" -> binding.rbPendienteEntrega.isChecked = true
            "cancelado" -> binding.rbCancelado.isChecked = true
        }
    }

    private fun obtenerEstatusSeleccionado(): Int {
        val numero = when (binding.rgEstado.checkedRadioButtonId) {
            R.id.rbPendienteEntrega -> 1
            R.id.rbEnTransito -> 2
            R.id.rbDetenido -> 3
            R.id.rbEntregado -> 4
            R.id.rbCancelado -> 5
            else -> 0 // Valor por defecto si no hay selección
        }

        Log.d("ActualizarEstatusActivity", "Número seleccionado: $numero")

        return numero
    }

    private fun sonCamposValidos(): Boolean {
        if (binding.rgEstado.checkedRadioButtonId == -1) {
            mostrarDialogo("Error", "Por favor selecciona un estado")
            return false
        }
        return true
    }

    private fun mostrarDialogo(titulo: String, mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun editarEstatus(numeroSeleccionado: Int) {
        // obtencion el texto del motivo ingresado por el usuario
        val motivo = binding.etMotivo.text.toString().trim()

        // validacion de que el motivo no esté vacío
        if (motivo.isEmpty()) {
            mostrarDialogo("Por favor", "Ingresa un motivo")
            return
        }

        Log.d("ActualizarEstatusActivity", "editarEstatus: Número seleccionado: $numeroSeleccionado, Motivo: $motivo")

        val gson = Gson()

        // crear el mapa de parametros
        val parametros = mapOf(
            "idEstatus" to numeroSeleccionado,  // Número asociado al RadioButton
            "motivoModificacion" to motivo,               // Texto del motivo ingresado
            "numeroDeGuia" to envio.numeroDeGuia,
            "idColaboradorModificacion" to envio.idColaboradorModificacion
        )
        Log.d("ActualizarEstatusActivity", "editarEstatus: Parámetros: $parametros")

        //se convierten los parámetros a JSON
        val parametrosJson = gson.toJson(parametros)

        // para enviar la solicitud PUT al servidor
        Ion.with(this)
            .load("PUT", "${Constantes().URL_WS}/envio/actualizarEstatusEnvio")
            .setHeader("Content-Type", "application/json")
            .setStringBody(parametrosJson)
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null) {
                    Log.d("ActualizarEstatusActivity", "editarEstatus: Info: $result")
                    respuestaEdicion(result)
                } else {
                    Log.e("ActualizarEstatusActivity", "editarEstatus: Error: ${e?.message}")
                    mostrarError("Error al actualizar el estatus")
                }
            }
    }

    private fun esFormatoValido(texto: String): Boolean {
        //  acepta solo letras (mayúsculas y minúsculas) y números
        val patron = "^[a-zA-Z0-9\\s]*$".toRegex()
        return patron.matches(texto)
    }

    fun serializarEnvio(envios : String?){
        val gson = Gson()
        envio = gson.fromJson(envios,Envio::class.java)
    }

    private fun respuestaEdicion(resultado: String) {
        try {
            val gson = Gson()
            if (resultado.trim().startsWith("{")) {
                val mensaje = gson.fromJson(resultado, Mensaje::class.java)
                Toast.makeText(this, mensaje.mensaje, Toast.LENGTH_LONG).show()
                if (!mensaje.error) {
                    finish()
                }
            } else {
                Toast.makeText(this, resultado, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Log.e("ActualizarEstatusActivity", "respuestaEdicion: Error: ${e.message}")
            mostrarError("Error al procesar la respuesta del servidor")
        }
    }

    private fun mostrarError(mensaje: String) {
        runOnUiThread {
            //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }

    private fun volver() {
        finish()
    }


}