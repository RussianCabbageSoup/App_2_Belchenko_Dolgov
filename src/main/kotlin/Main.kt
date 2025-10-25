import kotlin.math.abs
import kotlin.math.log
import kotlin.math.round
import kotlin.random.Random

fun main() {

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

    print("Введите количество строк: ")
    val rows = readln().toInt()
    print("Введите количество столбцов: ")
    val cols = readln().toInt()

    var arr: Array<Array<Int>> =  Array(rows) { Array(cols) { 0 } }
    println("Введите $rows×$cols трехзначных чисел:")
    val count = mutableSetOf<Char>()

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            var input: Int
            do {
                print("Элемент [$i][$j]: ")
                input = readln().toInt()
                if (input !in 100..999) {
                    println("Число должно быть трехзначным.")
                }
            } while (input !in 100..999)
            arr[i][j] = input
        }
    }

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            arr[i][j].toString().forEach { count.add(it) }
        }
    }

    println("\nВведенный массив:")
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            print("%4d".format(arr[i][j]))
        }
        println()
    }

    println("\nИспользовано различных цифр: ${count.size}")
}

fun task2() {

    val matrix = Array(5) {
        IntArray(5) { Random.nextInt(1, 11) }
    }

    val matrix2 = arrayOf(
        intArrayOf(5, 9, 6, 7, 2),
        intArrayOf(9, 8, 4, 5, 3),
        intArrayOf(6, 4, 3, 8, 7),
        intArrayOf(7, 5, 8, 4, 8),
        intArrayOf(2, 3, 7, 8, 1)
    )

    println("Нажмите 1 чтобы получить симметричный массив\nНажмите 2 чтобы получить массив случайных чисел")
    val choose = readln().toInt()
    if (choose == 1) {
        println("\nСимметричный Массив:")
        for (i in 0..4) {
            for (j in 0..4) {
                print("%4d".format(matrix2[i][j]))
            }
            println()
        }

        println("Матрица симметрична: ${Symmetric(matrix2)}")

    } else if (choose == 2){
        println("\nСлучайный массив:")
        for (i in 0..4) {
            for (j in 0..4) {
                print("%4d".format(matrix[i][j]))
            }
            println()
        }

        println("Матрица симметрична: ${Symmetric(matrix)}")
    } else {
        println("Введите 1 или 2")
    }
}
fun Symmetric(matrix: Array<IntArray>) : Boolean{

    var isSymmetric = true

    for (i in 0..4) {
        for (j in (i + 1)..4) {
            if (matrix[i][j] != matrix[j][i]) {
                isSymmetric = false
                break
            }
        }
        if (!isSymmetric) break
    }

    return isSymmetric
}
fun task3() {
    val alphabet = mapOf(
        'А' to 21, 'Б' to 13, 'В' to 4, 'Г' to 20, 'Д' to 22, 'Е' to 1, 'Ё' to 25, 'Ж' to 12,
        'З' to 24, 'И' to 14, 'Й' to 2, 'К' to 28, 'Л' to 9, 'М' to 23, 'Н' to 3, 'О' to 29,
        'П' to 6, 'Р' to 16, 'С' to 15, 'Т' to 11, 'У' to 26, 'Ф' to 5, 'Х' to 30, 'Ц' to 27,
        'Ч' to 8, 'Ш' to 18, 'Щ' to 10, 'Ь' to 33, 'Ы' to 31, 'Ъ' to 32, 'Э' to 19, 'Ю' to 7, 'Я' to 17
    )

    print("Операция (1-шифровка, 2-дешифровка): ")
    when (readln().toInt()) {
        1 -> process(true, alphabet)
        2 -> process(false, alphabet)
        else -> println("Ошибка")
    }
}

fun process(encrypt: Boolean, alphabet: Map<Char, Int>) {
    print("Ключ: ")
    val key = readln().uppercase()
    print("Текст: ")
    val text = readln().uppercase()

    val result = StringBuilder()
    var keyIndex = 0

    for (char in text) {
        if (char in alphabet) {
            val charCode = alphabet[char]!!
            val keyCode = alphabet[key[keyIndex % key.length]]!!
            val newCode = if (encrypt) {
                (charCode + keyCode - 1) % 33 + 1
            } else {
                (charCode - keyCode + 33 - 1) % 33 + 1
            }
            result.append(alphabet.entries.find { it.value == newCode }?.key)
            keyIndex++
        } else {
            result.append(char)
        }
    }

    println("Результат: $result")
}

fun task4() {
    println("Введите элементы первого массива через пробел:")
    val firstArray = readln().split(" ").mapNotNull { it.toIntOrNull() }
    println("Введите элементы второго массива через пробел:")
    val secondArray = readln().split(" ").mapNotNull { it.toIntOrNull() }
    val result = findIntersection(firstArray, secondArray)

    println("Результат пересечения: ${result.joinToString()}")
}

fun findIntersection(first: List<Int>, second: List<Int>): List<Int> {
    val secondFrequency = second.groupingBy { it }.eachCount().toMutableMap()
    val result = mutableListOf<Int>()

    for (num in first) {
        if (secondFrequency.containsKey(num) && secondFrequency[num]!! > 0) {
            result.add(num)
            secondFrequency[num] = secondFrequency[num]!! - 1
        }
    }

    return result
}

fun task5() {
    println("Введите слова через пробел:")
    val input = readln().toString()

    if (input.isNullOrEmpty()) {
        println("Ввод не может быть пустым")
        return
    }

    val words = input.split(" ").filter { it.isNotBlank() }

    val groupedWords = words.groupBy { word ->
        word.toCharArray().sorted().joinToString("")
    }

    println("Сгруппированные слова:")
    groupedWords.values.forEach { group ->
        println(group.joinToString(", ", prefix = "\"", postfix = "\""))
    }
}
