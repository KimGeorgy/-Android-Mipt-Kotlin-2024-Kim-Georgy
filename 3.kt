fun main() {
    val n = readln().toInt()
    val rootKey = readln().toLong()
    val root = Node(rootKey)
    var key: Long

    for (i in 1..n-1) {
        key = readln().toLong()
        root.add(key)
    }

    root.print()
}

class Node (
    private var key: Long,
    private var left: Node? = null,
    private var right: Node? = null
) {
    fun add(key: Long) {
        if (key < this.key)
            if (this.left == null)
                this.left = Node(key)
            else
                this.left!!.add(key)
        else
            if (this.right == null)
                this.right = Node(key)
            else
                this.right!!.add(key)

    }

    fun print() {
        print(this.key.toString() + ' ')
        this.left?.print()
        this.right?.print()
    }
}
