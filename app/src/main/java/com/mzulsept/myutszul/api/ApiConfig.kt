package com.mzulsept.myutszul.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    const val baseUrl = "http://172.16.120.9/api-uts-zul/public/api/"
    const val gambar = "http://172.16.120.9/api-uts-zul/public/storage/mahasiswa/"

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() : ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}
