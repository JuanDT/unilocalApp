package com.edu.eam.unilocalapp.modelo

class Administrador(id: Int, nombre: String, correo: String, password: String): Persona(id, nombre, correo, password) {

    override fun toString(): String {
        return "Administrador() ${super.toString()}"
    }
}