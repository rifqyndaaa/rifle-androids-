package com.example.myapplication.pertemuan_2

import java.io.StringWriter

fun main () {
    println ("hai rekan-rekan..")
    println("selamat datang di bahasa pemograman kotlin")


    println("=================")

    var angka = 15
    println("hasil darti 15 + 10 = $ {angka + 10 }")

     val nilaiInt = 10000
     val nilaiDoble = 100.003
    val nilaiFloat = 1000.0f


    println ("nilai Integer = $nilaiInt")
    println("nilai Double $nilaiDoble")
    println("nilai Float = $nilaiFloat")

    println("=========== KONDISI ========")


    val nilai = 10
    if (nilai<0)
            println("bilangan negatif")

    else {
        if(nilai%2 == 0)
            println ("bilangan ganjil")
    }



    println("==== pengulangan =========")
    val kampusKu : Array<String> = arrayOf("kampus","politexnik", " caltex" , "riau")
    for (kampus: String in kampusKu) {

    }

}
