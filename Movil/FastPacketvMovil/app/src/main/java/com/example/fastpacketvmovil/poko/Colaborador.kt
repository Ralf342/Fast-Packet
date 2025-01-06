package com.example.fastpacketvmovil.poko

data class Colaborador(
    val idColaborador: Int,
    val correo : String,
    val contrasenia : String,
    val curp : String,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val noPersonal: Int,
    val numLicencia: String?,
    val foto: String?,
    val idRol:Int
)
