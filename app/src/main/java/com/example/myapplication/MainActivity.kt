package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.Model.ResponseRPEstado
import com.example.myapplication.Model.RestAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var restAPI = RestAPI()

    val datos = arrayOf("1", "2", "3", "4",
        "5", "6", "7", "8",
        "9", "9", "10", "12",
        "13", "14", "15")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        doAsync {

            activityUiThread {

                getData()

                datos.forEach { at->

                    Log.i("response", "primero")
                }

                Thread.sleep(2000)

                datos.forEach { at->

                    Log.i("response", at)
                }

                val adaptador = ArrayAdapter(this@MainActivity,
                    R.layout.elemento_de_lista,
                    datos)
                miLista.adapter = adaptador

                miLista.onItemClickListener =
                    object : AdapterView.OnItemClickListener{
                        override fun onItemClick(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            Toast.makeText(
                                this@MainActivity,
                                "Escogido: ${miLista.getItemAtPosition(position)}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

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

                response.body()!!.jugados.forEachIndexed { index, at ->

//                    datos.set(index)
//                    datos.fill(index.toString())
                    datos.toMutableList().add(index.toString())

                    datos.forEach {

                        Log.i("responseNum",it.toString())
                    }

                }


//                response.body()!!.jugados.forEachIndexed  {  index,at->
//
//                    var item = layoutInflater.inflate(R.layout.layout_item, null)
//
//                    item.chip.setText(index.toString())
//
//                    Log.i("response", index.toString())
//                    scContent.addView(item)
//
//                }

                repolla.setText(response.body()!!.id_repolla.toString())
                ronda.setText(response.body()!!.ronda.toString())
                acumulado.setText(response.body()!!.acumulado.toString())




            }

            override fun onFailure(call: Call<ResponseRPEstado>, t: Throwable) {
                Log.i("error", t.toString())
            }


        })


    }

}