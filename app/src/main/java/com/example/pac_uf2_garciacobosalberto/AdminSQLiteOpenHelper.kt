package com.example.pac_uf2_garciacobosalberto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper (context: Context ,name: String ,factory: SQLiteDatabase.CursorFactory? ,version: Int) : SQLiteOpenHelper(context, name, factory, version){

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("create table articulos(codigo int primary key, nombre text, precio real)")
        }

        override fun onUpgrade(db: SQLiteDatabase ,oldVersion: Int ,newVersion: Int) {

        }

    }
