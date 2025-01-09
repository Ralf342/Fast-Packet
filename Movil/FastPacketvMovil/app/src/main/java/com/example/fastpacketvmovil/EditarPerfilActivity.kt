package com.example.fastpacketvmovil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fastpacketvmovil.databinding.ActivityEditarPerfilBinding
import com.example.fastpacketvmovil.poko.Colaborador
import com.example.fastpacketvmovil.poko.Mensaje
import com.example.fastpacketvmovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class EditarPerfilActivity : AppCompatActivity() {

     lateinit var binding: ActivityEditarPerfilBinding
     lateinit var colaborador: Colaborador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerDatos()
        Log.d("LoginActivity", "Respuesta del servidor: $colaborador")


    }
    override fun onStart() {
        super.onStart()
        cargarDatos()

        binding.btnRegresar.setOnClickListener {
            regresarPerfil()
        }

        binding.btnGuardar.setOnClickListener {
            val colaborador :Colaborador = Colaborador(
                colaborador.idColaborador,
                binding.etCorreo.text.toString(),
                binding.etPassword.text.toString(),
                binding.etCurp.text.toString(),
                binding.etNombre.text.toString(),
                binding.etApellidoPaterno.text.toString(),
                binding.etApellidoMaterno.text.toString(),
                colaborador.noPersonal,
                binding.etLicencia.text.toString(),
                colaborador.foto,
                colaborador.idRol
                )
            if(sonCamposValidos()){
                editarCliente(colaborador)
                cerrarSesion()
                }
        }
    }

    private fun sonCamposValidos(): Boolean {
        var valido = true

        // validacion de campos vacíos nombre y apellidos
        if (binding.etNombre.text.isEmpty()) {
            valido = false
            binding.etNombre.error = "Campo Obligatorio"
        }

        if (binding.etApellidoPaterno.text.isEmpty()) {
            valido = false
            binding.etApellidoPaterno.error = "Campo Obligatorio"
        }

        if (binding.etApellidoMaterno.text.isEmpty()) {
            valido = false
            binding.etApellidoMaterno.error = "Campo Obligatorio"
        }

        if (binding.etCurp.text.isEmpty()) {
            valido = false
            binding.etCurp.error = "Campo Obligatorio"
        }

        if (binding.etCorreo.text.isEmpty()) {
            valido = false
            binding.etCorreo.error = "Campo Obligatorio"
        }

        if (binding.etLicencia.text.isEmpty()) {
            valido = false
            binding.etLicencia.error = "Campo Obligatorio"
        }

        if (binding.etPassword.text.isEmpty()) {
            valido = false
            binding.etPassword.error = "Campo Obligatorio"
        }

        // v de formato para nombres y apellidos (letras, espacios y acentos)
        if (!Regex("^[a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙäëïöüÄËÏÖÜñÑ ]+$")
                .matches(binding.etNombre.text.toString())) {
            valido = false
            mostrarAlerta("Nombre inválido", "Solo se permiten letras y acentos")
        }

        if (!Regex("^[a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙäëïöüÄËÏÖÜñÑ ]+$")
                .matches(binding.etApellidoPaterno.text.toString())) {
            valido = false
            mostrarAlerta("Apellido paterno inválido", "Solo se permiten letras y acentos")
        }

        if (!Regex("^[a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙäëïöüÄËÏÖÜñÑ ]+$")
                .matches(binding.etApellidoMaterno.text.toString())) {
            valido = false
            mostrarAlerta("Apellido materno inválido", "Solo se permiten letras y acentos")
        }

        // validacion de CURP acepta letras y numeros sin espacios y sin estar vacia
        val curp = binding.etCurp.text.toString()
        if (binding.etCurp.text.toString().isEmpty() ||
            !Regex("^[A-Za-z0-9]{16}$").matches(binding.etCurp.text.toString())) {
            valido = false
            mostrarAlerta("CURP inválida", "El CURP debe tener exactamente 18 caracteres, no puede estar vacío, no debe contener espacios y solo puede incluir letras y números.")
        }


        // correo (debe contener @ y terminar en .com, sin espacios)
        val correo = binding.etCorreo.text.toString()
        if (!Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$").matches(correo)) {
            valido = false
            mostrarAlerta("Correo inválido", "El correo debe contener @ y terminar en .com, sin espacios")
        }

        // licencia (letras y números, sin espacios)
        val licencia = binding.etLicencia.text.toString()
        if (!Regex("^[a-zA-Z0-9]+$").matches(licencia)) {
            valido = false
            mostrarAlerta("Licencia inválida", "Solo se permiten letras y números, sin espacios")
        }

        // sin espacios y que no sea vacia en passw
        if (binding.etPassword.text.isEmpty() || binding.etPassword.text.toString().contains(" ")) {
            valido = false
            mostrarAlerta("Contraseña inválida", "La contraseña no puede estar vacía ni contener espacios")
        }

        return valido
    }

    private fun mostrarAlerta(titulo: String, mensaje: String) {
        AlertDialog.Builder(this@EditarPerfilActivity)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun editarCliente(colaborador: Colaborador){
        val gson = Gson()
        val parametros = gson.toJson(colaborador)
        Ion.with(this@EditarPerfilActivity)
            .load("PUT", "${Constantes().URL_WS}/colaborador/editarColaborador")
            .setHeader("Content-Type", "application/json")
            .setStringBody(parametros)
            .asString()
            .setCallback { e, result ->
                if(e == null){
                    respuestaEdicion(result)
                } else {
                    Toast.makeText(this@EditarPerfilActivity, "e.message", Toast.LENGTH_LONG).show()
                }
            }
    }


    private fun respuestaEdicion(resultado: String){
        try {
            val gson = Gson()
            // Intenta deserializar como un objeto JSON
            if (resultado.trim().startsWith("{")) {
                val mensaje = gson.fromJson(resultado, Mensaje::class.java)
                Toast.makeText(this@EditarPerfilActivity, mensaje.mensaje, Toast.LENGTH_LONG).show()
                if (!mensaje.error) {
                    finish()
                }
                Log.i("EditarPerfilActivity", mensaje.mensaje)
            } else {
                // Si no es un objeto JSON, simplemente muestra el mensaje como cadena
                Toast.makeText(this@EditarPerfilActivity, resultado, Toast.LENGTH_LONG).show()
                Log.i("EditarPerfilActivity", resultado)
            }
        } catch (e: Exception) {
            Toast.makeText(this@EditarPerfilActivity, "Error al leer la respuesta del servicio", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }


    private fun cargarDatos(){

        binding.etNombre.setText(colaborador.nombre)
        binding.etApellidoPaterno.setText(colaborador.apellidoPaterno)
        binding.etApellidoMaterno.setText(colaborador.apellidoMaterno)
        binding.etCurp.setText(colaborador.curp)
        binding.etCorreo.setText(colaborador.correo)
        binding.etLicencia.setText(colaborador.numLicencia)
        binding.etPassword.setText(colaborador.contrasenia)
    }

    private fun obtenerDatos(){
        val jsonConductor = intent.getStringExtra("conductor")
        if (jsonConductor!=null){
            val gson = Gson()
            colaborador = gson.fromJson(jsonConductor, Colaborador::class.java)
            cargarDatos()
        }
    }

    fun cerrarSesion() {
        Toast.makeText(this, "Redirigiendo a cerrar sesion", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@EditarPerfilActivity, LoginActivity::class.java)
        startActivity(intent)
    }
    fun regresarPerfil() {
        Toast.makeText(this, "Redirigiendo a cerrar sesion", Toast.LENGTH_SHORT).show()
        val gson = Gson()
        val stringConductor = gson.toJson(colaborador)
        val intent = Intent(this@EditarPerfilActivity, GestionDatosActivity::class.java)
        intent.putExtra("conductor",stringConductor)
        startActivity(intent)
    }

}