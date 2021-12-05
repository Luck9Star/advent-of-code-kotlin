import kotlin.math.abs

fun main() {

    class Point(val first: Int, val second: Int) : Comparable<Point> {
        override fun compareTo(other: Point): Int {
            if (this.first != other.first) {
                return this.first - other.first
            }
            return this.second - other.second
        }
    }

    fun part1(input: List<String>): Int {
        val datas = input.map { s ->
            s.split(" -> ").map {
                val d = it.split(",")
                Pair(d[0].toInt(), d[1].toInt())
            }
        }
        val x = datas.map { list -> list.map { it.first } }.flatten()
        val y = datas.map { list -> list.map { it.second } }.flatten()
        val xEnd = x.maxOrNull()!!
        val xStart = x.minOrNull()!!
        val yEnd = y.maxOrNull()!!
        val yStart = y.minOrNull()!!
        val horizonRange = datas.filter { it[0].first == it[1].first }
            .map { list ->
                (list[0].second.coerceAtMost(list[1].second)..list[0].second.coerceAtLeast(list[1].second)).map {
                    Pair(
                        list[0].first,
                        it
                    )
                }
            }.flatten()
        val verticalRange = datas.filter { it[0].second == it[1].second }
            .map { list ->
                (list[0].first.coerceAtMost(list[1].first)..list[0].first.coerceAtLeast(list[1].first)).map {
                    Pair(
                        it,
                        list[0].second
                    )
                }
            }.flatten()
        val ranges = horizonRange.plus(verticalRange)
        return ranges.groupingBy { it }.eachCount().filter { (_, v) -> v >= 2 }.count()
    }


    fun part2(input: List<String>): Int {
        val datas = input.map { s ->
            s.split(" -> ").map {
                val d = it.split(",")
                Pair(d[0].toInt(), d[1].toInt())
            }
        }
        val x = datas.map { list -> list.map { it.first } }.flatten()
        val y = datas.map { list -> list.map { it.second } }.flatten()
        val xEnd = x.maxOrNull()!!
        val xStart = x.minOrNull()!!
        val yEnd = y.maxOrNull()!!
        val yStart = y.minOrNull()!!
        val horizonRange = datas.filter { it[0].first == it[1].first }
            .map { list ->
                (list[0].second.coerceAtMost(list[1].second)..list[0].second.coerceAtLeast(list[1].second)).map {
                    Pair(
                        list[0].first,
                        it
                    )
                }
            }.flatten()
        val verticalRange = datas.filter { it[0].second == it[1].second }
            .map { list ->
                (list[0].first.coerceAtMost(list[1].first)..list[0].first.coerceAtLeast(list[1].first)).map {
                    Pair(
                        it,
                        list[0].second
                    )
                }
            }.flatten()
        val diagonal = datas.filter { abs(it[0].first - it[1].first) == abs(it[0].second - it[1].second) }
            .map { list ->
                val x =
                    if (list[0].first < list[1].first) (list[0].first..list[1].first) else (list[1].first..list[0].first).reversed()
                val y =
                    if (list[0].second < list[1].second) (list[0].second..list[1].second) else (list[1].second..list[0].second).reversed()
                x.zip(y)
            }.flatten()
        val ranges = horizonRange.plus(verticalRange).plus(diagonal)
        return ranges.groupingBy { it }.eachCount().filter { (_, v) -> v >= 2 }.count()
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    val input = readInput("Day05")
    println(part1(input))

    check(part2(testInput) == 12)
    println(part2(input))

}
