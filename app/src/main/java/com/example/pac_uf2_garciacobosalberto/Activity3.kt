package com.example.pac_uf2_garciacobosalberto

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.pac_uf2_garciacobosalberto.databinding.Activity3Binding

/*
    PAC_UF2_PROGRAMACION MULTIMEDIA ILERNA
    24/11/2021 ALBERTO GARCIA COBOS
 */

class Activity3 : AppCompatActivity() {
    //Creamos variables
    private lateinit var binding: Activity3Binding
    private val REQUEST_CAMERA = 1001

    var foto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Boton volver al activity principal
        binding.BotonVolver3.setOnClickListener() {
            startActivity(Intent(this ,Activity1::class.java))
        }
        //Iniciamos la funcion camara
        abreCamera_Click()
    }

    //Mostramos la foto en la imagen
    override fun onActivityResult(requestCode: Int ,resultCode: Int ,data: Intent?) {
        super.onActivityResult(requestCode ,resultCode ,data)
        //Si toma la foto y la solicitud es de la camara
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CAMERA) {
            //La mostramos en la imagen del layaout
            binding.imgFoto.setImageURI(foto)
        }
    }

    //Activamos los permisos
    override fun onRequestPermissionsResult(
        //Configuramos todos los permisos
        requestCode: Int ,
        permissions: Array<out String> ,
        grantResults: IntArray ,
    ) {
        super.onRequestPermissionsResult(requestCode ,permissions ,grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> {
                //Creamos condición para ver si están aceptados los permisos
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abreCamara()
                } else
                    //Si no acepta, se le deniega el acceso
                    Toast.makeText(applicationContext ,
                        "No puedes acceder a la camara" ,
                        Toast.LENGTH_SHORT).show()
            }
        }
    }


    //Detectamos cuando se pulse el boton para abrir la camera para mostrar los permisos
    private fun abreCamera_Click() {
        binding.btnCamara.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                ) {
                    //Pedirle permiso al usuario y que los acepte
                    val permisosCamara = arrayOf(android.Manifest.permission.CAMERA ,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permisosCamara ,REQUEST_CAMERA)
                } else abreCamara()

            } else abreCamara()
        }
    }

    //Abre la camara del telefono
    private fun abreCamara() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE ,"Nueva imagen")
        //Guardamos la foto en la variable foto que es una URI
        foto = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI ,values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //Lo guardamos para poder mostrarla en el imagenView
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT ,foto)
        startActivityForResult(cameraIntent ,REQUEST_CAMERA)
    }
}