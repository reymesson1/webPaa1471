package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Model.RestAPI
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class Login : AppCompatActivity() {

    var restAPI = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {

            restAPI.setPostLogin("ag@ag.com","123456789")
//            restAPI.setPostLogin(emailTXT.text.toString(),passwordTXT.text.toString())

            doAsync {

                activityUiThread {

                    Thread.sleep(4000)

                    restAPI.setPostLogin("ag@ag.com","123456789")
//                    restAPI.setPostLogin(emailTXT.text.toString(),passwordTXT.text.toString())

                    var intent = Intent(this@Login,MiddleActivity::class.java)
//                    var intent = Intent(this@Login,MainActivity::class.java)
                    startActivity(intent)


                }
            }

        }


    }
}