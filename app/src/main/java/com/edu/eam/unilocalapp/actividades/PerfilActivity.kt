package com.edu.eam.unilocalapp.actividades

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.edu.eam.unilocalapp.R

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val sharedPreferences = getSharedPreferences("sesion", Context.MODE_PRIVATE)

        val nombreUsuario = sharedPreferences.getString("nombre_usuario", "")
        val nicknameUsuario = sharedPreferences.getString("nickname_usuario", "")
        val correoUsuario = sharedPreferences.getString("correo_usuario", "")

        val nombreTextView = findViewById<TextView>(R.id.nombreUsuario)
        val nicknameTextView = findViewById<TextView>(R.id.nicknameUsuario)
        val correoTextView = findViewById<TextView>(R.id.correoUsuario)

        nombreTextView.text = nombreUsuario
        nicknameTextView.text = nicknameUsuario
        correoTextView.text = correoUsuario

        val editarPerfilButton = findViewById<View>(R.id.botonEditarPerfil)

        editarPerfilButton.setOnClickListener {
            val intent = Intent(this, EditarPerfilActivity::class.java)
            startActivity(intent)
        }
    }
}