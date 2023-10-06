package com.edu.eam.unilocalapp.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.adapter.LugarAdapter
import com.edu.eam.unilocalapp.bd.Lugares
import com.edu.eam.unilocalapp.databinding.ActivityResultadoBusquedaBinding
import com.edu.eam.unilocalapp.modelo.Lugar

class ResultadoBusquedaActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultadoBusquedaBinding
    var textoBusqueda:String = ""
    lateinit var listaLugares:ArrayList<Lugar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultadoBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        textoBusqueda = intent.extras!!.getString("texto", "")
        listaLugares = ArrayList()

        if(textoBusqueda.isNotEmpty()){
            listaLugares = Lugares.buscarNombre(textoBusqueda)
        }

        val adapter = LugarAdapter(listaLugares)
        binding.listaLugares.adapter = adapter
        binding.listaLugares.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}