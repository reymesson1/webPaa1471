package com.example.myapplication.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MasterService {

    @GET("/api/RP/RP_estado")
    fun getMasterService() : Call<ResponseRPEstado>

}