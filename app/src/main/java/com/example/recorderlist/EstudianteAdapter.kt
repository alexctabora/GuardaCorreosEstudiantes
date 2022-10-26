package com.example.recorderlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EstudianteAdapter : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>(){

    private var stdList: ArrayList<EstudianteModel> = ArrayList()

    fun addItems(items:ArrayList<EstudianteModel>){
        this.stdList= items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= EstudianteViewHolder (
   LayoutInflater.from(parent.context).inflate(R.layout.card_item_estudiantes,parent,false)
    )

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        var std= stdList[position]
        holder.bindView(std)
    }

    override fun getItemCount(): Int {
     return stdList.size
    }


            class EstudianteViewHolder (var view: View):RecyclerView.ViewHolder(view){
                private var id = view.findViewById<TextView>(R.id.tvId)
                private var nombre = view.findViewById<TextView>(R.id.tvNombre)
                private var correo = view.findViewById<TextView>(R.id.tvCorreo)
                private var btnEliminar = view.findViewById<Button>(R.id.btnEliminar)

                fun bindView(std:EstudianteModel) {
                    id.text = std.id.toString()
                    nombre.text=std.nombre
                    correo.text = std.correo
                }
            }
}
