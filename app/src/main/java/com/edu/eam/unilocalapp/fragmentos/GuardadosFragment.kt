package com.edu.eam.unilocalapp.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.databinding.FragmentGuardadosBinding


class GuardadosFragment : Fragment() {

    lateinit var binding: FragmentGuardadosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentGuardadosBinding.inflate(inflater, container, false)

        return binding.root;
    }


}