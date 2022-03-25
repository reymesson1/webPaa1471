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
import com.example.myapplication.Model.ResponseRPEstadoArray
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

                        restAPI.removeElementFourthDigit(miLista4.getItemIdAtPosition(position).toInt())
                        var intent = Intent(this@MainActivity,MiddleActivity::class.java)
                        startActivity(intent)

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

//                        restAPI.setPostSendComprarUnoAPI(
//                            miLista.getItemIdAtPosition(position).toInt(),
//                            1,
//                            "8098443270",
//                            "test",
//                            "9",
//                            "1"
//                        )
//                        restAPI.setPostSendComprarUnoAPI(
//                            miLista.getItemIdAtPosition(position).toInt(),
//                            1,
//                            telephoneTXTLocal.text.toString(),
//                            nameTXTLocal.text.toString(),
//                            "9",
//                            "1"
//                        )

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

                Log.i("response", response.body()!!.jugados[0].toString())
                Log.i("response", "Jugados array size: "+ response.body()!!.jugados.size.toString())

                loadingJugados(response.body()!!.jugados)


                repolla.setText(response.body()!!.id_repolla.toString())
                ronda.setText(response.body()!!.ronda.toString())
                acumulado.setText(response.body()!!.acumulado.toString())




            }

            override fun onFailure(call: Call<ResponseRPEstado>, t: Throwable) {
                Log.i("error", t.toString())
            }


        })


    }

    fun loadingJugados(jugados : ArrayList<*> ){

//        restAPI.removeElementFourthDigit(9706)
//        restAPI.removeElementFourthDigit(9999)
        restAPI.removeElementFourthDigit(125)
//        restAPI.removeElementFourthDigit(247)
//        restAPI.removeElementFourthDigit(177)
//        restAPI.removeElementFourthDigit(141)
//        restAPI.removeElementFourthDigit(151)
//        restAPI.removeElementFourthDigit(217)
//        restAPI.removeElementFourthDigit(131)
//        restAPI.removeElementFourthDigit(307)
//        restAPI.removeElementFourthDigit(2999)
//        restAPI.removeElementFourthDigit(1151)
//        restAPI.removeElementFourthDigit(6141)
//        restAPI.removeElementFourthDigit(4131)
//        restAPI.removeElementFourthDigit(2125)
//        restAPI.removeElementFourthDigit(9997)
//        restAPI.removeElementFourthDigit(361)
//        restAPI.removeElementFourthDigit(1361)
//        restAPI.removeElementFourthDigit(136)
//        restAPI.removeElementFourthDigit(124)
//        restAPI.removeElementFourthDigit(417)
//        restAPI.removeElementFourthDigit(9)
//        restAPI.removeElementFourthDigit(3)

        for(jugado in jugados){

            var arrStr = jugado.toString().split(",")

            var firstOne =  arrStr[0].substring(1,2).toInt()
            var firstTwo =  arrStr[1].substring(1,5).split("]")
            var secondTwo = firstTwo[0].split(".")



            if(firstOne.toInt()==4){
                Log.i("response", "Jugados SecondFinal: "+ secondTwo[0].toString())
//                restAPI.removeElementFourthDigit(secondTwo[0].toInt())
//                restAPI.removeElementFourthDigit(9706)

            }
//            if(firstTwo[0]=="4.0"){
//                Log.i("response", "Jugados SecondFinal: "+ secondTwo[0].toString())
//                restAPI.removeElementFourthDigit(secondTwo[0].toInt())
//            }


//            if(secondTwo[0].length==1 && secondTwo[0].toInt()>0){
//                Log.i("response", "Jugados Final II: "+ secondTwo[0].toString())
//                restAPI.removeElementOneDigit(secondTwo[0].toInt()-1)
//            }
//            if(secondTwo[0].length==2 && secondTwo[0].toInt()>0){
//                Log.i("response", "Jugados Final II: "+ secondTwo[0].toString())
//                restAPI.removeElementTwoDigit (secondTwo[0].toInt()-1)
//            }
//            if(secondTwo[0].length==3 && secondTwo[0].toInt()>0){
//                Log.i("response", "Jugados Final II: "+ secondTwo[0].toString())
//                restAPI.removeElementTwoDigit (secondTwo[0].toInt()-1)
//            }
//            if(secondTwo[0].length==2){
//                restAPI.removeElementTwoDigit(secondTwo[0].toInt()-1)
//            }
//            if(secondTwo[0].length==3){
//                restAPI.removeElementThreeDigit(secondTwo[0].toInt()-1)
//            }
//            if(secondTwo[0].length==4){
//                restAPI.removeElementFourthDigit( secondTwo[0].toInt()-1)
//            }


//            if(secondTwo[0].toInt()<9990){
//                restAPI.removeElementFourthDigit(secondTwo[0].toInt()-1)
//            }
//            restAPI.removeElementFourthDigit(secondTwo[0].toInt()-2)
//            if(firstOne==4){
//                Log.i("response", "Jugados first: "+ jugado.toString())
////                var firstTwo =  arrStr[1].substring(1,5).toInt()
////                Log.i("response", "Jugados second: "+ firstTwo)
//
////                restAPI.removeElementFourthDigit(firstTwo)
//            }


        }

//        Log.i("response", "Jugados first: "+ jugados[0].toString())

        var arrStr = jugados[0].toString().split(",")

//        Log.i("response", "Jugados first: "+ arrStr[0].substring(1,2))
//        Log.i("response", "Jugados second: "+ arrStr[1].substring(1,5))


//        restAPI.removeElementFourthDigit(1705)

    //
//        for(jugado in jugados){
//        for(jugado in jugados){
//
//            Log.i("response", jugado.toString())
//            Log.i("response", "Jugados array size: "+ jugados.size.toString())
//            Log.i("response", "cifras: "+ jugado.toString())
//            Log.i("response", "valor: "+ jugado.toString())
//            Log.i("response", "Jugados array size: "+ jugados.size.toString())
//
//
//        }




    }

}