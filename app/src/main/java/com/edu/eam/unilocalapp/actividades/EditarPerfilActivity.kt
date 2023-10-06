package com.edu.eam.unilocalapp.actividades

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.text.Editable

import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.databinding.ActivityDetalleLugarBinding
import com.edu.eam.unilocalapp.databinding.ActivityEditarPerfilBinding

class EditarPerfilActivity : AppCompatActivity() {


    private lateinit var binding: ActivityEditarPerfilBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)
        supportActionBar?.hide()

        // Obtener los datos del usuario actual desde las preferencias compartidas
       // val sharedPreferences = getSharedPreferences("sesion", Context.MODE_PRIVATE)
       // val nombreUsuario = sharedPreferences.getString("nombre_usuario", "")
       // val nicknameUsuario = sharedPreferences.getString("nickname_usuario", "")
      //  val correoUsuario = sharedPreferences.getString("correo_usuario", "")

        // Establecer los datos del usuario en los EditText utilizando View Binding
       // binding.editTextName.text = nombreUsuario?.toEditable()
       // binding.editTextNickname.text = nicknameUsuario?.toEditable()
       // binding.editTextEmail.text = correoUsuario?.toEditable()

    }

    private fun String?.toEditable(): Editable {
        return Editable.Factory.getInstance().newEditable(this)
    }
}