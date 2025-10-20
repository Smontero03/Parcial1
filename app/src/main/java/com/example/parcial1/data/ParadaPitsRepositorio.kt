package com.example.parcial1.data

import android.content.ContentValues

import android.content.Context


class ParadaPitsRepositorio(context: Context) {
    private val dbHelper = ParadaPitsDB(context)

    fun insertar(p: ParadaPits) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("piloto", p.piloto)
            put("escuderia", p.escuderia)
            put("tiempo", p.tiempo)
            put("neumatico", p.neumatico)
            put("numNeumaticos", p.numNeumaticos)
            put("estado", p.estado)
            put("motivo", p.motivo)
            put("mecanico", p.mecanico)
            put("fechaHora", p.fechaHora)

        }
        db.insert("ParadaPits", null, values)
        db.close()
    }

    fun listar(): List<ParadaPits> {
        val lista = mutableListOf<ParadaPits>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ParadaPits", null)
        while (cursor.moveToNext()) {
            lista.add(
                ParadaPits(
                    id = cursor.getInt(0),
                    piloto = cursor.getString(1),
                    escuderia = cursor.getString(2),
                    tiempo = cursor.getDouble(3),
                    neumatico = cursor.getString(4),
                    numNeumaticos = cursor.getInt(5),
                    estado = cursor.getString(6),
                    motivo = cursor.getString(7),
                    mecanico = cursor.getString(8),
                    fechaHora = cursor.getString(9),

                )
            )
        }
        cursor.close()
        db.close()
        return lista
    }

    fun eliminar(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("ParadaPits", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    fun actualizar(p: ParadaPits) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("piloto", p.piloto)
            put("escuderia", p.escuderia)
            put("tiempo", p.tiempo)
            put("neumatico", p.neumatico)
            put("numNeumaticos", p.numNeumaticos)
            put("estado", p.estado)
            put("motivo", p.motivo)
            put("mecanico", p.mecanico)
            put("fechaHora", p.fechaHora)
        }
        db.update("ParadaPits", values, "id = ?", arrayOf(p.id.toString()))
        db.close()
    }

    fun calcularPromedioTiempo(): Double {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT AVG(tiempo) FROM ParadaPits", null)
        var promedio = 0.0
        if (cursor.moveToFirst()) {
            promedio = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
        return promedio
    }

    fun contarRegistros(): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM ParadaPits", null)
        var count = 0
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return count
    }

    fun obtenerMenorTiempo(): Double {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT MIN(tiempo) FROM ParadaPits", null)
        var minTiempo = 0.0
        if (cursor.moveToFirst()) {
            minTiempo = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
        return minTiempo
    }

    fun obtenerUltimasCincoParadas(): List<ParadaPits> {
        val lista = mutableListOf<ParadaPits>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ParadaPits ORDER BY id DESC LIMIT 5", null)
        while (cursor.moveToNext()) {
            lista.add(
                ParadaPits(
                    id = cursor.getInt(0),
                    piloto = cursor.getString(1),
                    escuderia = cursor.getString(2),
                    tiempo = cursor.getDouble(3),
                    neumatico = cursor.getString(4),
                    numNeumaticos = cursor.getInt(5),
                    estado = cursor.getString(6),
                    motivo = cursor.getString(7),
                    mecanico = cursor.getString(8),
                    fechaHora = cursor.getString(9),
                )
            )
        }
        cursor.close()
        db.close()
        return lista.reversed()
    }
}
