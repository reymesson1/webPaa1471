package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Model.ResponseRPEstado
import com.example.myapplication.Model.RestAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_middle.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MiddleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_middle)

        btn_next.setOnClickListener {

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }

    }

}