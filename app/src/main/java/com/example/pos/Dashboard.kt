package com.example.pos

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        var cv_menu = findViewById<CardView>(R.id.cvMenu)
        var cv_profil = findViewById<CardView>(R.id.cvProfil)
        var cv_list = findViewById<CardView>(R.id.cvList)
        var cv_logout = findViewById<CardView>(R.id.cvLogout)

        cv_menu.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        cv_list.setOnClickListener {
            val intent = Intent(this,ListPembeli::class.java)
            startActivity(intent)
        }

        cv_profil.setOnClickListener {
            val intent = Intent(this,Profil::class.java)
            startActivity(intent)
        }

        cv_logout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Log out")
            builder.setMessage("Yakin akan Log out ?")
            builder.setPositiveButton("Oke", { dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                Toast.makeText(this, "Anda Berhasil Logout", Toast.LENGTH_SHORT).show()
            })
            builder.setNegativeButton("Batal", { dialogInterface: DialogInterface, i: Int -> })
            builder.show()
        }
    }
}