package com.example.recorderlist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context):
SQLiteOpenHelper(context, DATABASE_NANE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NANE = "estudiante.db"
        private const val TBL_ESTUDIANTE = "tbl_estudiante"
        private const val ID = "id"
        private const val NOMBRE = "nombre"
        private const val CORREO = "correo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = ("CREATE TABLE " + TBL_ESTUDIANTE + "("
                + ID + " INTEGER PRIMARY KEY," + NOMBRE + " TEXT,"
                + CORREO + " TEXT" + ")")
        db?.execSQL(createTblStudent)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newWersion: Int) {
        db!!.execSQL( "DROP TABLE IF EXISTS $TBL_ESTUDIANTE")
        onCreate(db)
    }

    fun insertEstudiantes (std: EstudianteModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put (ID, std.id)
        contentValues.put (NOMBRE, std.nombre)
        contentValues.put (CORREO, std.correo)

        val success = db.insert (TBL_ESTUDIANTE,  null, contentValues)
        db.close()
        return success

    }

    @SuppressLint("Range")
    fun getAllEstudiante() : ArrayList<EstudianteModel> {
    val stdList: ArrayList<EstudianteModel>  = ArrayList()
    val selectQuery= "SELECT * FROM $TBL_ESTUDIANTE"
    val db=this.readableDatabase
    val cursor: Cursor?
    try {
        cursor = db.rawQuery (selectQuery, null)
    }catch (e: Exception) {
        e.printStackTrace()
        db.execSQL(selectQuery)
        return ArrayList()
    }
        var id: Int
        var nombre: String
        var correo: String

        if (cursor.moveToFirst()) {
            do{
                id =cursor.getInt(cursor.getColumnIndex("id"))
            nombre= cursor.getString(cursor.getColumnIndex("nombre"))
            correo= cursor.getString(cursor.getColumnIndex("correo"))

                val std=EstudianteModel (id = id, nombre=nombre, correo=correo)
                stdList.add(std)
            }  while (cursor.moveToNext())
        }
        return stdList
    }
}
