package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.Model.ResponseRPEstado
import com.example.myapplication.Model.RestAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.view.*
import kotlinx.android.synthetic.main.layout_item_modal.*
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

        if(RestAPI.firstTimeObj) {

            restAPI.generateDigit()
            restAPI.generateTwoDigit()
            restAPI.generateThreeDigit()
            restAPI.generateFourthDigit()

            RestAPI.firstTimeObj = false

        }

        Log.i("response", "RestAPI.datos " + RestAPI.datos.size.toString())
        Log.i("response", "RestAPI.datos4 " + RestAPI.datos4.size.toString())



        doAsync {

            activityUiThread {

                getData()

                Thread.sleep(2000)
                getAdapterDigit()
                getAdapterTwoDigit()
                getAdapterThreeDigit()
                getAdapterFourthDigit()


            }
        }
    }

    fun getAdapterFourthDigit() {

        val adaptador4 = ArrayAdapter(this@MainActivity,
            R.layout.elemento_de_lista,
            RestAPI.datos4)
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
                        Log.i("response", "saved ${miLista4.getItemIdAtPosition(position)} "  )
                        restAPI.setPostSendComprarUnoAPI(
                            miLista4.getItemIdAtPosition(position).toInt(),
                            4,
                            telephoneTXT.text.toString(),
                            nameTXT.text.toString(),
                            "9",
                            "1"
                        )
                        restAPI.removeElementFourthDigit(miLista4.getItemIdAtPosition(position).toInt())
                        var intent = Intent(this@MainActivity,MiddleActivity::class.java)
                        startActivity(intent)
//                                Log.i("response", "saved ${miLista} "  )
                    })

                    alertDialog.show()
                }
            }

    }
    fun getAdapterThreeDigit() {

        val adaptador3 = ArrayAdapter(this@MainActivity,
            R.layout.elemento_de_lista,
            RestAPI.datos3)
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

                        restAPI.removeElementThreeDigit(miLista3.getItemIdAtPosition(position).toInt())
                        restAPI.setPostSendComprarUnoAPI(
                            miLista3.getItemIdAtPosition(position).toInt(),
                            3,
                            telephoneTXT.text.toString(),
                            nameTXT.text.toString(),
                            "9",
                            "1"
                        )
                        var intent = Intent(this@MainActivity,MiddleActivity::class.java)
                        startActivity(intent)

                        Log.i("response", "saved ${miLista3.getItemAtPosition(position)} "  )
//                                Log.i("response", "saved ${miLista} "  )
                    })

                    alertDialog.show()
                }
            }

    }
    fun getAdapterTwoDigit() {

        val adaptador2 = ArrayAdapter(this@MainActivity,
            R.layout.elemento_de_lista,
            RestAPI.datos2)
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

                        restAPI.setPostSendComprarUnoAPI(
                            miLista2.getItemIdAtPosition(position).toInt(),
                            2,
                            telephoneTXT.text.toString(),
                            nameTXT.text.toString(),
                            "9",
                            "1"
                        )
                        restAPI.removeElementTwoDigit(miLista2.getItemIdAtPosition(position).toInt())
                        var intent = Intent(this@MainActivity,MiddleActivity::class.java)
                        startActivity(intent)
//                        Log.i("response", "saved ${miLista2.getItemAtPosition(position)} "  )
//                                Log.i("response", "saved ${miLista} "  )
                    })

                    alertDialog.show()
                }
            }
    }


    fun getAdapterDigit(){

        val nameTXTLocal = findViewById<EditText>(R.id.nameTXT)
        val telephoneTXTLocal = findViewById<EditText>(R.id.telephoneTXT)

        val adaptador = ArrayAdapter(this@MainActivity,
            R.layout.elemento_de_lista,
            RestAPI.datos)
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

                        restAPI.setPostSendComprarUnoAPI(
                            miLista.getItemIdAtPosition(position).toInt(),
                            1,
                            "8098443270",
                            "test",
                            "9",
                            "1"
                        )
                        restAPI.setPostSendComprarUnoAPI(
                            miLista.getItemIdAtPosition(position).toInt(),
                            1,
                            telephoneTXTLocal.text.toString(),
                            nameTXTLocal.text.toString(),
                            "9",
                            "1"
                        )

                        restAPI.removeElementOneDigit(miLista.getItemIdAtPosition(position).toInt())
                        var intent = Intent(this@MainActivity,MiddleActivity::class.java)
                        startActivity(intent)
                        Log.i("response", "saved ${miLista.getItemAtPosition(position)} "  )
                    })

                    alertDialog.show()

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