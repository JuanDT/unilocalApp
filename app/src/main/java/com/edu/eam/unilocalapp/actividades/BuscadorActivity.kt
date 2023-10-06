package com.edu.eam.unilocalapp.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.fragmentos.MenuPrincipalFragment

class BuscadorActivity : AppCompatActivity() {

    private var isActivityOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador)
        supportActionBar?.hide()


    }
}