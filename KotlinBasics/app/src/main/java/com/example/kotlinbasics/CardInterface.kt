package com.example.kotlinbasics

interface CardInterface {
    val type: Int
    val id: Int
    val name: String
    var balance: Double

    // true - the operation was successful
    // false - the operation was not successful
    fun pay(amount: Int): Boolean
    fun withdraw(amount: Int): Boolean
    fun deposit(amount: Int)
}