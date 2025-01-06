package com.example.fastpacketvmovil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fastpacketvmovil.databinding.ActivityLoginBinding
import com.example.fastpacketvmovil.poko.LoginConductor
import com.example.fastpacketvmovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun verificarCredenciales(noPersonal: Int, contrasenia: String){
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)

        Ion.with(this@LoginActivity)
            .load("POST","${Constantes().URL_WS}/Login/colaborador")
            .setHeader("Content-Type","application/x-www-form-urlencoded")
            .setBodyParameter("noPersonal",noPersonal.toString())
            .setBodyParameter("contrasenia",contrasenia)
            .asString()
            .setCallback { e, result ->
                if (e == null){
                    serializarRespuesta(result)
                }else
                    Toast.makeText(this@LoginActivity,"error",Toast.LENGTH_LONG).show()
            }
    }


    fun serializarRespuesta(json:String){
        val gson = Gson()
        val resultadoLoginConductor = gson.fromJson(json,LoginConductor::class.java)
        Toast.makeText(this@LoginActivity,resultadoLoginConductor.mensaje, Toast.LENGTH_LONG).show()
        if (!resultadoLoginConductor.error){
            Toast.makeText(this@LoginActivity,"serializacion",Toast.LENGTH_LONG).show()
            val jsonConductor = gson.toJson(resultadoLoginConductor.conductor)
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


    fun irPantallaPrincipal(jsonConductor : String){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("conductor",jsonConductor)
        startActivity(intent)
        finish()
    }
}