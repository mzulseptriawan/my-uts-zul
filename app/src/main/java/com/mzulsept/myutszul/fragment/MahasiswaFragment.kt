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
import com.mzulsept.myutszul.DataItem
import com.mzulsept.myutszul.MahasiswaAdapter
import com.mzulsept.myutszul.R
import com.mzulsept.myutszul.ResponseMahasiswa
import com.mzulsept.myutszul.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MahasiswaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MahasiswaFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename and change types of parameters
    private lateinit var adapter: MahasiswaAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var mhsArrayList: ArrayList<DataItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mahasiswa, container, false)
        onRefresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvMhs)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

//        mendapatkan instance dari Api Service dari ApiConfig
        val apiService = ApiConfig.getService()
//        melakukan pengadilan Api untuk mendapatkan data ResponPost
        val call = apiService.getMhs()

        call.enqueue(object : Callback<ResponseMahasiswa> {
            override fun onResponse(call: Call<ResponseMahasiswa>, response: Response<ResponseMahasiswa>) {
                if (response.isSuccessful){
                    val responseMahasiswa = response.body()

                    mhsArrayList = responseMahasiswa?.data as ArrayList<DataItem>
                    recyclerView.adapter = MahasiswaAdapter(mhsArrayList) { selectedItem ->

                    }
                } else {
                    // handle kesalahan respons
                    Log.e("MahasiswaFragment", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseMahasiswa>, t: Throwable) {
                Log.e("MahasiswaFragment", "Failed: ${t.message}")

            }

        })
    }

    override fun onRefresh() {

    }
}