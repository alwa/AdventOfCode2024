import java.io.File

object Day1 {

    fun day1Part1(filename: String) : Int  {
        var maxSum = 0
        var tempSum = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.isNotEmpty()) {
                tempSum += it.toInt()
            } else {
                if (tempSum > maxSum) {
                    maxSum = tempSum
                }
                tempSum = 0
            }
        }
        if (tempSum > maxSum) {
            maxSum = tempSum
        }
        return maxSum
    }

    fun day1Part2(filename: String) : Int {
        val maxSum = mutableListOf(0, 0, 0)
        var tempSum = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.isNotEmpty()) {
                tempSum += it.toInt()
            } else {
                if (tempSum > maxSum.min()) {
                    maxSum[maxSum.indexOf(maxSum.min())] = tempSum
                }
                tempSum = 0
            }
        }
        if (tempSum > maxSum.min()) {
            maxSum[maxSum.indexOf(maxSum.min())] = tempSum
        }
        var result = 0
        maxSum.forEach { result += it }
        return result
    }

}
