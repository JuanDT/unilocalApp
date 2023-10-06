package com.edu.eam.unilocalapp.actividades
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.bd.Usuarios
import com.edu.eam.unilocalapp.databinding.ActivityRegistroBinding
import com.edu.eam.unilocalapp.modelo.Usuario

class RegistroActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonRegistrar.setOnClickListener { registrarUsuario() }
    }

    private fun registrarUsuario() {
        val nombre = binding.editTextNombre.text.toString()
        val nickname = binding.editTextNickname.text.toString()
        val email = binding.editTextCorreo.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (nombre.isEmpty()) {
            binding.nombreLayout.error = getString(R.string.es_obligatorio)
            return
        } else {
            binding.nombreLayout.error = null
        }

        if (nickname.isEmpty()) {
            binding.nicknameLayout.error = getString(R.string.es_obligatorio)
            return
        } else if (nickname.length < 6) {
            binding.nicknameLayout.error = getString(R.string.minimo_caracteres_nick)
            return
        } else {
            binding.nicknameLayout.error = null
        }

        if (email.isEmpty()) {
            binding.correoLayout.error = getString(R.string.es_obligatorio)
            return
        } else {
            binding.correoLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordLayout.error = getString(R.string.es_obligatorio)
            return
        } else if (password.length < 8) {
            binding.passwordLayout.error = getString(R.string.contrasena_minimo_caracteres)
            return
        } else if (!password.matches(Regex("^(?=.*[A-Z])(?=.*[0-9]).+\$"))) {
            binding.passwordLayout.error = getString(R.string.contrasena_requisitos)
            return
        } else {
            binding.passwordLayout.error = null
        }

        if (Usuarios.listar().any { u -> u.correo == email }) {
            binding.correoLayout.error = getString(R.string.usuario_existente)
            return
        }

        val usuario = Usuario(4, nombre, nickname, email, password)
        Usuarios.agregar(usuario)

        binding.editTextNombre.text.clear()
        binding.editTextNickname.text.clear()
        binding.editTextCorreo.text.clear()
        binding.editTextPassword.text.clear()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

        Toast.makeText(this, getString(R.string.usuario_creado), Toast.LENGTH_LONG).show()
    }




}
