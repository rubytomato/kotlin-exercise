package com.example.exercise.file

import java.io.File
import java.net.URL

fun main(args: Array<String>) {
    FileExercise().tryAll()
}

/**
 * ファイル
 *
 */
class FileExercise {

    companion object {
        fun loader(name: String): File {
            val url: URL = ClassLoader.getSystemResource(name)
            println(url)
            return File(url.file)
        }
    }

    fun tryAll() {
        fileReadLines()
        fileReader()
        fileNotFound()
    }

    fun fileReadLines() {
        val file = File("test.txt")
        println(file.absoluteFile)

        // File.readLines(charset: Charset = Charsets.UTF_8): List<String>
        file.readLines()
            .forEach { println(it) }

        file.readLines(charset = Charsets.UTF_8)
            .joinToString(":") { it.trim() }
            .also { text -> println("$text") }

        // <T> File.useLines(charset: Charset = Charsets.UTF_8, block: (Sequence<String>) -> T): T
        file.useLines {
            it.map { it.toLowerCase() }.forEach { println(it) }
        }

        file.useLines(charset = Charsets.UTF_8, block = { line ->
            line.map { it.length }.sorted().forEach { println(it) }
        })

        // File.forEachLine(charset: Charset = Charsets.UTF_8, action: (line: String) -> Unit): Unit
        file.forEachLine {
            println(it)
        }

        file.forEachLine(charset = Charsets.UTF_8, action = { line ->
            println(line)
        })
    }

    fun fileReader() {
        val file = File("test.txt")

        // readLines -> List<String>
        file.reader().readLines().forEach { println(it) }

        file.reader().readLines().joinToString(separator = ":", prefix = "[", postfix = "]", transform = {
            it.trim()
        })
        .also { text -> println("$text") }

        // useLines
        file.reader().useLines {
            it.map { it.toLowerCase() }.forEach { println(it) }
        }

        // block: (Sequence<String>) -> T
        file.reader().useLines(block = {
            println("sorted")
            it.sortedBy { it.length }.forEach { println(it) }
            println()
        })

        // forEachLine
        file.reader().forEachLine {
            println(it)
        }

        // action: (String) -> Unit
        file.reader().forEachLine(action = { line ->
            println(line)
        })
    }

    fun fileNotFound() {
        try {
            loader("unknown.txt")
        } catch (e: Exception) {
            println(e)
        }
    }

}