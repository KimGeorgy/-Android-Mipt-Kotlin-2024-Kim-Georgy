fun main() {
    val input = readln().split(' ')
    val n = input[0].toLong()
    val m = input[1].toInt()

    val fib: MutableList<Int> = mutableListOf(0, 1)

    for (i in 2..n) {
        val listLast = fib.takeLast(2)

        if (i > 2 && listLast == listOf(0, 1)) {
            println(fib[(n % (i - 2)).toInt()])
            return
        }

        fib.add((listLast[0] + listLast[1]) % m)
    }
    println(fib.takeLast(1)[0])
}