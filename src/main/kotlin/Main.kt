import kotlin.math.sqrt

data class Point(val x: Double, val y: Double) {
    fun distance(p: Point): Double {
        val dx = p.x - x
        val dy = p.y - y
        return sqrt(dx * dx + dy * dy)
    }
}

fun main() {
    print("Введите количество точек (больше 2): ")
    val numPoints = readLine()!!.toInt()
    if (numPoints <= 2) {
        println("Количество точек должно быть больше 2.")
        return
    }

    val points = mutableListOf<Point>()
    println("Введите координаты точек (x y): ")
    for (i in 1..numPoints) {
        val input = readLine()!!.split(" ")
        if (input.size != 2) {
            println("Некорректный ввод. Должно быть 2 числа.")
            return
        }
        // Проверка ввода координат на корректность (только цифры и минусы)
        for (i in input.indices) {
            if (!input[i].matches(Regex("^-?\\d+$"))) {
                println("Некорректный ввод. Разрешены только цифры и минусы.")
                return
            }
        }
        val x = input[0].toDouble()
        val y = input[1].toDouble()
        points.add(Point(x, y))
    }

    var minD = Double.MAX_VALUE
    var maxD = Double.MIN_VALUE

    for (i in 0 until numPoints - 1) {
        for (j in i + 1 until numPoints) {
            val distance = points[i].distance(points[j])
            minD = minOf(minD, distance)
            maxD = maxOf(maxD, distance)
        }
    }

    println("Мин расстояние: $minD")
    println("Макс расстояние: $maxD")
}