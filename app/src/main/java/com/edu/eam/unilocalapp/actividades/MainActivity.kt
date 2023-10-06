package com.edu.eam.unilocalapp.actividades
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.databinding.ActivityMainBinding
import com.edu.eam.unilocalapp.fragmentos.ComunidadFragment
import com.edu.eam.unilocalapp.fragmentos.GuardadosFragment
import com.edu.eam.unilocalapp.fragmentos.InicioFragment

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private var MENU_INICIO = "mexplorar"
    private var MENU_COMUNIDAD = "comunidad"
    private var MENU_GUARDADOS = "guardados"
    private var currentFragmentId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reemplazarFragmento(1, MENU_INICIO)

        supportActionBar?.hide()

        // Configurar el listener del BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_explorar -> reemplazarFragmento(1, MENU_INICIO)
                R.id.menu_comunidad -> reemplazarFragmento(2, MENU_COMUNIDAD)
                R.id.menu_guardados -> reemplazarFragmento(3, MENU_GUARDADOS)
            }
            true
        }

    }



    private fun showProfileMenu(view: View) {

        // Aplicar estilo al menú emergente (fondo de color crema)
        val popupStyle = ContextThemeWrapper(this, R.style.PopupMenuStyle)
        val popupMenu = PopupMenu(popupStyle, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.profile_menu, popupMenu.menu)
        val popupHelper = PopupMenu::class.java.getDeclaredField("mPopup")
        popupHelper.isAccessible = true
        val menuPopupHelper = popupHelper.get(popupMenu)
        val setForceIcons = menuPopupHelper.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
        setForceIcons.invoke(menuPopupHelper, true)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.miPerfil -> {
                    // Abre la actividad de "Mi Perfil"
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.ajustes -> {
                    // Abre la actividad de "Ajustes" (ajústalo según tu implementación)
                    // val intent = Intent(this, AjustesActivity::class.java)
                    // startActivity(intent)
                    true
                }

                R.id.logout -> {
                    // Realiza el logout
                    confirmLogout()
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    fun reemplazarFragmento(valor: Int, nombre: String) {
        var fragmento: Fragment = when (valor) {
            1 -> InicioFragment()
            2 -> ComunidadFragment()
            else -> GuardadosFragment()
        }

        // Realiza la transacción de fragmentos
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.contenidoPrincipal.id, fragmento)

        // Verifica si el fragmento es InicioFragment
        if (fragmento is InicioFragment) {
            // Muestra el fragmento superior (barra_superior)
            val barraSuperiorFragment = supportFragmentManager.findFragmentById(R.id.barra_superior)
            transaction.show(barraSuperiorFragment!!)
        } else {
            // Oculta el fragmento superior (barra_superior)
            val barraSuperiorFragment = supportFragmentManager.findFragmentById(R.id.barra_superior)
            transaction.hide(barraSuperiorFragment!!)
        }

        // Agrega la transacción a la pila de retroceso
        transaction.addToBackStack(nombre)
        transaction.commit()
        // Actualizar el ID del fragmento actual
        currentFragmentId = valor
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount

        if(count > 0) {
            val nombre = supportFragmentManager.getBackStackEntryAt(count - 1).name
            when (nombre) {
                MENU_INICIO -> binding.bottomNavigationView.menu.getItem(0).isChecked = true
                MENU_GUARDADOS -> binding.bottomNavigationView.menu.getItem(1).isChecked = true
                else -> binding.bottomNavigationView.menu.getItem(2).isChecked = true
            }
        }

    }


    private fun confirmLogout() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro de que deseas cerrar sesión?")
            .setPositiveButton("Sí") { dialog, _ ->
                logout()
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun logout() {
        // Elimina los datos de sesión (por ejemplo, utilizando SharedPreferences)
        val sharedPreferences = getSharedPreferences("sesion", Context.MODE_PRIVATE).edit()
        sharedPreferences.clear().apply()

        // Redirige al usuario a la pantalla de inicio de sesión
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finaliza esta actividad para evitar volver atrás
    }
}