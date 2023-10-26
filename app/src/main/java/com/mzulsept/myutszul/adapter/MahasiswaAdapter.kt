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

class MahasiswaAdapter (
    val dataMahasiswa: List<DataItem?>?, param: (Any) -> Unit) : RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder>() {
    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val imgMahasiswa = view.findViewById<ImageView>(R.id.foto)
        val namaMahasiswa = view.findViewById<TextView>(R.id.namalengkap)
        val jenisKelamin = view.findViewById<TextView>(R.id.gender)
        val namaProdi = view.findViewById<TextView>(R.id.namaprodi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataMahasiswa != null){
            return dataMahasiswa.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataMahasiswa?.get(position)

        holder.namaMahasiswa.text = currentItem?.namalengkap
        holder.jenisKelamin.text = currentItem?.gender
        holder.namaProdi.text = currentItem?.namaprodi

        Glide.with(holder.itemView.context)
            .load(ApiConfig.gambar+currentItem?.foto)
            .into(holder.imgMahasiswa)

        holder.itemView.setOnClickListener{
            val namalengkap = dataMahasiswa?.get(position)?.namalengkap
            Toast.makeText(holder.itemView.context, "${namalengkap}", Toast.LENGTH_SHORT).show()
        }

    }
}