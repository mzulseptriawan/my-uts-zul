package com.mzulsept.myutszul

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mzulsept.myutszul.api.ApiConfig

class TekomAdapter (
    val dataTekom: List<DataTekom?>?, param: (Any) -> Unit) : RecyclerView.Adapter<TekomAdapter.MyViewHolder>() {
    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val imgTekom = view.findViewById<ImageView>(R.id.fotoTekom)
        val nimTekom = view.findViewById<TextView>(R.id.nimTekom)
        val jenisKelamin = view.findViewById<TextView>(R.id.genderTekom)
        val namaProdi = view.findViewById<TextView>(R.id.namaprodiTekom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tekom,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataTekom != null){
            return dataTekom.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataTekom?.get(position)

        holder.nimTekom.text = currentItem?.nim.toString()
        holder.jenisKelamin.text = currentItem?.gender
        holder.namaProdi.text = currentItem?.namaprodi

        Glide.with(holder.itemView.context)
            .load(ApiConfig.gambar+currentItem?.foto)
            .into(holder.imgTekom)

        holder.itemView.setOnClickListener{
            val nim = dataTekom?.get(position)?.nim
            Toast.makeText(holder.itemView.context, "${nim}", Toast.LENGTH_SHORT).show()
        }

    }
}