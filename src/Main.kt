// https://www.hackerrank.com/challenges/weighted-uniform-string/problem

fun weightedUniformStrings(s: String, queries: Array<Int>): Array<String> {
    val scoreSet = mutableSetOf<Int>()
    val answers = Array(size = queries.size, init = { "" })

    // Calculate all the possible scores
    for (chr in s.toCharArray().distinct()) {
        val charScore = chr - 'a' + 1
        val searchRegex = "$chr{2,}".toRegex()

        // Search the longest contiguous character
        val maxLength = searchRegex.findAll(s).maxBy { it.value.length }?.value?.length ?: 1

        for (i in 1..maxLength) {
            scoreSet.add(charScore * i)
        }
    }

    // Now check all the queries
    for ((idx, query) in queries.withIndex()) {
        if (scoreSet.contains(query)) {
            answers[idx] = "Yes"
        } else {
            answers[idx] = "No"
        }
    }

    return answers
}

fun main() {
    println(weightedUniformStrings("abccddde", intArrayOf(1, 3, 12, 5, 9, 10).toTypedArray()).toList())
    println(weightedUniformStrings("aaabbbbcccddd", intArrayOf(9, 7, 8, 12, 5).toTypedArray()).toList())
}