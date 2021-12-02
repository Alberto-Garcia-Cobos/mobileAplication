package com.example.pac_uf2_garciacobosalberto

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.pac_uf2_garciacobosalberto.databinding.*

/*
    PAC_UF2_PROGRAMACION MULTIMEDIA ILERNA
    24/11/2021 ALBERTO GARCIA COBOS
 */

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this,"Entrando en la activity 2", Toast.LENGTH_LONG).show()
        agregarArticuloBaseDatos()
        consultarBaseDatos()


        //Boton para vol    ver al main
        binding.botonVolver2.setOnClickListener() {
            startActivity(Intent(this ,Activity1::class.java))
             }
        }

        fun consultarBaseDatos() {
            //Boton para consultar un articulo de la tabla
            try {
                binding.consultaTabla.setOnClickListener() {
                        val admin = AdminSQLiteOpenHelper(this ,"administracion" ,null ,1)
                        val bd = admin.writableDatabase
                        val fila =
                            bd.rawQuery("select nombre, precio from articulos where codigo = " +
                                    "${binding.textInsertaCodigo.text.toString()}" ,
                                null)
                        if (fila.moveToFirst()) {
                            binding.textInsertaProducto.setText(fila.getString(0))
                            binding.textInsertaPrecio.setText(fila.getString(1))
                        } else {
                            Toast.makeText(this ,
                                "No existe un artículo con dicho código" ,
                                Toast.LENGTH_SHORT).show()

                            bd.close()
                        }
                    }

            } catch (e: Exception) {
                Toast.makeText(this ,"Debes introducir el código ha buscar" ,
                    Toast.LENGTH_SHORT).show()
            }
        }


        fun agregarArticuloBaseDatos() {
            //Boton para agregar un articulo a la base de datos
            try {
                binding.insertaTabla.setOnClickListener() {
                    val admin = AdminSQLiteOpenHelper(this ,"administracion" ,null ,1)
                    val bd = admin.writableDatabase
                    val registro = ContentValues()
                    registro.put("codigo" ,binding.textInsertaCodigo.getText().toString())
                    registro.put("nombre" ,binding.textInsertaProducto.getText().toString())
                    registro.put("precio" ,binding.textInsertaPrecio.getText().toString())
                    bd.insert("articulos" ,null ,registro)
                    bd.close()
                    binding.textInsertaCodigo.setText("")
                    binding.textInsertaProducto.setText("")
                    binding.textInsertaPrecio.setText("")
                    Toast.makeText(this ,"Se insertaron los datos del producto" ,
                        Toast.LENGTH_SHORT).show()

                }
            }catch (e:Exception){

                Toast.makeText(this ,"No introduciste todos los datos" ,
                    Toast.LENGTH_SHORT).show()
            }
        }
        fun validaConsuta(){
            if (binding.textInsertaCodigo.text.isEmpty()){
                Toast.makeText(this, "Inserta el código ha buscar",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
