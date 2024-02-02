package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    suspend fun saveExpense(expense : Expense)
    suspend fun getAllExpenses() : Flow<List<Expense>>

}