package com.example.fastpacketvmovil.poko

data class Paquete(
   val idPaquete: Int,
    val peso: Float,
    val alto: Float,
    val descripcion: String,
    val numeroDeGuia: Int,
    val idUnidad: Int,
    val ancho: Float,
    val profundidad: Float
)
