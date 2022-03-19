package com.example.myapplication.Model

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestAPI {

    companion object {

        var token = ""
        var userId = ""
    }

    private val client = OkHttpClient.Builder().apply{
        addInterceptor(HttpInterceptor())
    }.build()


    var retrofit = Retrofit.Builder()
        .baseUrl("http://derrama.net")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getMasterAPI() : MasterService{

        var masterService = retrofit.create(MasterService::class.java)

        return masterService
    }

    fun setPostLogin(email:String, password:String){

        var newUser = User()
        newUser.email = email
        newUser.password = password

        var json = Gson().toJson(newUser)

        var postService = retrofit.create(PostMasterService::class.java)

//        Calling with foo.example("Bob Smith", "Jane Doe") yields a request body of name=Bob+Smith&name=Jane+Doe.

        var call = postService.setMasterService(email,password)

        call.enqueue(object : Callback<Token>{
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                Log.i("response", response.body()!!.tienda.toString())

                RestAPI.token = response.body()!!.access_token.toString()

            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.i("error", t.message.toString())
            }

        })




    }

}