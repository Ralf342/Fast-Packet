package com.example.fastpacketvmovil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fastpacketvmovil.databinding.ActivityActualizarEstatusBinding
import com.example.fastpacketvmovil.poko.Estatus
import com.example.fastpacketvmovil.poko.Mensaje
import com.example.fastpacketvmovil.poko.Unidad
import com.example.fastpacketvmovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class ActualizarEstatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarEstatusBinding
    private lateinit var estatus: Estatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarEstatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("ActualizarEstatusActivity", "onCreate: Iniciando actividad")
        obtenerDatos()
    }

    override fun onStart() {
        super.onStart()
        Log.d("ActualizarEstatusActivity", "onStart: Configurando listeners")
        binding.btnGuardar.setOnClickListener {
            if (sonCamposValidos()) {
                val nuevoEstatus = obtenerEstatusSeleccionado()
                editarEstatus(nuevoEstatus)
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

    private fun obtenerEstatusSeleccionado(): Estatus {
        val nuevoEstatusTexto = when (binding.rgEstado.checkedRadioButtonId) {
            R.id.rbEnTransito -> "En Tránsito"
            R.id.rbDetenido -> "Detenido"
            R.id.rbEntregado -> "Entregado"
            R.id.rbPendienteEntrega -> "Pendiente Entrega"
            R.id.rbCancelado -> "Cancelado"
            else -> estatus.estatus
        }

        return Estatus(
            idEstatus = estatus.idEstatus,
            estatus = nuevoEstatusTexto

        )
    }

    private fun sonCamposValidos(): Boolean {
        if (binding.rgEstado.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor selecciona un estado", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    /*private fun editarEstatus(estatus: Estatus) {
        Log.d("ActualizarEstatusActivity", "editarEstatus: Actualizando estatus: $estatus")
        val gson = Gson()
        val parametros = gson.toJson(estatus)

        Ion.with(this)
            .load("PUT", "${Constantes().URL_WS}/envio/actualizarEstatusEnvio")
            .setHeader("Content-Type", "application/json")
            .setStringBody(parametros)
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null) {
                    respuestaEdicion(result)
                } else {
                    Log.e("ActualizarEstatusActivity", "editarEstatus: Error: ${e?.message}")
                    mostrarError("Error al actualizar el estatus")
                }
            }
    }*/
    private fun editarEstatus(estatus: Estatus) {
        Log.d("ActualizarEstatusActivity", "editarEstatus: Actualizando estatus: $estatus")
        val gson = Gson()
        // aca se asegura q el objeto Estatus enviado no incluya el número de guía innecesario
        val parametros = gson.toJson(Estatus(idEstatus = estatus.idEstatus, estatus = estatus.estatus))

        Ion.with(this)
            .load("PUT", "${Constantes().URL_WS}/envio/actualizarEstatusEnvio")
            .setHeader("Content-Type", "application/json")
            .setStringBody(parametros)
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null) {
                    respuestaEdicion(result)
                } else {
                    Log.e("ActualizarEstatusActivity", "editarEstatus: Error: ${e?.message}")
                    mostrarError("Error al actualizar el estatus")
                }
            }
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
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }

    private fun volver() {
        finish()
    }


}