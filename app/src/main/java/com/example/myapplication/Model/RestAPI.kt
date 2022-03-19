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
        var firstTimeObj = true
        var datos = mutableListOf<String>()
        var datos2 = mutableListOf<String>()
        var datos3 = mutableListOf<String>()
        var datos4 = mutableListOf<String>()
    }

    var datos = emptyArray<String>();
    var datos2 = emptyArray<String>();
    var datos3 = emptyArray<String>();
    var datos4 = emptyArray<String>();
    var datosx = emptyArray<String>();

    var firstTime = false

    private val client = OkHttpClient.Builder().apply{
        addInterceptor(HttpInterceptor())
    }.build()


    var retrofit = Retrofit.Builder()
        .baseUrl("http://derrama.net")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun removeElementFourthDigit(num : Int){
        
        var removeData3 = RestAPI.datos4.get( num).toString().substring(1).toInt()
        var removeData2 = RestAPI.datos4.get( num).toString().substring(2).toInt()
        var removeData = RestAPI.datos4.get( num).toString().substring(3).toInt()

        RestAPI.datos4.removeAt(num)
        RestAPI.datos3.removeAt(removeData3-1)
        RestAPI.datos2.removeAt(removeData2-1)
        RestAPI.datos.removeAt(removeData-1)

    }

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

    fun generateDigit(){

        for (i in 1..9) {
//            datos+=i.toString()
            RestAPI.datos+=i.toString()
            Log.i("response", i.toString())
        }
    }

    fun generateTwoDigit(){

        for (i in 1..99) {
            if(i<10){
                RestAPI.datos2+="0"+i.toString()
                Log.i("response", i.toString())
            }else{
                RestAPI.datos2+=i.toString()
                Log.i("response", i.toString())

            }
        }
    }

    fun generateThreeDigit(){

        for (i in 1..999) {
            if(i<10){
                RestAPI.datos3+="00"+i.toString()
                Log.i("response", i.toString())
            }else if(i<100){
                RestAPI.datos3+="0"+i.toString()
                Log.i("response", i.toString())
            }else{
                RestAPI.datos3+=i.toString()
                Log.i("response", i.toString())
            }
        }
    }

    fun generateFourthDigit(){

        for (i in 1..9999) {
            if(i<10){
                RestAPI.datos4+="000"+i.toString()
                Log.i("response", i.toString())
            }else if(i<100){
                RestAPI.datos4+="00"+i.toString()
                Log.i("response", i.toString())
            }else if(i<1000){
                RestAPI.datos4+="0"+i.toString()
                Log.i("response", i.toString())
            }else{
                RestAPI.datos4+=i.toString()
                Log.i("response", i.toString())
            }
        }
    }


}