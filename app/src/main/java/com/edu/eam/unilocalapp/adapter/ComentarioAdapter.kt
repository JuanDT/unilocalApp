package com.edu.eam.unilocalapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.databinding.ItemComentarioBinding
import com.edu.eam.unilocalapp.modelo.Comentario
import com.edu.eam.unilocalapp.bd.Usuarios
import java.text.SimpleDateFormat
import java.util.Locale

class ComentarioAdapter(private val listaComentarios: List<Comentario>) : RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder>() {

    var usuarios = Usuarios;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioViewHolder {
        val binding = ItemComentarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComentarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
        holder.bind(listaComentarios[position])
    }

    override fun getItemCount() = listaComentarios.size

    inner class ComentarioViewHolder(private val view: ItemComentarioBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(comentario: Comentario) {

            var nickname =   usuarios.obtener(comentario.idUsuario)

            if (nickname != null) {
                view.nombreUsuario.text = nickname.nickname
            }
            val formatoFecha = SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", Locale.getDefault())

            view.fechaComentario.text = formatoFecha.format(comentario.fecha)
            view.textoComentario.text = comentario.texto


            var calificacion =   comentario.calificacion
            if (calificacion == 1) {
                view.star1.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == 2) {
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == 3) {
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
                view.star3.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == 4) {
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
                view.star3.setImageResource(R.drawable.baseline_star_24);
                view.star4.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == 5){
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
                view.star3.setImageResource(R.drawable.baseline_star_24);
                view.star4.setImageResource(R.drawable.baseline_star_24);
                view.star5.setImageResource(R.drawable.baseline_star_24);
            }
        }
    }
}
