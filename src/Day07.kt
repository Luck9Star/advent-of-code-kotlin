import kotlin.math.abs

fun main() {

    fun minMoves2(nums: List<Int>): Int {
        val sorted = nums.sorted();
        val mid = sorted[sorted.size / 2]
        return nums.sumOf { abs(mid - it) }
    }

    fun part1(input: List<String>): Int {
        val inputs = input.map { it.split(",") }.flatten().map { it.toInt() }
        return minMoves2(inputs)
    }


    fun part2(input: List<String>): Long {
        val inputs = input.map { it.split(",") }.flatten().map { it.toLong() }.sorted()
        val distinctSorted = (inputs.first()..inputs.last()).toList()
        var index = distinctSorted.size / 2
        var cur = 0L
        var pre = 0L
        var aft = 0L
        while (index < distinctSorted.size && index > 0) {
            if (pre == 0L) {
                pre = inputs.map { abs(it - distinctSorted[index - 1]) }.sumOf { it * (1L + it) / 2  }
            }
            if (cur == 0L) {
                cur = inputs.map { abs(it - distinctSorted[index]) }.sumOf { it * (1L + it) / 2 }
            }
            if (aft == 0L) {
                aft = inputs.map { abs(it - distinctSorted[index + 1]) }.sumOf { it * (1L + it) / 2}
            }
            println(
                String.format(
                    "num:%d,%d,%d, pre:%d, cur:%d, aft:%d",
                    distinctSorted[index - 1],
                    distinctSorted[index],
                    distinctSorted[index + 1],
                    pre,
                    cur,
                    aft
                )
            )
            if (cur in (pre + 1) until aft) {
                index -= 1
                aft = cur
                cur = pre
                pre = 0L
            } else if (cur in (aft + 1) until pre) {
                index += 1
                pre = cur
                cur = aft
                aft = 0L
            } else if (cur <= pre && cur <= aft) {
                return cur
            }
        }
        return 0L
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    val input = readInput("Day07")
    check(part1(testInput) == 37)
    println(part1(input))

    check(part2(testInput) == 168L)
    println(part2(input))

}
