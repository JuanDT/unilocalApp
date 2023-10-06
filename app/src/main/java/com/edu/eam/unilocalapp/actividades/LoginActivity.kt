package com.edu.eam.unilocalapp.actividades

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.databinding.ActivityLoginBinding
import com.edu.eam.unilocalapp.modelo.Administrador
import com.edu.eam.unilocalapp.modelo.Moderador
import com.edu.eam.unilocalapp.modelo.Usuario
import com.edu.eam.unilocalapp.bd.Personas
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.edu.eam.unilocalapp.bd.Usuarios
import com.edu.eam.unilocalapp.modelo.Persona

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var sharedPreferences: SharedPreferences

    lateinit var usuario: Persona;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("sesion", Context.MODE_PRIVATE)

        val loggedInUserType = sharedPreferences.getString("tipo_usuario", null)

        if (loggedInUserType != null) {
            // Si hay un usuario con sesión activa, redirige a la actividad correspondiente
            when (loggedInUserType) {
                "usuario" -> startActivity(Intent(this, MainActivity::class.java))
                "moderador" -> startActivity(Intent(this, ModeradorActivity::class.java))
                "admin" -> startActivity(Intent(this, AdministradorActivity::class.java))
            }
            finish() // Finaliza esta actividad para evitar volver atrás
        }

        binding.buttonLogin.setOnClickListener { login() }
        binding.textViewRegister.setOnClickListener { irRegistro() }
    }

    object SesionUsuario {
        private var usuario: Persona? = null

        fun iniciarSesion(usuario: Persona) {
            this.usuario = usuario
        }

        fun cerrarSesion() {
            usuario = null
        }

        fun obtenerUsuario(): Persona? {
            return usuario
        }
    }


    private fun irRegistro() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        val correo = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (correo.isEmpty()) {
            binding.emailLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.emailLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.passwordLayout.error = null
        }

        if (correo.isNotEmpty() && password.isNotEmpty()) {
            try {
                val persona = Personas.login(correo.toString(), password.toString())

                if(persona!=null){

                    val tipo = if( persona is Usuario ) "usuario" else if( persona is Moderador ) "moderador" else "admin"

                    var usuarioEncontrado: Usuarios = Usuarios

                    var usuarioNick = usuarioEncontrado.obtener(persona.id)

                    val sharedPreferences = this.getSharedPreferences( "sesion", Context.MODE_PRIVATE ).edit()
                    sharedPreferences.putInt("codigo_usuario", persona.id)
                    sharedPreferences.putString("correo_usuario", persona.correo)
                    sharedPreferences.putString("nombre_usuario", persona.nombre)

                        if (usuarioNick != null) {
                            sharedPreferences.putString("nickname_usuario", usuarioNick.nickname)
                        }


                    sharedPreferences.putString("tipo_usuario", tipo)

                    sharedPreferences.commit()

                    when(persona){
                        is Usuario -> startActivity( Intent(this, MainActivity::class.java) )
                        is Moderador -> startActivity( Intent(this, ModeradorActivity::class.java) )
                        is Administrador -> startActivity( Intent(this, AdministradorActivity::class.java)
                        )
                    }

                    usuario.id = persona.id
                    println("id: "+usuario.id)
                }else{
                    Toast.makeText(this, getString(R.string.datos_incorrectos), Toast.LENGTH_LONG).show()
                }

            }catch (e:Exception){
                Toast.makeText(this, getString(R.string.datos_incorrectos), Toast.LENGTH_LONG).show()
            }
        }
    }
}
