package com.example.pac_uf2_garciacobosalberto

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pac_uf2_garciacobosalberto.databinding.Activity1Binding

/*
    PAC_UF2_PROGRAMACION MULTIMEDIA ILERNA
    24/11/2021 ALBERTO GARCIA COBOS
 */

class Activity1 : AppCompatActivity() {

    private lateinit var binding: Activity1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creamos el objeto media player
        val mediaPlayer = MediaPlayer.create(this,R.raw.queen)
        val position = 0

        //creamos el evento play
        binding.btnPlay.setOnClickListener(){
            if (mediaPlayer.isPlaying == false){
                mediaPlayer.seekTo(position)
                mediaPlayer.start()
            }else mediaPlayer.start()
        }

        //Creamos el evento stop
        binding.btnStop.setOnClickListener(){
            mediaPlayer.pause()
            mediaPlayer.seekTo(position)
        }
        //Evento pasar activity 2
        binding.boton1.setOnClickListener(){
            startActivity(Intent(this,Activity2::class.java))
        }
        //Evento pasar activity 3
        binding.boton2.setOnClickListener(){
            startActivity(Intent(this,Activity3::class.java))
        }
        //Evento pasar activity 4
        binding.boton3.setOnClickListener(){
            startActivity(Intent(this,Activity4::class.java))
        }




    }


}