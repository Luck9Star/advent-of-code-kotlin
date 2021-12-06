fun main() {
    fun part1(input: List<String>): Int {
        var inputs = input.map { it.split(",") }.flatten().map { it.toInt() }
        val life = 6
        val bron = 8
        val totalDays = 80
        for (i in 0 until totalDays) {
            val ages = inputs.map { it - 1 }
            val newBron = ages.count { it == -1 }
            inputs = ages.plus(List(newBron) { bron }).map { if (it == -1) life else it }
        }
        return inputs.size
    }


    fun part2(input: List<String>): Long {
        var inputs = input.map { it.split(",") }.flatten().map { it.toInt() }
        val life = 6
        val bron = 8
        val totalDays = 256
        var map = inputs.groupBy { it }.mapValues { it.value.count().toLong() }
        for (i in 0 until totalDays) {
            val newMap = map.mapKeys { it.key - 1 }.toMutableMap()
            newMap[-1]?.let {
                newMap[bron] = it
                if (newMap.containsKey(life)) {
                    newMap[life] = newMap[life]!!.plus(it)
                } else {
                    newMap[life] = it
                }
                newMap.remove(-1)
            }
            map = newMap
        }
        return map.values.sum()
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    val input = readInput("Day06")
    check(part1(testInput) == 5934)
    println(part1(input))

    check(part2(testInput) == 26984457539)
    println(part2(input))

}
