import kotlin.random.Random

fun main() {
    while (true) {
        println("Choose the application (1-5)")
        val input = readlnOrNull() ?: break  // Исправлено: используем readlnOrNull()
        val value = input.toIntOrNull()
        println()

        when (value) {
            1 -> {
                task1()
            }
            2 -> {
                task2()
            }
            3 -> {
                task3()
            }
            4 -> {
                task4()
            }
            5 -> {
                task5()
            }
            else -> {
                println("Wrong exit")
                break
            }
        }
        println()
    }
}

fun task1() {
    print("Введите количество строк: ")
    val rows = readlnOrNull()?.toIntOrNull() ?: 0  // Исправлено: используем readlnOrNull()
    print("Введите количество столбцов: ")
    val cols = readlnOrNull()?.toIntOrNull() ?: 0  // Исправлено: используем readlnOrNull()
    
    if (rows <= 0 || cols <= 0) {
        println("Некорректные размеры массива")
        return
    }

    val arr = Array(rows) { Array(cols) { 0 } }
    val count = mutableSetOf<Char>()

    println("Введите $rows×$cols трехзначных чисел:")
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            var input: Int
            do {
                print("Элемент [$i][$j]: ")
                val inputStr = readlnOrNull()  // Исправлено: используем readlnOrNull()
                input = inputStr?.toIntOrNull() ?: 0
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
    val choose = readlnOrNull()?.toIntOrNull() ?: 0  // Исправлено: используем readlnOrNull()
    
    when (choose) {
        1 -> {
            println("\nСимметричный Массив:")
            for (i in 0..4) {
                for (j in 0..4) {
                    print("%4d".format(matrix2[i][j]))
                }
                println()
            }
            println("Матрица симметрична: ${Symmetric(matrix2)}")
        }
        2 -> {
            println("\nСлучайный массив:")
            for (i in 0..4) {
                for (j in 0..4) {
                    print("%4d".format(matrix[i][j]))
                }
                println()
            }
            println("Матрица симметрична: ${Symmetric(matrix)}")
        }
        else -> println("Введите 1 или 2")
    }
}

fun Symmetric(matrix: Array<IntArray>): Boolean {
    for (i in 0..4) {
        for (j in (i + 1)..4) {
            if (matrix[i][j] != matrix[j][i]) {
                return false
            }
        }
    }
    return true
}

fun task3() {
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
    
    fun getIndex(char: Char): Int = alphabet.indexOf(char)
    fun getChar(index: Int): Char = alphabet[index % 33]
    
    println("Выберите действие: (1) Зашифровать, (2) Расшифровать")
    when (readlnOrNull()?.toIntOrNull()) {  // Исправлено: используем readlnOrNull()
        1 -> {
            println("Введите текст для шифрования:")
            val text = readlnOrNull()?.uppercase() ?: ""  // Исправлено: используем readlnOrNull()
            println("Введите ключевое слово:")
            val key = readlnOrNull()?.uppercase() ?: ""  // Исправлено: используем readlnOrNull()
            
            val result = StringBuilder()
            for (i in text.indices) {
                val textIndex = getIndex(text[i])
                val keyIndex = getIndex(key[i % key.length])
                val encryptedIndex = (textIndex + keyIndex) % 33
                result.append(getChar(encryptedIndex))
            }
            println("Зашифрованный текст: $result")
        }
        2 -> {
            println("Введите текст для дешифровки:")
            val text = readlnOrNull()?.uppercase() ?: ""  // Исправлено: используем readlnOrNull()
            println("Введите ключевое слово:")
            val key = readlnOrNull()?.uppercase() ?: ""  // Исправлено: используем readlnOrNull()
            
            val result = StringBuilder()
            for (i in text.indices) {
                val textIndex = getIndex(text[i])
                val keyIndex = getIndex(key[i % key.length])
                val decryptedIndex = (textIndex - keyIndex + 33) % 33
                result.append(getChar(decryptedIndex))
            }
            println("Расшифрованный текст: $result")
        }
        else -> println("Некорректный выбор")
    }
}

fun task4() {
    println("Введите элементы первого массива через пробел:")
    val firstArray = readlnOrNull()?.split(" ")?.mapNotNull { it.toIntOrNull() } ?: emptyList()  // Исправлено

    println("Введите элементы второго массива через пробел:")
    val secondArray = readlnOrNull()?.split(" ")?.mapNotNull { it.toIntOrNull() } ?: emptyList()  // Исправлено

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
    val input = readlnOrNull() ?: ""  // Исправлено: используем readlnOrNull()

    if (input.isEmpty()) {
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
