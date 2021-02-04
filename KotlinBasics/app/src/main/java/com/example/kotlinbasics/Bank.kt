package com.example.kotlinbasics

class Bank (private val bonusPercentage: Int) {

    init {
        println("Bank created with a bonus percentage (for credit cards) of $bonusPercentage%")
    }

    private val accounts : MutableList<CardInterface> = mutableListOf()
    private var id = 0

    fun createAccount(type: Int, limitType: Int, name: String) {
        // possible limit types:
        // 1 - small (10)
        // 2 - medium (100)
        // 3 - big (1000)
        val limit = when (limitType) {
            1 -> 10
            2 -> 100
            3 -> 1000
            else -> 0
        }
        // possible card types:
        // 1 - credit card
        // 2 - debit card
        when (type) {
            1 -> accounts.add(CreditCard(bonusPercentage, limit, id++, name, 0.0, type))
            2 -> accounts.add(DebitCard(id++, name, 0.0, type))
            else -> println("You can either create a credit card (1) or a debit card (2)")
        }
    }

    fun getAccountByNameAndType(name: String, type: Int): CardInterface {
        return accounts.first { a -> a.name == name && a.type == type }
    }

}