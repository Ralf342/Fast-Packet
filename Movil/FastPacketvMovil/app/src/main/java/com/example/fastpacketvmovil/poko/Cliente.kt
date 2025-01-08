package com.example.fastpacketvmovil.poko

data class Cliente(
   val idCliente: Int,
   val nombre: String,
   val apellidoPaterno: String,
   val apellidoMaterno: String,
   val telefono: String,
   val correo: String,
   val codigoPostal: Int,
   val calle: String,
   val colonia: String,
   val numeroDeCasa: Int,
   val estado: String,
   val ciudad: String
)
