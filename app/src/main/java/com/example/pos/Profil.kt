package com.example.pos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*

class Profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val id = intent.getStringExtra("id")
        val nama = intent.getStringExtra("nama")
        val username = intent.getStringExtra("username")
        val jenis_kelamin = intent.getStringExtra("jenis_kelamin")

        Toast.makeText(this, "Cek $id,$nama,$username,$jenis_kelamin", Toast.LENGTH_SHORT).show()

        idNama.text = ": "+nama
        idUsername.text = ": "+username
        idGender.text = ": "+jenis_kelamin


    }
}