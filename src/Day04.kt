fun main() {


    fun part1(input: List<String>): Int {
        val chosen = input.take(1)[0].split(',').map { it.toInt() }
        val grids = input.takeLast(input.size - 1).asSequence().windowed(6, 6)
            .map { list ->
                list.takeLast(5).map { s ->
                    s.split(" ")
                        .filter { it.isNotEmpty() }.map { it.toInt() }
                }
            }
            .toList()
        val flattenGrids = grids.flatten()
        (8 until chosen.size).asSequence()
            .forEach { i ->
                val chose = chosen.take(i)
                val rowStatus = grids.map { list -> list.any { chose.containsAll(it) } }
                val columnStatus =
                    grids.map { list ->
                        list.mapIndexed { index, insts -> (0 until 5).map { list[it][index] } }
                            .any { chose.containsAll(it) }
                    }
                val status = rowStatus.zip(columnStatus).map { it.first || it.second }
                if (status.any { it }) {
                    val index = status.indexOf(true)
                    val unmarked = grids[index].flatten().filter { !chose.contains(it) }.sum()
                    return unmarked * chose.last()
                }
            }
        return 0
    }


    fun part2(input: List<String>): Int {
        val chosen = input.take(1)[0].split(',').map { it.toInt() }
        val grids = input.takeLast(input.size - 1).asSequence().windowed(6, 6)
            .map { list ->
                list.takeLast(5).map { s ->
                    s.split(" ")
                        .filter { it.isNotEmpty() }.map { it.toInt() }
                }
            }
            .toList()
        val flattenGrids = grids.flatten()
        (8 until chosen.size).reversed().asSequence()
            .forEach { i ->
                val chose = chosen.take(i)
                val rowStatus = grids.map { list -> list.any { chose.containsAll(it) } }
                val columnStatus =
                    grids.map { list ->
                        list.mapIndexed { index, insts -> (0 until 5).map { list[it][index] } }
                            .any { chose.containsAll(it) }
                    }
                val status = rowStatus.zip(columnStatus).map { it.first || it.second }
                if (!status.all { it }) {
                    val index = status.indexOf(false)
                    val newChose = chosen.take(i + 1)
                    val unmarked = grids[index].flatten().filter { !newChose.contains(it) }.sum()
                    return unmarked * newChose.last()
                }
            }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))

}
