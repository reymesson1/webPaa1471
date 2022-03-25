package com.example.myapplication.Model

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field

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

    fun setPostSendComprarUnoAPI(
        numero : Int,
        cifras : Int,
        telefono: String,
        cliente: String,
        id_repolla: String,
        ronda: String
    ){

        var setPostSendComprarUno = retrofit.create(PostSendComprarUno::class.java)

        var call = setPostSendComprarUno.setSendComprarUno(

                    numero,
                    cifras,
                    telefono,
                    cliente,
                    id_repolla,
                    ronda
        )

        call.enqueue(object : Callback<ResponseComprarUno>{
            override fun onResponse(
                call: Call<ResponseComprarUno>,
                response: Response<ResponseComprarUno>
            ) {
                Log.i("response", response.body().toString())
            }

            override fun onFailure(call: Call<ResponseComprarUno>, t: Throwable) {
                Log.i("error", t.toString())
            }

        })


    }

    fun removeElementFourthDigit(num : Int){
        
        var removeData3 = RestAPI.datos4.get( num).toString().substring(1).toInt()
        var removeData2 = RestAPI.datos4.get( num).toString().substring(2).toInt()
        var removeData = RestAPI.datos4.get( num).toString().substring(3).toInt()

        if(num>0&&num<=9) {
//            Log.i("response", (miLista4.getItemIdAtPosition(position) + 1).toString())
            RestAPI.datos4.set(num,"") //0009
            RestAPI.datos3.set(num,"") //009
            RestAPI.datos2.set(num,"") //09
            RestAPI.datos.set(num-1,"") //9

        }else if(num>9&&num<=99) {
            Log.i("response",num.toString())
            Log.i("response",num.toString().substring(1))
            RestAPI.datos4.set(num,"")
            RestAPI.datos3.set(num,"")
            RestAPI.datos2.set(num,"")
            RestAPI.datos.set(num.toString().substring(1).toInt()-1,"")


        }else if(num>99&&num<=999) { //aqui esta llegand 125 -> 25 -> 5 solo tre digitos
            Log.i("response",num.toString())
            Log.i("response",num.toString().substring(1))
            Log.i("response",num.toString().substring(2))
            Log.i("response",num.toString().substring(3))


            RestAPI.datos4.set(num-1,"")
            RestAPI.datos3.set(num-1,"")
            RestAPI.datos2.set(num.toString().substring(1).toInt()-1,"")
            RestAPI.datos.set(num.toString().substring(2).toInt()-1,"")
            Log.i("response", "aqui "+ num.toString().substring(2))
        }else if(num>999&&num<=9999) {
            Log.i("response",num.toString())
            Log.i("response",num.toString().substring(1))
            Log.i("response",num.toString().substring(2))
            Log.i("response",num.toString().substring(3))


            RestAPI.datos4.set(num-1,"")
            RestAPI.datos3.set(num.toString().substring(1).toInt()-1,"")
            RestAPI.datos2.set(num.toString().substring(2).toInt()-1,"")
            RestAPI.datos.set(num.toString().substring(3).toInt()-1,"")
        }


    }

    fun removeElementThreeDigit(num : Int){

        var removeData3 = RestAPI.datos3.get( num).toString().substring(1).toInt()
        var removeData2 = RestAPI.datos3.get( num).toString().substring(2).toInt()

        RestAPI.datos.removeAt(removeData2-1)
        RestAPI.datos2.removeAt(removeData3-1)
        RestAPI.datos3.removeAt(num)

        RestAPI.datos4.removeAt((num))
        for(i in 1..9){

            var sum = i * 999
            RestAPI.datos4.removeAt(sum+(num))
            Log.i("response","Three: " + ((sum)+(num)).toString() )


        }

    }

    fun removeElementTwoDigit(num : Int){

        var removeData3 = RestAPI.datos2.get( num).toString().substring(1).toInt()
        RestAPI.datos.removeAt(removeData3-1)
        RestAPI.datos2.removeAt(num)
        RestAPI.datos3.removeAt(num)
        for(i in 1..9){
            var sum = i * 99
            RestAPI.datos3.removeAt(sum+(num))
        }

        RestAPI.datos4.removeAt((num))
        for(i in 1..9){
            var sum = i * 99
            RestAPI.datos4.removeAt(sum+(num))
        }
        for(i in 1..9){
            var sum = i * 999
            RestAPI.datos4.removeAt(sum+(num))
        }

    }

    fun removeElementOneDigit(num : Int){

//        var removeData3 = RestAPI.datos2.get( num).toString().substring(1).toInt()
//        RestAPI.datos.removeAt(num)
//        RestAPI.datos2.removeAt(num)
//        RestAPI.datos3.removeAt(num)
//        RestAPI.datos4.removeAt((num))
        RestAPI.datos.set(num,"")
        RestAPI.datos2.set(num,"")
        RestAPI.datos3.set(num,"")
        RestAPI.datos4.set(num,"")

        for(i in 1..9){
            var sum = i * 9
            RestAPI.datos2.removeAt(sum+(num))
        }

        for(i in 1..9){
            var sum = i * 9
            RestAPI.datos3.removeAt(sum+(num))
        }
        RestAPI.datos3.removeAt(90+(num))
        Log.i("response", "Three " +RestAPI.datos3.get(90+(num)) )
        for(i in 1..9){
            var sum = i * 99
            RestAPI.datos3.removeAt(sum+(num))
            Log.i("response", RestAPI.datos3.get(sum+(num)) )
        }
        RestAPI.datos4.removeAt(99+(num))
        for(i in 1..9){
            var sum = i * 9
            RestAPI.datos4.removeAt(sum+(num))
        }
        for(i in 1..9){
            var sum = i * 99
            RestAPI.datos4.removeAt(sum+(num))
            //0105
        }
        for(i in 1..9){
            var sum = i * 999
            RestAPI.datos4.removeAt(sum+(num))
        }

    }

//    fun removeElementTwoDigit(num : Int){
//
//        var removeData3 = RestAPI.datos2.get( num).toString().substring(1).toInt() //8
//
//        RestAPI.datos2.removeAt(removeData3-1)
//
//        RestAPI.datos3.removeAt(num)
//        for(i in 1..9){
//
//            var sum = i * 100
//            RestAPI.datos3.removeAt(sum+(num-1))
//
//        }
//
//        RestAPI.datos4.removeAt((num))
//        for(i in 1..9){
//
//            var sum = i * 1000
//            RestAPI.datos4.removeAt(sum+(num-1))
//
//        }
//
//
//
//    }

//    fun removeElementOneDigit(num : Int){
//
////        var removeData3 = RestAPI.datos2.get( num).toString().substring(1).toInt() //8
//
////        RestAPI.datos2.removeAt(removeData3-1)
//
//        Log.i("response","one: " + num.toString())
//        RestAPI.datos.removeAt(num)
//
//        RestAPI.datos2.removeAt(num)
//        for(i in 1..9){
//
//            var sum = i * 10
//            RestAPI.datos2.removeAt(sum+(num-1))
//
//        }
//        for(i in 1..9){
//
//            var sum = i * 100
//            RestAPI.datos3.removeAt(sum+(num-1))
//
//        }
//        RestAPI.datos4.removeAt((num))
//        for(i in 1..9){
//
//            var sum = i * 1000
//            RestAPI.datos4.removeAt(sum+(num-1))
//
//        }
//
//
//
//    }



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
//            Log.i("response", i.toString())
        }
    }

    fun generateTwoDigit(){

        for (i in 1..99) {
            if(i<10){
                RestAPI.datos2+="0"+i.toString()
//                Log.i("response", i.toString())
            }else{
                RestAPI.datos2+=i.toString()
//                Log.i("response", i.toString())

            }
        }
    }

    fun generateThreeDigit(){

        for (i in 1..999) {
            if(i<10){
                RestAPI.datos3+="00"+i.toString()
//                Log.i("response", i.toString())
            }else if(i<100){
                RestAPI.datos3+="0"+i.toString()
//                Log.i("response", i.toString())
            }else{
                RestAPI.datos3+=i.toString()
//                Log.i("response", i.toString())
            }
        }
    }

    fun generateFourthDigit(){

        for (i in 1..9999) {
            if(i<10){
                RestAPI.datos4+="000"+i.toString()
//                Log.i("response", i.toString())
            }else if(i<100){
                RestAPI.datos4+="00"+i.toString()
//                Log.i("response", i.toString())
            }else if(i<1000){
                RestAPI.datos4+="0"+i.toString()
//                Log.i("response", i.toString())
            }else{
                RestAPI.datos4+=i.toString()
//                Log.i("response", i.toString())
            }
        }
    }


}