package com.example.pos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_pembeli.view.*

class RVAdapterPembeli (private val context: Context, private val arrayList: ArrayList<Pembeli>)
    : RecyclerView.Adapter<RVAdapterPembeli.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_pembeli,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.listId.text = "id : "+arrayList?.get(position)?.id
        holder.view.listNama.text = "Nama : "+arrayList?.get(position)?.nama
        holder.view.listUsername.text = "Username : "+arrayList?.get(position)?.username
        holder.view.listGender.text = "Jenis Kelamin : "+arrayList?.get(position)?.jenis_kelamin
    }

    override fun getItemCount(): Int = arrayList!!.size

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}