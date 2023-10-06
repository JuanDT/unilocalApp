package com.edu.eam.unilocalapp.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edu.eam.unilocalapp.databinding.FragmentInicioBinding


 class InicioFragment : Fragment() {

    lateinit var binding: FragmentInicioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInicioBinding.inflate(inflater, container, false)

        return binding.root
    }

}