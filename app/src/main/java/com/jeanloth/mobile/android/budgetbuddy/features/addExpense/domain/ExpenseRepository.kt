package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain

interface ExpenseRepository {

    suspend fun saveExpense(expense : Expense)

}