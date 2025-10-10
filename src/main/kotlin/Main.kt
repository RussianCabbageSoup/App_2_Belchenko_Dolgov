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
 // Запрос размеров массива
    print("Введите количество строк: ")
    val rows = readLine()!!.toInt()
    print("Введите количество столбцов: ")
    val cols = readLine()!!.toInt()

    // Создание и заполнение массива
    val array = Array(rows) { IntArray(cols) }
    println("Введите $rows×$cols трехзначных чисел:")

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            array[i][j] = readLine()!!.toInt().also {
                require(it in 100..999) { "Число должно быть трехзначным" }
            }
        }
    }

    // Подсчет уникальных цифр
    val uniqueDigits = mutableSetOf<Char>()
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            uniqueDigits.addAll(array[i][j].toString().toList())
        }
    }

    // Вывод результатов
    println("\nМассив:")
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            print("%6d".format(array[i][j]))
        }
        println()
    }

    println("\nВ массиве использовано ${uniqueDigits.size} различных цифр")
}

fun task2() {
 val matrix = arrayOf(
        intArrayOf(5, 9, 6, 7, 2),
        intArrayOf(9, 8, 4, 5, 3),
        intArrayOf(6, 4, 3, 8, 7),
        intArrayOf(7, 5, 8, 4, 8),
        intArrayOf(2, 3, 7, 8, 1)
    )

    var isSymmetric = true

    // Проверяем элементы выше главной диагонали
    for (i in 0..4) {
        for (j in (i + 1)..4) {
            if (matrix[i][j] != matrix[j][i]) {
                isSymmetric = false
                break
            }
        }
        if (!isSymmetric) break
    }

    println("Матрица симметрична: $isSymmetric")
}

fun task3() {
    // Заданный алфавит с номерами
    val alphabet = listOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ', 'Э', 'Ю', 'Я'
    )
    val numbers = listOf(
        21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17
    )

    // Создаем отображения: символ -> номер и номер -> символ
    val charToNumber = alphabet.zip(numbers).toMap()
    val numberToChar = numbers.zip(alphabet).toMap()

    // Функция для получения номера символа
    fun getCharNumber(char: Char): Int {
        return charToNumber[char] ?: throw IllegalArgumentException("Неизвестный символ: $char")
    }

    // Функция для получения символа по номеру
    fun getCharFromNumber(number: Int): Char {
        return numberToChar[number] ?: throw IllegalArgumentException("Неизвестный номер: $number")
    }

    // Функция модуля с учетом закольцованности алфавита
    fun mod33(n: Int): Int {
        val result = n % 33
        return if (result <= 0) result + 33 else result
    }

    // Функция шифрования
    fun encrypt(text: String, key: String): String {
        val upperText = text.uppercase()
        val upperKey = key.uppercase()
        val result = StringBuilder()
        for (i in upperText.indices) {
            val textChar = upperText[i]
            val keyChar = upperKey[i % upperKey.length]
            val textNumber = getCharNumber(textChar)
            val keyNumber = getCharNumber(keyChar)
            val encryptedNumber = mod33(textNumber + keyNumber)
            result.append(getCharFromNumber(encryptedNumber))
        }
        return result.toString()
    }

    // Функция дешифровки
    fun decrypt(text: String, key: String): String {
        val upperText = text.uppercase()
        val upperKey = key.uppercase()
        val result = StringBuilder()
        for (i in upperText.indices) {
            val textChar = upperText[i]
            val keyChar = upperKey[i % upperKey.length]
            val textNumber = getCharNumber(textChar)
            val keyNumber = getCharNumber(keyChar)
            val decryptedNumber = mod33(textNumber - keyNumber)
            result.append(getCharFromNumber(decryptedNumber))
        }
        return result.toString()
    }

    // Взаимодействие с пользователем
    println("Выберите действие: (1) Зашифровать, (2) Расшифровать")
    when (readLine()?.toIntOrNull()) {
        1 -> {
            println("Введите текст для шифрования:")
            val text = readLine() ?: ""
            println("Введите ключевое слово:")
            val key = readLine() ?: ""
            println("Зашифрованный текст: ${encrypt(text, key)}")
        }
        2 -> {
            println("Введите текст для дешифровки:")
            val text = readLine() ?: ""
            println("Введите ключевое слово:")
            val key = readLine() ?: ""
            println("Расшифрованный текст: ${decrypt(text, key)}")
        }
        else -> println("Некорректный выбор")
    }
}
}

fun task4() {
println("Введите элементы первого массива через пробел:")
    val firstArray = readLine()?.split(" ")?.map { it.toIntOrNull() }?.filterNotNull() ?: emptyList()

    println("Введите элементы второго массива через пробел:")
    val secondArray = readLine()?.split(" ")?.map { it.toIntOrNull() }?.filterNotNull() ?: emptyList()

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
}

fun task5() {
    println("Введите слова через пробел:")
    val input = readLine()
    
    if (input.isNullOrEmpty()) {
        println("Ошибка: ввод не может быть пустым")
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
}
