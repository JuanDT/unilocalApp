package com.edu.eam.unilocalapp.fragmentos


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.actividades.BuscadorActivity
import com.edu.eam.unilocalapp.actividades.LoginActivity
import com.edu.eam.unilocalapp.actividades.PerfilActivity
import com.edu.eam.unilocalapp.actividades.ResultadoBusquedaActivity
import com.edu.eam.unilocalapp.databinding.FragmentMenuPrincipalBinding

class MenuPrincipalFragment: Fragment() {

    lateinit var binding: FragmentMenuPrincipalBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)


        binding.editTextSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val busqueda = binding.editTextSearch.text.toString()

                if (busqueda.isNotEmpty()) {
                    val intent = Intent(activity, ResultadoBusquedaActivity::class.java)
                    intent.putExtra("texto", busqueda)

                    // Agregar la siguiente línea para mantener el fragmento actual abierto
                    intent.putExtra("mantener_fragmento_abierto", true)

                    startActivity(intent)
                }
            }
            true
        }

        binding.btnMenu.setOnClickListener { view ->
            showProfileMenu(view)
        }

        return binding.root
    }



    private fun showProfileMenu(view: View) {
        val context = requireContext()

        // Aplicar estilo al menú emergente (fondo de color crema)
        val popupStyle = ContextThemeWrapper(context, R.style.PopupMenuStyle)
        val popupMenu = PopupMenu(popupStyle, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.profile_menu, popupMenu.menu)
        val popupHelper = PopupMenu::class.java.getDeclaredField("mPopup")
        popupHelper.isAccessible = true
        val menuPopupHelper = popupHelper.get(popupMenu)
        val setForceIcons = menuPopupHelper.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
        setForceIcons.invoke(menuPopupHelper, true)

        // Muestra el menú emergente en la ubicación de la vista proporcionada
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.miPerfil -> {
                    // Abre la actividad de "Mi Perfil"
                    val intent = Intent(context, PerfilActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.ajustes -> {
                    // Abre la actividad de "Ajustes" (ajústalo según tu implementación)
                    // val intent = Intent(context, AjustesActivity::class.java)
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
    }





    private fun confirmLogout() {
        val context = requireContext()
        AlertDialog.Builder(context)
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
        val sharedPreferences = context?.getSharedPreferences("sesion", Context.MODE_PRIVATE)?.edit()
        sharedPreferences?.clear()?.apply()

        // Redirige al usuario a la pantalla de inicio de sesión
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Finaliza la actividad principal para evitar volver atrás
    }



}