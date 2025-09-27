import kotlin.math.abs
import kotlin.math.log
import kotlin.math.round

fun main() {

    println("Hello World!")
    while (true) {

        println("Choose the application (1-5)")
        val input = readln()
        val value = input.toIntOrNull()
        println()

        when (value) {
            1 -> {
                println(task1())
            }

            2 -> {
                println(task2())
            }

            3 -> {

                println(task3())

            }

            4 -> {

                println(task4())

            }

            5 -> {

                println(task5())

            }

            else -> {
                println("Wrong exit")
                break;
            }

        }
        println();
    }

}

fun task1(){

}

fun task2() {

}

fun task3() {

}

fun task4() {

}

fun task5() {

}


