package com.example.parcial1.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ParadaPitsDB(context: Context): SQLiteOpenHelper(context,"ParadaPits.db",null,1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE ParadaPits(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
              piloto TEXT,
              escuderia TEXT,
              tiempo REAL,
              neumatico TEXT,
              numNeumaticos INTEGER,
              estado TEXT,
              motivo TEXT,
              mecanico TEXT,
              fechaHora TEXT
            )
        """.trimIndent())

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ParadaPits")
        onCreate(db)
    }


}