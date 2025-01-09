package com.example.fastpacketvmovil


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fastpacketvmovil.databinding.ActivityLoginBinding
import com.example.fastpacketvmovil.poko.LoginColaborador
import com.example.fastpacketvmovil.util.Constantes
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.koushikdutta.ion.Ion

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        binding.btnIniciarSesion.setOnClickListener {
            val noPersonal = binding.etNumeroPersonal.text.toString()
            val contrasenia = binding.etContrasenia.text.toString()
            if (sonCamposValido(noPersonal, contrasenia)){
                // solo se convierte a Int cuando se valida
                verificarCredenciales(noPersonal.toInt(), contrasenia)
            }
        }
    }

    fun verificarCredenciales(noPersonal: Int, contrasenia: String){
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)

        Ion.with(this@LoginActivity)
            .load("POST","${Constantes().URL_WS}/Login/conductor")
            .setHeader("Content-Type","application/x-www-form-urlencoded")
            .setBodyParameter("noPersonal",noPersonal.toString())
            .setBodyParameter("contrasenia",contrasenia)
            .asString()
            .setCallback { e, result ->
                if (e == null){
                    serializarRespuesta(result)
                    Log.d("LoginActivity", "Respuesta del result: $result")
                }else
                    Toast.makeText(this@LoginActivity,"error",Toast.LENGTH_LONG).show()
            }
    }


    fun serializarRespuesta(json:String){
        val gson = Gson()
        val resultadoLoginColaborador = gson.fromJson(json, LoginColaborador::class.java)
        Log.d("LoginActivity", "Respuesta del resultadoLOgin: $resultadoLoginColaborador")
        Toast.makeText(this@LoginActivity,resultadoLoginColaborador.mensaje, Toast.LENGTH_LONG).show()
        if (!resultadoLoginColaborador.error){
            Toast.makeText(this@LoginActivity,"serializacion",Toast.LENGTH_LONG).show()
            val jsonConductor = gson.toJson(resultadoLoginColaborador.colaborador)
            Log.d("LoginActivity", "Respuesta del servidor: $jsonConductor")
            irPantallaPrincipal(jsonConductor)
        }
    }

    fun sonCamposValido(noPersonal: String, contrasenia: String): Boolean {
        var valido = true

        // campos no vacios
        if (noPersonal.isEmpty()) {
            valido = false
            binding.etNumeroPersonal.error = "Número personal es obligatorio"
        }

        if (contrasenia.isEmpty()) {
            valido = false
            binding.etContrasenia.error = "Contraseña es obligatoria"
        }

        // formato del número personal
        /*if (!Regex("^\\d{4}$").matches(noPersonal)) {
            valido = false
            mostrarAlerta("Número personal inválido",
                "El número personal debe ser un número de 4 dígitos.")
        }*/

        // espacios en blanco
        if (noPersonal.contains(" ") || contrasenia.contains(" ")) {
            valido = false
            mostrarAlerta("Espacios en blanco",
                "No se permiten espacios en blanco en el número personal o contraseña.")
        }

        //formato de passwrd con caracteres
        //if (!Regex("^[a-zA-Z0-9#*/]{4,8}$").matches(contrasenia)) {
        /* valido = false
         mostrarAlerta("Contraseña inválida",
             "La contraseña puede entre 4 y 8 y sin caracteres")
     }*/

        //pass sin caracteres
        /*if (!Regex("^[a-zA-Z0-9]{4,12}$").matches(contrasenia)) {
            valido = false
            mostrarAlerta("Contraseña inválida",
                "La contraseña debe tener entre 4 y 8 caracteres y solo puede incluir letras y números")
        }*/

        return valido
    }

    fun mostrarAlerta(titulo: String, mensaje: String) {
        AlertDialog.Builder(this@LoginActivity)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    fun irPantallaPrincipal(jsonColaborador : String){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("conductor",jsonColaborador)
        Log.d("LoginActivity", "Respuesta del intent: $jsonColaborador")
        startActivity(intent)
        finish()
    }

}