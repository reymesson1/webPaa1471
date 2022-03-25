package com.example.myapplication.Model

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface PostSendComprarUno {

    @FormUrlEncoded
    @POST("/api/RP/comprar_uno")

    fun setSendComprarUno(
        @Field("numero") numero : Int,
        @Field("cifras") cifras : Int,
        @Field("telefono") telefono: String,
        @Field("cliente") cliente: String,
        @Field("id_repolla") id_repolla: String,
        @Field("ronda") ronda: String,

    ): Call<ResponseComprarUno>

}