package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.Model.ResponseRPEstado
import com.example.myapplication.Model.RestAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    var restAPI = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var datos = emptyArray<String>();
        var datos2 = emptyArray<String>();
        var datos3 = emptyArray<String>();
        var datos4 = emptyArray<String>();
        var datosx = emptyArray<String>();

        for (i in 1..9) {
            datos+=i.toString()
            Log.i("response", i.toString())
        }
        for (i in 1..99) {
            if(i<10){
                datos2+="0"+i.toString()
                Log.i("response", i.toString())
            }else{
                datos2+=i.toString()
                Log.i("response", i.toString())

            }
        }
        for (i in 1..999) {
            if(i<10){
                datos3+="00"+i.toString()
                Log.i("response", i.toString())
            }else if(i<100){
                datos3+="0"+i.toString()
                Log.i("response", i.toString())
            }else{
                datos3+=i.toString()
                Log.i("response", i.toString())
            }
        }
        for (i in 1..9999) {
            if(i<10){
                datos4+="000"+i.toString()
                Log.i("response", i.toString())
            }else if(i<100){
                datos4+="00"+i.toString()
                Log.i("response", i.toString())
            }else if(i<1000){
                datos4+="0"+i.toString()
                Log.i("response", i.toString())
            }else{
                datos4+=i.toString()
                Log.i("response", i.toString())
            }
        }


        doAsync {

            activityUiThread {

                getData()

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
//                            Log.i("response", "onClicked!")
//                            Toast.makeText(this@MainActivity, "Escogido: ${miLista.getItemAtPosition(position)}", Toast.LENGTH_LONG).show()

                            var modal = layoutInflater.inflate(R.layout.layout_item_modal, null)

                            var alertDialog = AlertDialog.Builder(this@MainActivity)

                            alertDialog.setView(modal)

                            alertDialog.setTitle("Numero a vender")

                            alertDialog.setPositiveButton("Confirmar venta", DialogInterface.OnClickListener { dialogInterface, i ->

                                Log.i("response", "saved ${miLista.getItemAtPosition(position)} "  )
                            })

                            alertDialog.show()

                        }
                    }

                val adaptador2 = ArrayAdapter(this@MainActivity,
                    R.layout.elemento_de_lista,
                    datos2)
                miLista2.adapter = adaptador2

                miLista2.onItemClickListener =
                    object : AdapterView.OnItemClickListener{
                        override fun onItemClick(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            var modal = layoutInflater.inflate(R.layout.layout_item_modal, null)

                            var alertDialog = AlertDialog.Builder(this@MainActivity)

                            alertDialog.setView(modal)

                            alertDialog.setTitle("Numero a vender")

                            alertDialog.setPositiveButton("Confirmar venta", DialogInterface.OnClickListener { dialogInterface, i ->

                                Log.i("response", "saved ${miLista2.getItemAtPosition(position)} "  )
//                                Log.i("response", "saved ${miLista} "  )
                            })

                            alertDialog.show()
                        }
                    }

                val adaptador3 = ArrayAdapter(this@MainActivity,
                    R.layout.elemento_de_lista,
                    datos3)
                miLista3.adapter = adaptador3

                miLista3.onItemClickListener =
                    object : AdapterView.OnItemClickListener{
                        override fun onItemClick(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            var modal = layoutInflater.inflate(R.layout.layout_item_modal, null)

                            var alertDialog = AlertDialog.Builder(this@MainActivity)

                            alertDialog.setView(modal)

                            alertDialog.setTitle("Numero a vender")

                            alertDialog.setPositiveButton("Confirmar venta", DialogInterface.OnClickListener { dialogInterface, i ->

                                Log.i("response", "saved ${miLista3.getItemAtPosition(position)} "  )
//                                Log.i("response", "saved ${miLista} "  )
                            })

                            alertDialog.show()
                        }
                    }

                val adaptador4 = ArrayAdapter(this@MainActivity,
                    R.layout.elemento_de_lista,
                    datos4)
                miLista4.adapter = adaptador4

                miLista4.onItemClickListener =
                    object : AdapterView.OnItemClickListener{
                        override fun onItemClick(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            var modal = layoutInflater.inflate(R.layout.layout_item_modal, null)

                            var alertDialog = AlertDialog.Builder(this@MainActivity)

                            alertDialog.setView(modal)

                            alertDialog.setTitle("Numero a vender")

                            alertDialog.setPositiveButton("Confirmar venta", DialogInterface.OnClickListener { dialogInterface, i ->

                                Log.i("response", "saved ${miLista4.getItemAtPosition(position)} "  )
//                                Log.i("response", "saved ${miLista} "  )
                            })

                            alertDialog.show()
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