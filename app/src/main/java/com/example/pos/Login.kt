package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var etUsername = findViewById<EditText>(R.id.etUsername)
        var etPassword = findViewById<EditText>(R.id.etPassword)
        var tvRegister = findViewById<TextView>(R.id.tvRegister)
        var btnLogin = findViewById<Button>(R.id.btnSignin)

        val URL = "https://KewanKu.000webhostapp.com/kewanku/login.php"
        val jsonObject = JSONObject()
        val requestQueue = Volley.newRequestQueue(this)

        tvRegister.setOnClickListener{
            intent = Intent(this,Register::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if (etUsername.text.toString().length < 6) {
                Toast.makeText(this, "Username minimal 6 karakter", Toast.LENGTH_SHORT).show()
            } else if (etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                jsonObject.put("username", etUsername.text.toString())
                jsonObject.put("password", etPassword.text.toString())

                val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, URL, jsonObject, { response ->
                    if (response.getString("status") == "berhasil") {

                        val stringUsername = etUsername.text.toString()
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                        intent.putExtra("username", stringUsername)

                    } else if (response.getString("status") == "gagal") {
                        Toast.makeText(this, "Gagal Login", Toast.LENGTH_SHORT).show()
                    }
                }, { error ->
                    Toast.makeText(this, "Volley Error${error.toString()}", Toast.LENGTH_SHORT)
                        .show()
                })
                requestQueue.add(jsonObjectRequest)
            }
        }
    }
}