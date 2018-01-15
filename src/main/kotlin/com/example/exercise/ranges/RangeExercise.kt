package com.example.exercise.ranges

import com.example.exercise.logging.logger

fun main(args: Array<String>) {
    RangeExercise().tryAll()
}

/**
 * 範囲
 *
 * [ranges] (https://kotlinlang.org/docs/reference/ranges.html)
 * [kotlin.ranges] (https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/index.html)
 */
class RangeExercise {
    private val log = logger(RangeExercise::class.java)

    fun tryAll() {
        inForLoop()
        inLambdas()
    }

    fun inForLoop() {
        val start = 1
        val end = 10

        for (i in start..end) {
            println(i)
        }

        for (i in start until end) {
            println(i)
        }

        for (i in end downTo start) {
            println(i)
        }

        for (i in  start..end step 3) {
            println(i)
        }
    }

    fun inLambdas() {
        val start = 1
        val end = 10

        (start..end).forEach {
            println(it)
        }

        (start..end).reversed().forEach {
            println(it)
        }

        (start..end step 3).forEach {
            println(it)
        }

        (start..end).step(3).forEach {
            println(it)
        }

        (start..end).filter { it % 2 == 0 }.forEach {
            even -> println(even)
        }
    }

}