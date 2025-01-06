package com.example.fastpacketvmovil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fastpacketvmovil.databinding.ActivityGestionDatosBinding
import com.example.fastpacketvmovil.poko.Colaborador
import com.google.gson.Gson

class GestionDatosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGestionDatosBinding
    private lateinit var colaborador: Colaborador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGestionDatosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerDatos()

    }

    override fun onStart() {
        super.onStart()
        binding.btnVolvera.setOnClickListener {
            regresarMain()
        }
        binding.btnActualizar.setOnClickListener {
            irEditarPerfil()
        }
        cargarDatos()
    }

    private fun sonCamposValidos(): Boolean{
        var valido = true
        if (binding.tvNombre.text.isEmpty()){
            valido = false
            binding.tvNombre.error = "Campo obligatorio"
        }
        if (binding.tvApellidoPaterno.text.isEmpty()){
            valido = false
            binding.tvApellidoPaterno.error = "Campo obligatorio"
        }
        if (binding.tvApellidoMaterno.text.isEmpty()){
            valido = false
            binding.tvApellidoMaterno.error = "Campo obligatorio"
        }
        if (binding.tvCURP.text.isEmpty()){
            valido = false
            binding.tvCURP.error = "Campo obligatorio"
        }
        if (binding.tvCorreo.text.isEmpty()){
            valido = false
            binding.tvCorreo.error = "Campo obligatorio"
        }
        return valido
    }

    private fun cargarDatos(){

        binding.tvNombre.setText(colaborador.nombre)
        binding.tvApellidoPaterno.setText(colaborador.apellidoPaterno)
        binding.tvApellidoMaterno.setText(colaborador.apellidoMaterno)
        binding.tvCURP.setText(colaborador.curp)
        binding.tvCorreo.setText(colaborador.correo)
        binding.tvLicencia.setText(colaborador.numLicencia)
    }

    private fun obtenerDatos(){
        val jsonConductor = intent.getStringExtra("conductor")
        if (jsonConductor!=null){
            val gson = Gson()
            colaborador = gson.fromJson(jsonConductor, Colaborador::class.java)
            cargarDatos()
        }
    }
//listener
    fun actualizarDatos(view: View) {
        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
    }

    fun irEditarPerfil() {
        Toast.makeText(this, "Redirigiendo a Mi perfil", Toast.LENGTH_SHORT).show()
        val gson = Gson()
        val stringConductor = gson.toJson(colaborador)
        val intent = Intent(this@GestionDatosActivity, EditarPerfilActivity::class.java)
        intent.putExtra("conductor",stringConductor)
        startActivity(intent)
    }
    fun regresarMain() {
        Toast.makeText(this, "Redirigiendo a Main", Toast.LENGTH_SHORT).show()
        val gson = Gson()
        val stringConductor = gson.toJson(colaborador)
        val intent = Intent(this@GestionDatosActivity, MainActivity::class.java)
        intent.putExtra("conductor",stringConductor)
        startActivity(intent)
    }

}