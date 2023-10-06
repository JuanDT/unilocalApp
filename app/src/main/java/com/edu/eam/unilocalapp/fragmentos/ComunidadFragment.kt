package com.edu.eam.unilocalapp.fragmentos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.eam.unilocalapp.actividades.CrearLugarActivity
import com.edu.eam.unilocalapp.adapter.LugarAdapter
import com.edu.eam.unilocalapp.bd.Horarios.lista
import com.edu.eam.unilocalapp.bd.Lugares
import com.edu.eam.unilocalapp.databinding.FragmentComunidadBinding
import com.edu.eam.unilocalapp.modelo.Lugar


class ComunidadFragment : Fragment() {

    lateinit var binding: FragmentComunidadBinding
    var lista:ArrayList<Lugar> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComunidadBinding.inflate(inflater, container, false)

        binding.fabAddPlace.setOnClickListener { irACrearLugar() }

        val sp = requireActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val codigoUsuario = sp.getInt("codigo_usuario", -1)

        if( codigoUsuario != -1 ){
            lista = Lugares.listarPorPropietario(codigoUsuario)

            val adapter = LugarAdapter(lista)
            binding.recyclerViewLugares.adapter = adapter
            binding.recyclerViewLugares.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }


        return binding.root
    }

    private fun irACrearLugar(){
        startActivity( Intent(activity, CrearLugarActivity::class.java) )
    }

}