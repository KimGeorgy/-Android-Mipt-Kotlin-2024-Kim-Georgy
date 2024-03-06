import java.util.*


fun main() {
    var zoo = Zoo()
    val cat = Cat(100.0)
    zoo = Zoo(listOf(cat, Dog(1.2), Hippo(1.75), Horse(2.34), Fish(0.2)))
    val cats = zoo.animalsIsInstance<Cat>()
    zoo.remove(cat.id)
}

class Zoo(
    val animals: TreeSet<Animal> = TreeSet(compareBy<Animal> { it.height }),
    val animalIds: HashMap<UUID, Animal> = HashMap(),
    private val zookeepers: HashMap<UUID, Zookeeper> = HashMap(),
    private val assignment: HashMap<Animal, Zookeeper> = HashMap()
) {
    constructor(collection: Collection<Animal>) : this() {
        animals.addAll(collection)
        val firstZookeeper = getFirstZookeeper()
        for (animal in collection) {
            assignment[animal] = firstZookeeper
        }
    }

    fun getFirstZookeeper(): Zookeeper {
        val firstZookeeper = Zookeeper("Zack")
        zookeepers[firstZookeeper.id] = firstZookeeper
        return firstZookeeper
    }

    fun add(animal: Animal) {
        if (zookeepers.isEmpty()) {
            getFirstZookeeper()
        }
        animals.add(animal)
        assignment[animal] = zookeepers.values.first()
    }

    fun findAnimal(id: UUID): Animal? {
        return animalIds[id]
    }

    fun remove(id: UUID) {
        val animal = findAnimal(id)
        animals.remove(animal)
        assignment.remove(animal)
    }

    fun assign(animal: Animal, zookeeper: Zookeeper) {
        if (!animals.contains(animal)) {
            this.add(animal)
        }
        assignment[animal] = zookeeper
    }

    fun assignById(animal: Animal, zookeeperId: UUID) {
        if (!animals.contains(animal)) {
            this.add(animal)
        }
        assignment[animal] = zookeepers[zookeeperId]!!
    }

    fun findByZookeeper(zookeeperId: UUID): Collection<Animal> {
        return assignment.filterValues{ it.id == zookeeperId }.keys
    }

    fun findByZookeeper(zookeeperName: String): Collection<Animal> {
        return assignment.filterValues{ it.name == zookeeperName }.keys
    }

    fun animalsHigherThen(height: Double): Collection<Animal>? {
        return animals.tailSet(Cat(height))
    }

    fun animalsTalking(): Collection<Animal> {
        return animals.filterIsInstance<Talking>()
    }

    inline fun <reified T: Animal> animalsIsInstance(): Collection<Animal> {
        return animals.filterIsInstance<T>()
    }
}

abstract class Animal(
    open val id: UUID = UUID.randomUUID(),
    open val height: Double,
    open val species: String
)

abstract class Talking(
    override val height: Double,
    override val species: String,
    open val says: String
): Animal(height = height, species = species)

class Cat(
    override val height: Double
): Talking(height = height, species = "cat", says = "Meow")

class Dog(
    override val height: Double
): Talking(height = height, species = "dog", says = "Woof")

class Hippo(
    override val height: Double
): Talking(height = height, species = "hippo", says = "Good Morning")

class Horse(
    override val height: Double
): Talking(height = height, species = "horse", says = "Neigh")

class Fish(
    override val height: Double
): Animal(height = height, species = "fish")

class Zookeeper(
    val name: String,
    val id: UUID = UUID.randomUUID()
)