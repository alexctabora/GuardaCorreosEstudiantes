package com.example.recorderlist
import java.util.*

data class EstudianteModel(
    var id: Int = getAutoId(),
    var nombre: String="",
    var correo: String=""
){
    companion object{
        fun getAutoId (): Int{
        val random = Random()
        return random.nextInt (100)

       }
    }
  }