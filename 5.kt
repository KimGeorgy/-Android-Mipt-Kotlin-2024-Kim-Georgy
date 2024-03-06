fun main() {
    val q = readln().toInt()
    val stack = Stack()
    for (i in 1..q) {
        val query = readln().split(' ')
        when (query[0]) {
            "push" -> stack.push(query[1].toInt())
            "pop" -> stack.pop()
            "max" -> println(stack.max())
        }
    }
}

class Node (
    val max: Int,
    var prev: Node? = null
)

class Stack (
    private var head: Node? = null,
    private var tail: Node? = null
) {
    fun push(v: Int) {
        if (head == null) {
            head = Node(v)
            tail = head
        } else {
            val newNode = Node(if (v > tail!!.max) v else tail!!.max)
            newNode.prev = tail
            tail = newNode
        }
    }

    fun pop() {
        tail = tail?.prev
    }

    fun max(): Int {
        return tail!!.max
    }
}