package com.example.fastpacketvmovil


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
            if (sonCamposValido(noPersonal.toInt(),contrasenia)){
                verificarCredenciales(noPersonal.toInt(),contrasenia)
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


    fun sonCamposValido(noPersonal:Int, contrasenia:String):Boolean{
        var valido=true

        if (noPersonal.toString().isEmpty()){
            valido = false
            binding.etNumeroPersonal.setError("Numero personal es obligatorio")
        }
        if (contrasenia.isEmpty()){
            valido = false
            binding.etContrasenia.setError("Contraseña es obligatoria")
        }

        return valido
    }


    fun irPantallaPrincipal(jsonColaborador : String){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("conductor",jsonColaborador)
        Log.d("LoginActivity", "Respuesta del intent: $jsonColaborador")
        startActivity(intent)
        finish()
    }

}
