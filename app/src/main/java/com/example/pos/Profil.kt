package com.example.pos

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

        idNama.text = ": "+nama
        idUsername.text = ": "+username

    }
}