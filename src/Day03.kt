import kotlin.math.pow
import kotlin.math.roundToInt


fun main() {

    fun binToInt10(input: List<Int>): Int {
        val len = input.size
        return input.mapIndexed { i, t -> if (t == 1) 2.0.pow((len - i - 1).toDouble()).toInt() else 0 }.sum()
    }

    fun part1(input: List<String>): Int {
        val data = input.map { it -> it.toList().map(Character::getNumericValue) }
        val len = data[0].size
        val gammas = (0 until len).map { i ->
            data.map { it[i] }.average().roundToInt()
        }
        val epsilons = gammas.map { 1 - it }


        val gamma = binToInt10(gammas)
        val epsilon = binToInt10(epsilons)


        return Math.multiplyExact(gamma, epsilon)
    }


    fun lifeSupport(data: List<List<Int>>, index: Int, co2: Boolean): List<Int> {
        var bin = data.map { it[index] }.average().roundToInt()
        if (co2) bin = 1 - bin
        val binaries = data.filter { it[index] == bin }
        return if (binaries.size > 1) {
            lifeSupport(binaries, index + 1, co2)
        } else {
            binaries[0]
        }
    }

    fun part2(input: List<String>): Int {
        val data = input.map { it -> it.toList().map(Character::getNumericValue) }
        val len = data[0].size
        val life = binToInt10(lifeSupport(data, 0, false))
        val co2s = binToInt10(lifeSupport(data, 0, true))
        return Math.multiplyExact(life, co2s)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
