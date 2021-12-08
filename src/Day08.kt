import kotlin.math.pow

fun main() {


    fun part1(input: List<String>): Int {
        val inputs = input.map { it.split("|")[1].split(" ") }.flatten().filterNotNull()
        return inputs.map { it.length }.count { listOf(2, 3, 4, 7).contains(it) }
    }

    val keyMap = mapOf(
        Pair("abcefg", 0),
        Pair("cf", 1),
        Pair("acdeg", 2),
        Pair("acdfg", 3),
        Pair("bcdf", 4),
        Pair("abdfg", 5),
        Pair("abdefg", 6),
        Pair("acf", 7),
        Pair("abcdefg", 8),
        Pair("abcdfg", 9)
    )

    fun part2(input: List<String>): Long {
        val inputs = input.map { it.split(" ").filter { it != "|" }.filterNotNull() }
        val results = inputs.map { list ->
            val digs = list.take(10)
            val map = mutableMapOf<Char, Char>()
            //找到1
            val cf = digs.find { it.length == 2 }!!
            val cfCount = digs.count { it.contains(cf[0]) }
            if (cfCount == 8) {
                map['c'] = cf[0]
                map['f'] = cf[1]
            } else {
                map['c'] = cf[1]
                map['f'] = cf[0]
            }
            //找到7
            val acf = digs.find { it.length == 3 }!!
            map['a'] = acf.find { !map.containsValue(it) }!!
            //找到4
            val bcdf = digs.find { it.length == 4 }!!
            val bd = bcdf.filter { !map.containsValue(it) }
            val bdCount = digs.count { it.contains(bd[0]) }
            if (bdCount == 6) {
                map['b'] = bd[0]
                map['d'] = bd[1]
            } else {
                map['b'] = bd[1]
                map['d'] = bd[0]
            }
            //找到9
            val nine = digs.find { it.length == 6 && it.contains(map['d']!!) && it.contains(map['c']!!) }!!
            map['e'] = ('a'..'g').find { !nine.contains(it) }!!
            map['g'] = ('a'..'g').find { !map.containsValue(it) }!!
            val finalMap = map.map { Pair(it.value, it.key) }.toMap()
            val num = list.takeLast(4).map { l -> l.map { finalMap[it]!! }.sorted() }.mapIndexed { i, it ->
                (keyMap[it.joinToString("")]!!).toInt() * 10.0.pow(
                    3 - i
                ).toLong()
            }.sum()
            return@map num
        }
        return results.sum()
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    val input = readInput("Day08")
    check(part1(testInput) == 26)
    println(part1(input))

    check(part2(testInput) == 61229L)
    println(part2(input))

}
