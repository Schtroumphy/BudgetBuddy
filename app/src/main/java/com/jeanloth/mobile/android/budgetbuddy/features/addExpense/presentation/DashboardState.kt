package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.Expense

data class DashboardState (
    val expenses : List<Expense> = emptyList(),
    val isAddingExpense : Boolean = false,
    val currentExpense : Expense? = null,
)