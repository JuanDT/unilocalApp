package com.edu.eam.unilocalapp.modelo

class Moderador(id: Int, nombre: String, correo: String, password: String): Persona(id, nombre, correo, password) {

    override fun toString(): String {
        return "Moderador() ${super.toString()}"
    }

}