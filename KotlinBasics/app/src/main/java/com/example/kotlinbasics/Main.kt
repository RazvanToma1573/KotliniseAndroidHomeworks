package com.example.kotlinbasics

import kotlin.random.Random

fun main() {
    // Exercise 1
    one()
    // Exercise 2
//    two()
    // Exercise 3
//    three()
}
// Exercise 1
fun one() {
    inputProcess()
    continuousProcess()
}

// get systolic and diastolic blood pressure from user and check them
private fun inputProcess() {
    println("Process values from user:")
    print("systolic pressure:")

    val systolicInput = readLine()
    println("You entered: $systolicInput")

    print("diastolic pressure:")

    val diastolicInput = readLine()
    println("You entered: $diastolicInput")

    if (systolicInput != null && diastolicInput != null) {
        val resultInput = check(systolicInput.trim().toInt(), diastolicInput.trim().toInt())
        println("Your pressure is: $resultInput")
    } else {
        println("There was an error with the data you entered...")
    }
}

// or generate continuously values for systolic and diastolic blood pressure and check them
private fun continuousProcess() {
    println("Generate values and process them continuously...")
    var stop = true
    while (stop) {
        val systolic = Random.nextInt(from = 0, until = 201)
        val diastolic = Random.nextInt(from = 0, until = 201)
        val result = check(systolic, diastolic)
        println("systolic: $systolic | diastolic: $diastolic | pressure: $result")

        print("Do you wish to stop? (y/n)")
        val input = readLine()
        if (input != null) {
            stop = input.trim() != "y"
        }
    }
}

fun check(systolic: Int, diastolic: Int): String {
    return when {
        systolic < 120 && diastolic < 80 -> "normal"
        systolic in 120..129 && diastolic < 80 -> "elevated"
        systolic in 130..139 || diastolic in 80..89 -> "high blood pressure (HYPERTENSION STAGE 1)"
        systolic in 140..180 || diastolic in 90..120 -> "high blood pressure (HYPERTENSION STAGE 2)"
        systolic > 180 || diastolic > 120 -> "hypertensive crisis (CONSULT YOUR DOCTOR IMMEDIATELY)"
        else -> "error"
    }
}

// ===============================================================

// Exercise 2
fun two() {
    // create a bank with a bonus percentage for credit cards of 5
    val bank: Bank = Bank(5)

    // debit card
    debit(bank)

    println("=========================================================================================")

    // credit card
    credit(bank)
}

private fun debit(bank: Bank) {
    bank.createAccount(2, 1, "Razvan Toma")
    val debitCard = bank.getAccountByNameAndType("Razvan Toma", 2)

    // balance is currently 0 so a payment/withdrawal should fail
    var resultPayment = debitCard.pay(10)
    var resultWithdrawal = debitCard.pay(20)

    if (!resultPayment && !resultWithdrawal) {
        println("Test passed!!! (pay or withdraw from debit card with 0 balance")
    } else {
        println("Test failed!!! (pay or withdraw from debit card with 0 balance")
    }

    // deposit some money
    debitCard.deposit(50)

    // pay some money

    resultPayment = debitCard.pay(10)

    if (resultPayment) {
        println("Test passed!!! (pay from debit card with money in the account)")
    } else {
        println("Test failed!!! (pay from debit card with money in the account)")
    }

    // withdraw some money

    resultWithdrawal = debitCard.withdraw(30)

    if (resultWithdrawal) {
        println("Test passed!!! (withdraw from debit card with money in the account)")
    } else {
        println("Test failed!!! (withdraw from debit card with money in the account)")
    }

    // remaining balance should be 10
    if (debitCard.balance == 10.0) {
        println("Test passed!!!! (balance after operations is correct)")
    } else {
        println("Test failed!!! (balance after operations is correct)")
    }
}

private fun credit(bank: Bank) {
    bank.createAccount(1,3,"Razvan Toma")
    val creditCard = bank.getAccountByNameAndType("Razvan Toma", 1)

    // balance is 0 but limit is 1000 so a payment/withdrawal should succeed
    // furthermore there will be a bonus added to payment equal to the percentage of 5 (set in the bank)

    // payment
    val resultPayment = creditCard.pay(10)

    if (resultPayment) {
        println("Test passed!!! (pay with balance 0 on credit card)")
        // balance should be -9.5
        if (creditCard.balance == -9.5) {
            println("Test passed!!! (balance is -9.5)")
        } else {
            println("Test failed!!! (balance is -9.5)")
        }
    } else {
        println("Test failed!!! (pay with balance 0 on credit card)")
    }

    // withdrawal

    val resultWithdrawal = creditCard.withdraw(20)

    if (resultWithdrawal) {
        println("Test passed!!! (withdraw with balance -9.5 on credit card)")
        // balance should be -29.5
        if (creditCard.balance == -29.5) {
            println("Test passed!!! (balance is -29.5)")
        } else {
            println("Test failed!!! (balance is -29.5)")
        }
    } else {
        println("Test failed!!! (pay with balance 0 on credit card)")
    }

    // deposit

    creditCard.deposit(30)
    if (creditCard.balance == 0.5) {
        println("Test passed!!! (balance is 0.5)")
    } else {
        println("Test failed!!! (balance is 0.5)")
    }

}

// ===============================================================

// Exercise 3
fun three() {
    print("Enter a number:")

    val number = readLine()
    if (number != null) {
        val result = factorial(number.trim().toInt())
        println("Factorial for $number is: $result")
    }
}

// receive a number n and return a string containing the factorial of that number n
fun factorial(number: Int) : String {
    var result = 1
    val sb = StringBuilder()
    sb.append(1)
    for (i in 2..number) {
        result *= i
        sb.append(" * $i")
    }
    sb.append(" = $result")
    return  sb.toString()
}