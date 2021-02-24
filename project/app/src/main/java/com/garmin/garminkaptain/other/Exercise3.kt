package com.garmin.garminkaptain.other

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

data class Book(
    val name: String,
    val type: String,
)

val bookNameList: List<String> = listOf(
    "The Art of Game Design",
    "Pixel Logic",
    "Fundamentals of Graphics",
    "Morometii",
    "Nordic Gods",
    "The Illusion of Life",
    "The Animator's Survival Kit"
)

val bookTypeList: List<String> = listOf(
    "Adventure",
    "Mystery",
    "Fantasy",
    "Historical",
    "Teaching"
)

fun getBookList(): List<Book> {
    // get a "randomly" generated list of books
    val bookList: MutableList<Book> = mutableListOf()
    val numberOfBooks = Random.nextInt(1,6)
    println("Order has ${numberOfBooks+1} books:")
    for (i in 0..numberOfBooks) {
        val indexName = Random.nextInt(0, bookNameList.size)
        val indexType = Random.nextInt(0, bookTypeList.size)
        bookList.add(i, Book(bookNameList[indexName], bookTypeList[indexType]))
    }
    return bookList
}

fun order(): Flow<Book> = flow {
    val books = getBookList()
    books.forEach {
        val getBookTime = Random.nextLong(1, 7)
        delay(getBookTime * 1000)
        emit(it)
    }
}

fun main() {
    var o = 0
    while (true) {
        // order books
        GlobalScope.launch {
            val orderNumber = o
            println("Order $orderNumber start...")
            order().collect { println("Got book for order $orderNumber: ${it.name} ${it.type}") }
            delay(4000)
            println("Order $orderNumber done!")
        }
        // wait 10 seconds
        runBlocking {
            delay(10000)
        }

        o++
    }
}