package com.mzulsept.myutszul.api

import com.mzulsept.myutszul.ResponseMahasiswa
import com.mzulsept.myutszul.ResponseTekom

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("mahasiswa")
    fun getMhs(): Call<ResponseMahasiswa>
    @GET("mahasiswa")
    fun getTekom(): Call<ResponseTekom>
}
