package com.example.exercise.arrays

import java.util.*

fun main(args: Array<String>) {
    ArrayExercise().tryAll()
}

/**
 * 配列
 *
 */
class ArrayExercise {

    fun tryAll() {
        factoryMethod()
        constructorWithLambda()
        indeies()
        convert()
    }

    fun factoryMethod() {
        val array = arrayOf("A", "B", "C", "D", "E")
        println(array.joinToString())

        // intの配列を生成する
        val intArray = intArrayOf(1, 2, 3, 4, 5)
        println(intArray.joinToString(": "))

        // 空の配列を生成する
        val emptyArray = emptyArray<String>()
        println(emptyArray.size)
    }

    fun constructorWithLambda() {
        val array = Array(size = 5, init = { i ->
            if (i % 2 == 0) {
                "*"
            } else {
                Random().nextInt(i * 10).toString()
            }
        })
        println(array.joinToString(",", "<", ">"))
    }

    fun indeies() {
        val array = arrayOf("A", "B", "C", "B", "D", "A")

        for (i in array.indices) {
            println(array[i])
        }

        for ((index, value) in array.withIndex()) {
            println("index:$index, value:$value")
        }

        for ((index, value) in array.distinct().withIndex()) {
            println("index:$index, value:$value")
        }
    }

    private fun convert() {
        val list = listOf("A", "B", "C", "D")
        val set = list.toSet()

        println(list.joinToString())
        println(set.joinToString())

        val mutableList: MutableCollection<String> = mutableListOf()
        val mutableSet: MutableCollection<String> = mutableSetOf()

        list.toCollection(mutableList)
        list.toCollection(mutableSet)

        mutableList.add("E")
        mutableSet.add("F")

        println(mutableList.joinToString())
        println(mutableSet.joinToString())
    }

}