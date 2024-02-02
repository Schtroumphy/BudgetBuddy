package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.ExpenseCategory
import kotlinx.coroutines.flow.Flow

interface ExpenseCategoryRepository {

    suspend fun saveExpenseCategories(categories : List<ExpenseCategory>)

    suspend fun getAllCategories() : Flow<List<ExpenseCategory>>

    suspend fun isEmpty() : Flow<Boolean>
}