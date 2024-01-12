package com.ozanyazici.kotlinadvancedfunctions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myNumList = listOf<Int>(1,3,5,7,9,11,13,15)

        //Filter

        //.filter Yalnızca verilen durumla eşleşen öğeleri içeren bir liste döndürür
        val smallNumberList = myNumList.filter { num -> num < 6 }
        for (num in smallNumberList) {
            println(num)
        }

        println("---------------------")

        /* Böyle de kullanılıyor.
        val smallNumberList = myNumList.filter { it < 6 }
        for (num in smallNumberList) {
            println(num)
        }
         */

        //Map

        //Özgün koleksiyondaki her öğeye verilen dönüştürme işlevinin uygulanmasının sonuçlarını içeren bir liste döndürür.

        /*
        val squaredNumbers = myNumList.map { num -> num * num }
        for (num in squaredNumbers) {
            println(num)
        }
         */

        val squaredNumbers = myNumList.map { it * it }
        for (num in squaredNumbers) {
            println(num)
        }

        println("---------------------")

        //Filter & Map bir arada

        val filterAndMapCombined = myNumList.filter { it < 6 }.map { it * it }
        for (num in filterAndMapCombined) {
            println(num)
        }

        println("---------------------")

        //Filter & Map nesneler içinde kullanılabilir.

        val musician = listOf<Musician>(
            Musician("james",60,"guitar"),
            Musician("lars",55,"drum"),
            Musician("kirk",50,"guitar")
        )

        val youngGuitarist = musician.filter { it.age < 60 }.map { it.instrument }
        for (element in youngGuitarist) {
            println(element)
        }

        println("---------------------")

        //.All listedeki elemanların hepsi verilen duruma uyuyorsa true döndürür.
        val allCheck = myNumList.all { it > 5 }
        println(allCheck)

        println("---------------------")

        //.Any listede verilen durumu sağlayan herhangi bir eleman varsa true döndürür.
        val anyCheck = myNumList.any { it > 5 }
        println(anyCheck)

        println("---------------------")

        //.Count verilen durumu sağlayan eleman sayısını döndürür.
        val totalCount = myNumList.count { it > 5 }
        println(totalCount)

        println("---------------------")

        //.Find verilen durumu sağlayan ilk elemanı döndürür.
        val findNum = myNumList.find { it > 5 }
        println(findNum)

        println("---------------------")

        val findLastNum = myNumList.findLast { it > 5 }
        println(findLastNum)

        println("---------------------")

        //Predicate

        //Aynı durum birden çok kez kullanılacaksa bir predicate(yüklem) oluşturabilir, yazımda kolaylık sağlar.
        val myPredicate = { num : Int -> num > 5 }
        val allCheckWithPredicate = myNumList.all(myPredicate)
        println(allCheckWithPredicate)

        println("---------------------")

        //.Let

        var myInt: Int? = null

        if (myInt != null) {
            val num = myInt!! + 1
        }

        //Yukarıdakini yapmak yerine bu daha kolay
        myInt?.let {
            val num = it + 1
        }

        //.let in başka bir kullanım şekli. Burada myInt null gelmezse eğer 1 arttırılıp myNum a atanıyor.
        //Eğer null gelirse elvis operatörü ile myNum a 0 değerini atıyoruz.
        val myNum = myInt?.let {
            it + 1
        } ?: 0

        //Also

        //bir işlemden den sonra ayrıca şunları yap dememize yarıyor
        val ozan = Person("ozan",23)
        val atil = Person("atil",1)
        val lars = Person("lars",21)

        val people = listOf<Person>(ozan,atil,lars)

        people.filter { it.age > 18 }.also {
            for (person in it) {
                println(person.name)
            }
        }

        println("---------------------")

        //Apply

        //mesela intenti apply ile birlikte kullanabiliriz yazım kolaylığı sağlıyor ve intent i ayrıca start etmeme gerek kalmıyor.
        val intentWithApply = Intent().apply {
            putExtra("","")
            putExtra("","")
            `package` = ""
            action = ""
        }

        //with

        //parametreyi değiştirmek için böyle bir yöntem kullanabilirim.
        Person("barley",4).apply {
            name = "Barley"
        }.also { println(it.name) }

        val withBarley = Person("arley",4)

        //yada with ile böyle değiştirebiirim.
        with(withBarley) {
            name = "Barley"
            age = 4
        }

        println("last barley: " + withBarley.name)

        println("---------------------")
    }
}
data class Musician(val name: String, val age: Int, val instrument: String)
data class Person(var name: String, var age: Int)