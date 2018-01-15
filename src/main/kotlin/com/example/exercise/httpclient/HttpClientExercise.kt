package com.example.exercise.httpclient

import com.example.data.WeatherMap
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

fun main(args: Array<String>) {
    HttpClientExercise().tryAll()
}

/**
 * Fuel
 *
 * [Github - Fuel] (https://github.com/kittinunf/Fuel)
 */
class HttpClientExercise {

    companion object {
        private const val SAMPLE_URL: String = "http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22"
    }

    fun tryAll() {
        getApi()
    }

    fun getApi() {
        SAMPLE_URL.httpGet().responseString { _, _, result ->
            when(result) {
                is Result.Success -> {
                    val data = result.getAs<String>()
                    data?.let {
                        val mapper = jacksonObjectMapper()
                        val jsonData: WeatherMap = mapper.readValue(data)
                        println(jsonData)
                    }
                }
                is Result.Failure -> {
                    val error = result.getAs<String>()
                    error?.let {
                        println(error)
                    }
                }
            }
        }
    }

}