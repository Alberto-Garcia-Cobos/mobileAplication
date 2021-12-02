package com.example.pac_uf2_garciacobosalberto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pac_uf2_garciacobosalberto.databinding.Activity4Binding

/*
    PAC_UF2_PROGRAMACION MULTIMEDIA ILERNA
    24/11/2021 ALBERTO GARCIA COBOS
 */

class Activity4 : AppCompatActivity() {
    private lateinit var binding: Activity4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Boton volver al activity 1
        binding.BotonVolver4.setOnClickListener(){
            startActivity(Intent(this,Activity1::class.java))
        }
    }
}