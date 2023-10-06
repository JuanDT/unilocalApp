package com.edu.eam.unilocalapp.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.adapter.ViewPagerAdapter
import com.edu.eam.unilocalapp.databinding.ActivityDetalleLugarBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetalleLugarActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetalleLugarBinding
    private var codigoLugar:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalleLugarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        codigoLugar = intent.extras!!.getInt("codigo")

        if(codigoLugar != 0) {

            binding.viewPager.adapter = ViewPagerAdapter(this, codigoLugar)
            TabLayoutMediator(binding.tabs, binding.viewPager) { tab, pos ->
                when (pos) {
                    0 -> tab.text = getString(R.string.info_lugar)
                    1 -> tab.text = getString(R.string.comentarios)
                }
            }.attach()

        }

    }
}