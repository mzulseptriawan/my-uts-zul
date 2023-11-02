package com.mzulsept.myutszul.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mzulsept.myutszul.DataTekom
import com.mzulsept.myutszul.TekomAdapter
import com.mzulsept.myutszul.R
import com.mzulsept.myutszul.ResponseTekom
import com.mzulsept.myutszul.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TekomFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var adapter: TekomAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var tekomArrayList: ArrayList<DataTekom>
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tekom, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvTekom)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        // mendapatkan instance dari Api Service dari ApiConfig
        val apiService = ApiConfig.getService()
        // melakukan pengadilan Api untuk mendapatkan data ResponPost
        val call = apiService.getTekom()

        call.enqueue(object : Callback<ResponseTekom> {
            override fun onResponse(call: Call<ResponseTekom>, response: Response<ResponseTekom>) {
                if (response.isSuccessful){
                    val responseTekom = response.body()
                    tekomArrayList = responseTekom?.data as ArrayList<DataTekom>
                    recyclerView.adapter = TekomAdapter(tekomArrayList) { selectedItem ->
                        // Implementasi aksi item click di sini jika diperlukan
                    }
                } else {
                    // handle kesalahan respons
                    Log.e("TekomFragment", "Error: ${response.message()}")
                }
                // Selesaikan animasi refresh setelah mendapatkan respons
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<ResponseTekom>, t: Throwable) {
                Log.e("TekomFragment", "Failed: ${t.message}")
                // Selesaikan animasi refresh jika panggilan gagal
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    override fun onRefresh() {
        // Implementasikan aksi refresh di sini, misalnya memuat ulang data dari server
        // Panggil metode untuk memuat data kembali
        loadData()
    }

    private fun loadData() {
        // Mendapatkan instance dari Api Service dari ApiConfig
        val apiService = ApiConfig.getService()
        // Melakukan pengadilan Api untuk mendapatkan data ResponPost
        val call = apiService.getTekom()

        call.enqueue(object : Callback<ResponseTekom> {
            override fun onResponse(call: Call<ResponseTekom>, response: Response<ResponseTekom>) {
                if (response.isSuccessful){
                    val responseTekom = response.body()
                    tekomArrayList.clear()
                    tekomArrayList.addAll(responseTekom?.data as ArrayList<DataTekom>)
//                    adapter.notifyDataSetChanged()
                } else {
                    // handle kesalahan respons
                    Log.e("TekomFragment", "Error: ${response.message()}")
                }
                // Selesaikan animasi refresh setelah mendapatkan respons
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<ResponseTekom>, t: Throwable) {
                Log.e("TekomFragment", "Failed: ${t.message}")
                // Selesaikan animasi refresh jika panggilan gagal
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
