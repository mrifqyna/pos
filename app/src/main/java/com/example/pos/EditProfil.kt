package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.activity_edit_profil.etUsername
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class EditProfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        val URL = "https://KewanKu.000webhostapp.com/kewanku/update.php"
        val jsonObject = JSONObject()
        val requestQueue = Volley.newRequestQueue(this)

        val id = intent.getStringExtra("id")
        val nama = intent.getStringExtra("nama")
        val username = intent.getStringExtra("username")
        val jenis_kelamin = intent.getStringExtra("jenis_kelamin")

        etNama.setText(nama)
        etUsername.setText(username)
        etGender.setText(jenis_kelamin)

        btnSimpan.setOnClickListener {
            if (etNama.text.toString().isEmpty()) {
                Toast.makeText(this, "Nama harus diisi", Toast.LENGTH_SHORT).show()
            } else if (etUsername.text.toString().length < 6) {
                Toast.makeText(this, "Username minimal 6 karakter", Toast.LENGTH_SHORT).show()
            } else if (etGender.text.toString().contains("Pria")) {
                Toast.makeText(this, "Isikan Pria atau Wanita", Toast.LENGTH_SHORT).show()
            } else if (etGender.text.toString().contains("Wanita")) {
                Toast.makeText(this, "Isikan Pria atau Wanita", Toast.LENGTH_SHORT).show()
            } else {
                jsonObject.put("nama", etNama.text.toString())
                jsonObject.put("username", etUsername.text.toString())
                jsonObject.put("jenis_kelamin", etGender.text.toString())
                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST,
                    URL,
                    jsonObject,
                    Response.Listener { response ->
                        if (response.getString("status") == "berhasil") {
                            Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                            etNama.setText("")
                            etUsername.setText("")
                            etGender.setText("")
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