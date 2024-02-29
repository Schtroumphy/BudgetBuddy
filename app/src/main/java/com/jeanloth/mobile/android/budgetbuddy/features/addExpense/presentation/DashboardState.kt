package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.Expense
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.MenuItem

data class DashboardState (
    val expenses : List<Expense> = emptyList(),
    val isAddingExpense : Boolean = false,
    val currentExpense : Expense? = null,
    val paymentMethods : List<MenuItem> = emptyList(),
    val categories : List<MenuItem> = emptyList(),
)