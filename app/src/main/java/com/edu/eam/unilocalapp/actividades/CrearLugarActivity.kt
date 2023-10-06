package com.edu.eam.unilocalapp.actividades


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.edu.eam.unilocalapp.bd.Categorias
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.bd.Ciudades
import com.edu.eam.unilocalapp.databinding.ActivityCrearLugarBinding
import com.edu.eam.unilocalapp.bd.Lugares
import com.edu.eam.unilocalapp.modelo.Categoria
import com.edu.eam.unilocalapp.modelo.Ciudad
import com.edu.eam.unilocalapp.modelo.EstadoLugar
import com.edu.eam.unilocalapp.modelo.Lugar


class CrearLugarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCrearLugarBinding
    var posCiudad: Int = -1
    var posCategoria: Int = -1
    lateinit var ciudades: ArrayList<Ciudad>
    lateinit var categorias: ArrayList<Categoria>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearLugarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        ciudades = Ciudades.listar()
        categorias = Categorias.listar()

        cargarCiudades()
        cargarCategorias()

        binding.buttonAddPlace.setOnClickListener { crearNuevoLugar() }
    }

    fun cargarCiudades() {
        val listaCiudades = ciudades.map { ciudad -> ciudad.nombre }
        val adapterCiudades = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCiudades)
        adapterCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCiudad.adapter = adapterCiudades

        binding.spinnerCiudad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                posCiudad = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun cargarCategorias() {
        val listaCategorias = categorias.map { categoria -> categoria.nombre }
        val adapterCategorias = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCategorias)
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapterCategorias

        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                posCategoria = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun crearNuevoLugar() {
        val nombre = binding.editTextPlaceName.text.toString()
        val descripcion = binding.editTextPlaceDescription.text.toString()
        val telefono = binding.telefonoLayout.toString()
        val direccion = binding.editTextPlaceAddress.text.toString()
        val idCiudad = ciudades[posCiudad].id
        val idCategoria = categorias[posCategoria].id

        val sharedPreferences = getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val idUsuario = sharedPreferences.getInt("codigo_usuario", -1)
        if (nombre.isEmpty()) {
            binding.nombreLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.nombreLayout.error = null
        }

        if (descripcion.isEmpty()) {
            binding.descripcionLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.descripcionLayout.error = null
        }

        if (direccion.isEmpty()) {
            binding.direccionLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.direccionLayout.error = null
        }

        if (telefono.isEmpty()) {
            binding.telefonoLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.telefonoLayout.error = null
        }

        if (nombre.isNotEmpty() && descripcion.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty() && idCiudad != -1 && idCategoria != -1) {
            val nuevoLugar = Lugar(nombre, descripcion, idUsuario, EstadoLugar.SIN_REVISAR, idCategoria, direccion, 0f, 0f, idCiudad)

            val telefonos: ArrayList<String> = ArrayList()
            telefonos.add(telefono)

            nuevoLugar.telefonos = telefonos

            Lugares.crear(nuevoLugar)

            Toast.makeText(this, getString(R.string.lugar_creado), Toast.LENGTH_LONG).show()
        }
    }
}



