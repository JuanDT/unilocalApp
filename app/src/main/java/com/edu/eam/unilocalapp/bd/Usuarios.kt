package com.edu.eam.unilocalapp.bd

import com.edu.eam.unilocalapp.modelo.Usuario

object Usuarios {

    private val lista:ArrayList<Usuario> = ArrayList()

    init {

        lista.add( Usuario(1, "Carlos", "carlos", "carlos@email.com", "1203") )
        lista.add( Usuario(2, "Pepito", "pepe", "pepe@email.com", "3451") )
        lista.add( Usuario(3, "Laura", "laura", "laura@email.com", "6543") )
        lista.add( Usuario(4, "Marcos", "marcos", "marcos@email.com", "8635") )
        lista.add( Usuario(5, "Maria", "maria", "maria@email.com", "5437") )
        lista.add(Usuario(6, "juan", "juandt", "juandavidtoromesa2@gmail.com", "A12345"))

    }

    fun listar():ArrayList<Usuario>{
        return lista
    }

    fun agregar(usuario: Usuario){
        lista.add(usuario)
    }

    fun obtener(id:Int): Usuario?{
        return lista.firstOrNull { u -> u.id == id }
    }


}