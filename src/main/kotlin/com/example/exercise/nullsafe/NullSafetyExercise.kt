package com.example.exercise.nullsafe

fun <T : Any> T?.notNull(actions: (T) -> Unit) {
    if (this != null) actions(this)
}

fun main(args: Array<String>) {
    NullSafetyExercise().tryAll()
}

class NullSafetyExercise {

    fun tryAll() {
        demoNotNull("xxx")
        demoNotNull(null)

        demoLetWithNotNull("xxx")
        demoLetWithNotNull(null)
    }

    fun demoNotNull(s: String?) {
        s.notNull {
            println("notNull: $it")
        }
    }

    fun demoLetWithNotNull(s: String?) {
        s?.let {
            println("let: $it")
        }
    }

}