package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profil.*

class Profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val id = intent.getStringExtra("id")
        val nama = intent.getStringExtra("nama")
        val username = intent.getStringExtra("username")
        val jenis_kelamin = intent.getStringExtra("jenis_kelamin")

        idNama.text = ": "+nama
        idUsername.text = ": "+username
        idGender.text = ": "+jenis_kelamin

        btnUbah.setOnClickListener{
            val intent = Intent(this, EditProfil::class.java)
            intent.putExtra("id", id)
            intent.putExtra("nama", nama)
            intent.putExtra("username", username)
            intent.putExtra("jenis_kelamin", jenis_kelamin)
            startActivity(intent)
        }
    }
}