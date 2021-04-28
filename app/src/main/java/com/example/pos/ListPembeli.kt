package com.example.pos

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_list_pembeli.*
import org.json.JSONObject

class ListPembeli : AppCompatActivity() {
    var arrayList = ArrayList<Pembeli>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pembeli)

        rvPembeli.setHasFixedSize(true)
        rvPembeli.layoutManager = LinearLayoutManager(this)

    }
    override fun onResume() {
        super.onResume()
        loadAllPembeli()
    }
    
    private fun loadAllPembeli(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        AndroidNetworking.get("https://kewanku.000webhostapp.com/kewanku/read.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    arrayList.clear()
                    val jsonArray = response?.optJSONArray("result")
                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(applicationContext,"Data Kosong",Toast.LENGTH_SHORT).show()
                    }
                    for(i in 0 until jsonArray?.length()!!){
                        val jsonObject :JSONObject = jsonArray?.optJSONObject(i)
                        arrayList.add(Pembeli(jsonObject.getString("id"),
                            jsonObject.getString("nama"),
                            jsonObject.getString("username"),
                            jsonObject.getString("jenis_kelamin")))
                        if(jsonArray?.length() - 1 == i){
                            loading.dismiss()
                            val adapter = RVAdapterPembeli(applicationContext,arrayList)
                            adapter.notifyDataSetChanged()
                            rvPembeli.adapter = adapter
                        }
                    }
                }
                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    anError?.errorDetail?.toString()?.let { Log.d("ONERROR", it) }
                    Toast.makeText(applicationContext,"Connection Failure",Toast.LENGTH_SHORT).show()
                }
            })
    }
}