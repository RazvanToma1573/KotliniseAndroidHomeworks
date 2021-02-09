package com.example.kotlinbasics

data class HeartRateEntry(val date: Long, val value: Int)
fun populateData(vararg dataPair: Pair<Long, Int>): List<HeartRateEntry> =
        dataPair.map { HeartRateEntry(it.first, it.second) }
val data = populateData(
        1612310400L to 76,
        1612310400L to 89,
        1612310400L to 44,
        1612224000L to 47,
        1612224000L to 115,
        1612224000L to 76,
        1612224000L to 87,
        1612137600L to 90,
        1612137600L to 167)

fun main() {
    println(data.minOf { it.value })
    println(data.map { it.value }.average())
    data.filter { it.value > 100 }.forEach { print("${it.value} ") }
    println()
    data.groupBy { it.date }.forEach { group -> var value = ""; group.value.forEach { value += "${it.value} " }; println("${group.key} $value") }
    data.groupBy { it.date }.forEach { println("${it.key} ${it.value.maxOf { a -> a.value }}") }
}