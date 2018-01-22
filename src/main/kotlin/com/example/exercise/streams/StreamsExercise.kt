package com.example.exercise.streams

import com.example.data.Person
import com.example.type.Blood
import java.time.LocalDate
import java.util.Random
import java.util.stream.Collectors

fun main(args: Array<String>) {
    StreamsExercise().tryAll()
}

class StreamsExercise {

    private val numList: List<Int> by lazy {
        (1..20).asSequence()
                .map { Random().nextInt(99) }
                .toList()
    }
    private val strList: List<String> by lazy {
        val azList = listOf('A'..'Z').flatten()
        (1..20).asSequence()
                .map {
                    val r = Random().nextInt(azList.size)
                    azList[r].toString()
                }
                .toList()
    }
    private val personList: List<Person> by lazy {
        listOf(
            Person("tom", 20, Blood.B, LocalDate.of(1998,1,1)),
            Person("john", 27, Blood.O),
            Person("sam", 24, Blood.A, LocalDate.of(1994,1,1)),
            Person("jake", 25, Blood.AB, LocalDate.of(1993,1,1)),
            Person("dave", 20, Blood.A),
            Person("alice", 24, Blood.B),
            Person("kate", 22, Blood.O, LocalDate.of(1996,1,1))
        )
    }

    fun tryAll() {
        demoIterable()
        demoIterable2()
        demoIterable3()

        demoJvmStream()
        demoJvmStream2()

        demoComparable()
    }

    fun demoIterable() {
        println("demoIterable")

        val newList = numList
                .onEach { println(it) }
                .filter { it % 2 == 0 }
                .sortedBy { it }.reversed()
                .distinct()
                .map { it.toString() }
                .toList()
        newList.joinToString(" , ").also { println(it) }
    }

    fun demoIterable2() {
        println("demoIterable2")

        val newList = personList
                .onEach { println(it) }
                //.sortedBy { it.age }
                .sortedWith(compareBy(Person::age, Person::name))
                //.sortedWith(compareBy({ it.age }, { it.name }))
                .map { it.name }
                .toList()
        newList.joinToString(" , ").also { println(it) }
    }

    fun demoIterable3() {
        println("demoIterable3")

        val newList = personList
                .sortedWith(Person.comparator)
                .toList()
        newList.joinToString(" , ").also { println(it) }
    }

    fun demoJvmStream() {
        println("demoJvmStream")

        val newList: List<String> = numList.stream()
                .peek { println(it) }
                .filter { it % 2 == 0 }
                .sorted(Comparator.reverseOrder<Int>())
                .distinct()
                .map { it.toString() }
                .collect(Collectors.toList())
        newList.joinToString(" , ").also { println(it) }
    }

    fun demoJvmStream2() {
        println("demoJvmStream2")

        val newList = personList.stream()
                .peek { println(it) }
                //.sorted(Comparator.comparing(Person::age))
                .sorted(Comparator.comparing(Person::age)
                                  .thenComparing(Person::name))
                .map { it.name }
                .collect(Collectors.toList())
        newList.joinToString(" , ").also { println(it) }
    }

    /*
    fun demoJavaStream3() {
        val byJvmBlood = java.util.Comparator.comparing(Person::blood)
        val byJvmBirthDay = java.util.Comparator.comparing(Person::birthDay)

        // error
        //val byJvmBirthDay = java.util.Comparator.comparing(Person::birthDay,
        //        java.util.Comparator.naturalOrder<Person>())

        val javaComparator = byJvmBlood.thenComparing(byJvmBirthDay)

        val newList = personList.stream()
                .sorted(javaComparator)
                .collect(Collectors.toList())
        newList.joinToString(" , ").also { println(it) }
    }
    */

    fun demoComparable() {
        println("demoComparable")

        val newList = personList.sorted()
        newList.joinToString(" , ").also { println(it) }
    }
}