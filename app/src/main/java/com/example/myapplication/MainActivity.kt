package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Model.ResponseRPEstado
import com.example.myapplication.Model.RestAPI
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var restAPI = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {

            activityUiThread {

                getData()

            }
        }
    }

    fun getData(){

        var masterService = restAPI.getMasterAPI()

        var call = masterService.getMasterService()

        call.enqueue(object : Callback<ResponseRPEstado> {

            override fun onResponse(call: Call<ResponseRPEstado>, response: Response<ResponseRPEstado>) {
                Log.i("response", response.body().toString())

                premio.setText(response.body()!!.precio.toString())
                debo.setText(response.body()!!.debo.toString())
                cupo.setText(response.body()!!.cupo.toString())

//                response.body().

            }

            override fun onFailure(call: Call<ResponseRPEstado>, t: Throwable) {
                Log.i("error", t.toString())
            }


        })


    }

}