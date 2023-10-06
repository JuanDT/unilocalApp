package com.edu.eam.unilocalapp.adapter



import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.edu.eam.unilocalapp.modelo.Lugar
import com.edu.eam.unilocalapp.actividades.DetalleLugarActivity
import com.edu.eam.unilocalapp.bd.Categorias
import com.edu.eam.unilocalapp.databinding.ItemLugarBinding
import com.edu.eam.unilocalapp.R
import com.edu.eam.unilocalapp.bd.Comentarios

class LugarAdapter(private var lista:ArrayList<Lugar>): RecyclerView.Adapter<LugarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ItemLugarBinding.inflate( LayoutInflater.from(parent.context), parent, false )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind( lista[position] )


    }

    override fun getItemCount() = lista.size

    inner class ViewHolder(private var view:ItemLugarBinding):RecyclerView.ViewHolder(view.root), View.OnClickListener{

        private var codigoLugar:Int = 0

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(lugar: Lugar){
            view.nombreLugar.text = lugar.nombre
            view.direccionLugar.text = lugar.direccion

            val estado = lugar.estaAbierto()

            if(estado == "Abierto"){
                view.estadoLugar.setTextColor( ContextCompat.getColor(itemView.context, R.color.verde ) )
                view.horarioLugar.text = "Cierra a las ${lugar.obtenerHoraCierre()}:00"
            }else{
                view.estadoLugar.setTextColor( ContextCompat.getColor(itemView.context, R.color.rojo ) )
                view.horarioLugar.visibility = View.GONE
            }

            val calificacion = lugar.obtenerCalificacionPromedio( Comentarios.listar(lugar.id) ).toString()

            if (calificacion == "1") {
                view.star1.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == "2") {
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == "3") {
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
                view.star3.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == "4") {
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
                view.star3.setImageResource(R.drawable.baseline_star_24);
                view.star4.setImageResource(R.drawable.baseline_star_24);
            }
            if (calificacion == "5"){
                view.star1.setImageResource(R.drawable.baseline_star_24);
                view.star2.setImageResource(R.drawable.baseline_star_24);
                view.star3.setImageResource(R.drawable.baseline_star_24);
                view.star4.setImageResource(R.drawable.baseline_star_24);
                view.star5.setImageResource(R.drawable.baseline_star_24);
            }
            view.estadoLugar.text = lugar.estaAbierto()
            view.iconoLugar.text = Categorias.obtener(lugar.idCategoria)!!.icono
            codigoLugar = lugar.id
        }

        override fun onClick(p0: View?) {
            val intent = Intent( view.root.context, DetalleLugarActivity::class.java )
            intent.putExtra("codigo", codigoLugar)
            view.root.context.startActivity(intent)
        }

    }

}