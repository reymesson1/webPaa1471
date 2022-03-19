package com.example.myapplication.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MasterService {

    @GET("/api/RP/RP_estado")
//    @Headers("Content-type: application/x-www-form-urlencoded", "Accept: application/json")
    fun getMasterService() : Call<ResponseRPEstado>
//    fun getMasterService() : Call<Any>
//    fun getMasterService() : Call<*>
}