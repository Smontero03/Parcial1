package com.example.parcial1.data

import android.content.Context

class AuxiliarParadaPits(context: Context): ParadaPitsDB(context) {

    init {
        writableDatabase.execSQL("DROP TABLE IF EXISTS ParadaPits")
        onCreate(writableDatabase)
    }
}