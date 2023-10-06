package com.edu.eam.unilocalapp.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.eam.unilocalapp.adapter.ComentarioAdapter
import com.edu.eam.unilocalapp.bd.Comentarios
import com.edu.eam.unilocalapp.databinding.FragmentComentariosLugarBinding
import com.edu.eam.unilocalapp.modelo.Comentario

class ComentariosLugarFragment : Fragment() {

    lateinit var binding: FragmentComentariosLugarBinding
    var lista:ArrayList<Comentario> = ArrayList()
    private var codigoLugar:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(arguments != null){
            codigoLugar = requireArguments().getInt("id_lugar")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComentariosLugarBinding.inflate(inflater, container, false)

        // Obtén la lista de comentarios para el lugar específico
        val listaComentarios = Comentarios.listar(codigoLugar)

        // Configura el RecyclerView con el adaptador personalizado
        val comentarioAdapter = ComentarioAdapter(listaComentarios)
        binding.recyclerViewComentarios.adapter = comentarioAdapter

        // Configura el diseño de los elementos del RecyclerView (puedes ajustar esto según tus necesidades)
        binding.recyclerViewComentarios.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }


    companion object{

        fun newInstance(codigoLugar:Int):ComentariosLugarFragment{
            val args = Bundle()
            args.putInt("id_lugar", codigoLugar)
            val fragmento = ComentariosLugarFragment()
            fragmento.arguments = args
            return fragmento
        }

    }

}