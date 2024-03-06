fun main() {
    var (a, b) = readln().split(' ').map(String::toInt)

    while (a % b != 0) {
        val c = a % b
        a = b
        b = c
    }

    println(b)
}