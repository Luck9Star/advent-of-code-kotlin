fun main() {
    fun part1(input: List<String>): Int? {

        val data = input.asSequence().map { it.split(" ") }.groupBy({ it[0] }, { it[1] })
            .mapValues { it -> it.value.sumOf { it.toInt() } }
        return data["forward"]?.times((data["up"].let { it?.let { it1 -> data["down"]?.minus(it1) ?: 0 } ?: 0 }))
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var distance = 0
        return input.asSequence().map { s ->
            val values = s.split(" ")
            val name = values[0]
            val num = values[1].toInt()
            when (name) {
                "forward" -> {
                    distance += num
                    return@map Triple(name, num.toInt(), num * depth)
                }
                "up" -> {
                    depth -= num
                    return@map Triple(num, num, depth)
                }
                else -> {
                    depth += num
                    return@map Triple(num, num, depth)
                }
            }
        }.filter { it.first == "forward" }.map {
            it.third
        }.sum() * distance

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
