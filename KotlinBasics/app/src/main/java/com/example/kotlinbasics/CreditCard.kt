package com.example.kotlinbasics

class CreditCard(
    private val bonusPercentage: Int,
    private val limit: Int,
    override val id: Int,
    override val name: String,
    override var balance: Double, override val type: Int,
) : CardInterface {

    init {
        println()
        println("Credit card created for $name")
        println("The limit is $limit")
    }

    override fun pay(amount: Int): Boolean {
        if (amount <= balance + limit) {
            balance -= amount
            balance += (bonusPercentage/100.0) * amount
            println()
            println("Payment done!")
            println("Payment Information:")
            println()
            println("Account Name: $name")
            println("Bonus: $bonusPercentage%")
            println("Limit: $limit")
            println("Paid: $amount")
            println("Balance: $balance")
            println()
            println()
            return true
        }
        return false
    }

    override fun withdraw(amount: Int): Boolean {
        if (amount <= balance + limit) {
            balance -= amount
            println()
            println("Withdrawal done!")
            println("Withdrawal Information:")
            println()
            println("Account Name: $name")
            println("Bonus: $bonusPercentage%")
            println("Limit: $limit")
            println("Withdrawn: $amount")
            println("Balance: $balance")
            println()
            println()
            return true
        }
        return false
    }

    override fun deposit(amount: Int) {
        balance += amount
        println()
        println("Deposit done!")
        println("Account Name: $name")
        println("Bonus: $bonusPercentage%")
        println("Limit: $limit")
        println("Added: $amount")
        println("Balance: $balance")
        println()
    }

}