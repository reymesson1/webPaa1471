package com.example.myapplication.Model

class ResponseRPEstado {

    var activa : String = ""
    var winner : String = ""
    var cupo : Int = 0
    var usuario : String = ""
    var precio : Int = 0
    var id_repolla : Int = 0
    var ronda : Int = 0
    var debo : Int = 0
    var base : Int = 0
    var jugados : Array<*> = arrayOf("",null)
//    var jugados: Array<Array<out Int?>> = arrayOf(arrayOf(1), arrayOf(2), arrayOf(3, null, 4))
    var last_id : Int = 0
    var acumulado : Int = 0
}