package com.example.recorderlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.*

class MainActivity : AppCompatActivity() {
    private lateinit var edNombre: EditText
    private lateinit var edCorreo: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnVer: Button

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private  var adapter: EstudianteAdapter?=null







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRecyclerVew()
        sqLiteHelper= SQLiteHelper(this)
        btnAgregar.setOnClickListener{addEstudiante()}
        btnVer.setOnClickListener{getEstudiante()}

    }

    private fun getEstudiante() {
        val stdList= sqLiteHelper.getAllEstudiante()
        Log.e("pppp" ,"${stdList.size}")

adapter?.addItems(stdList)
    }

    private fun addEstudiante() {

        val nombre=edNombre.text.toString()
        val correo=edCorreo.text.toString()
        if (nombre.isEmpty()|| correo.isEmpty()) {
            Toast.makeText(this, "Por favor ingresar los campos requeridos", Toast.LENGTH_SHORT).show()
        }  else {
            val std= EstudianteModel(nombre=nombre, correo = correo)
            val status= sqLiteHelper.insertEstudiantes(std)
                    if (status >-1) {
                        Toast.makeText(this,"Estudiante Agregado", Toast.LENGTH_SHORT).show()
                        limpiarTexto()
                        getEstudiante()
                    }else{
                        Toast.makeText(this,"Estudiante No Agregado", Toast.LENGTH_SHORT).show()
                    }
        }

    }

    private fun limpiarTexto() {
        edNombre.setText("")
        edCorreo.setText("")
        edNombre.requestFocus()
    }

    private fun initRecyclerVew() {
        recyclerView.layoutManager =LinearLayoutManager(this)
        adapter=EstudianteAdapter()
        recyclerView.adapter=adapter
    }

    private fun initView() {
        edNombre= findViewById(R.id.edNombre)
        edCorreo = findViewById (R.id.edCorreo)
        btnAgregar =findViewById(R.id.btnAgregar)
        btnVer= findViewById(R. id.btnVer)
        recyclerView=findViewById(R. id.recyclerView)
    }
}