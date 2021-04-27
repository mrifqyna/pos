package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var btnRegister = findViewById<Button>(R.id.btnRegister)
        var etNama = findViewById<EditText>(R.id.etNama)
        var etUsername = findViewById<EditText>(R.id.etUsername)
        var etPassword = findViewById<EditText>(R.id.etPass1)
        var etPassword2 = findViewById<EditText>(R.id.etPass2)
        var tvLogin = findViewById<TextView>(R.id.tvLogin)

        //akses IP & path file insert.php sesuai komputer masing-masing (localhost)
        val URL = "https://KewanKu.000webhostapp.com/kewanku/insert.php"
        val jsonObject = JSONObject()
        val requestQueue = Volley.newRequestQueue(this)

        tvLogin.setOnClickListener{
            intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener {
            if (etNama.text.toString().isEmpty()) {
                Toast.makeText(this, "Nama harus diisi", Toast.LENGTH_SHORT).show()
            } else if (etUsername.text.toString().length < 6) {
                Toast.makeText(this, "Username minimal 6 karakter", Toast.LENGTH_SHORT).show()
            }  else if (etPassword.text.toString().length < 8) {
                Toast.makeText(this,"Password minimal 8 karakter",Toast.LENGTH_SHORT).show()
            } else if (etPassword2.text.toString() != etPassword.text.toString()) {
                Toast.makeText(this,"Password tidak sama",Toast.LENGTH_SHORT).show()
            } else {
                //action kirim nilai yang diisikan pada kolom2 tsb ke URL file PHP via JSON menggunakan method POST
                jsonObject.put("nama", etNama.text.toString())
                jsonObject.put("username", etUsername.text.toString())
                jsonObject.put("password", etPassword.text.toString())
                val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,
                    URL,
                    jsonObject,
                    Response.Listener { response ->
                        if (response.getString("status") == "berhasil") {
                            Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                            etNama.setText("")
                            etUsername.setText("")
                            etPassword.setText("")
                            etPassword2.setText("")
                            val intent = Intent(this,Login::class.java)
                            startActivity(intent)
                        } else if (response.getString("status") == "gagal") {
                            Toast.makeText(this, "Gagal Disimpan", Toast.LENGTH_SHORT).show()
                        }
                    },
                    Response.ErrorListener { error ->
                    })
                requestQueue.add(jsonObjectRequest)
            }
        }
    }
}