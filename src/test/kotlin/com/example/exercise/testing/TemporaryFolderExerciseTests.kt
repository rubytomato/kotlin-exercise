package com.example.exercise.testing

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class TemporaryFolderExerciseTests {

    companion object {
        fun loader(name: String): Path {
            val url: URL = ClassLoader.getSystemResource(name)
            return File(url.file).toPath()
        }
        fun copy(srcTemplateFile: String, root: String, destFile: String): Unit {
            val src = loader("data/template/$srcTemplateFile")
            val dest = Paths.get(root, destFile)
            Files.copy(src, dest)
        }
    }

    @Rule
    @JvmField
    val tempFolder: TemporaryFolder = TemporaryFolder()
    lateinit var root: String

    @Before
    fun setup() {
        println("setup: root=${tempFolder.root}")
        root = tempFolder.root.toString()
    }

    @After
    fun tearDown() {
        println("tear down:")
        //tempFolder.delete()
    }

    @Test
    fun test1() {
        println("test1")
        // setup
        copy("test1.txt", root, "demo.txt")

        // exercise
        val testFile = Paths.get(root, "demo.txt").also { println(it.toAbsolutePath().toString()) }

        Files.readAllLines(testFile).forEach {
            println(it)
        }
    }

    @Test
    fun test2() {
        println("test2")
        // setup
        copy("test2.txt", root, "demo.txt")

        // exercise
        val testFile = Paths.get(root, "demo.txt").also { println(it.toAbsolutePath().toString()) }

        Files.readAllLines(testFile).forEach {
            println(it)
        }
    }

}