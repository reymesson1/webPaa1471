package com.example.myapplication.Model


import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface PostMasterService {

    @FormUrlEncoded
    @POST("/api/RP/login")
    @Headers("Content-type: application/x-www-form-urlencoded", "Accept: application/json")

    fun setMasterService(@Field("email") email : String, @Field("password") password: String ): Call<Token>

}