package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseEntity

data class DashboardState (
    val expenses : List<ExpenseEntity> = emptyList(),
    val isAddingExpense : Boolean = false,
    val amount : Double = 0.0,
    // Filter activated
)