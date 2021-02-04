package com.example.kotlinbasics

class DebitCard(override val id: Int,
                override val name: String,
                override var balance: Double, override val type: Int,
) : CardInterface {

    init {
        println()
        println("Debit card created for $name")
    }

    override fun pay(amount: Int): Boolean {
        if (balance > 0) {
            return if (amount <= balance) {
                balance -= amount
                println()
                println("Payment done!")
                println("Payment Information:")
                println()
                println("Account Name: $name")
                println("Paid: $amount")
                println("Balance: $balance")
                println()
                println()
                true
            } else {
                println("Payment not done!")
                println("Insufficient funds!")
                false
            }
        }
        println("Payment not done!")
        println("Balance is 0!")
        return false
    }

    override fun withdraw(amount: Int): Boolean {
        if (balance > 0) {
            return if (amount <= balance) {
                balance -= amount
                println()
                println("Withdrawal done!")
                println("Withdrawal Information:")
                println()
                println("Account Name: $name")
                println("Withdrawn: $amount")
                println("Balance: $balance")
                println()
                println()
                true
            } else {
                println("Withdrawal not done!")
                println("Insufficient funds!")
                false
            }
        }
        return false
    }

    override fun deposit(amount: Int) {
        balance += amount
        println()
        println("Deposit done!")
        println("Account Name: $name")
        println("Added: $amount")
        println("Balance: $balance")
        println()
    }

}