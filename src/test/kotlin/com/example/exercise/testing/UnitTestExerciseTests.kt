package com.example.exercise.testing

import org.assertj.core.api.Assertions.*
import org.junit.*

import org.junit.rules.ExpectedException
import java.time.LocalDate

class UnitTestExerciseTests {

    private val sut: UnitTestExercise = UnitTestExercise()

    @Rule
    private val expectedException: ExpectedException = ExpectedException.none()

    @Before
    fun setup() {
        println("Before")
    }

    @After
    fun tearDown() {
        println("After")
    }

    @Test
    fun testFormat() {
        val pattern = "yyyy/MM/dd"
        val date = LocalDate.of(2018, 1, 22)
        val actual = sut.format(date, pattern)

        assertThat(actual).isEqualTo("2018/01/22")
    }

    @Ignore
    @Test
    fun errorIfNot() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Exception Message")
    }

}