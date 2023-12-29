package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseEntity
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.ExpenseCategory

sealed interface DashboardEvent {
    object SaveExpense : DashboardEvent
    object ShowBottomSheet : DashboardEvent
    object HideBottomSheet : DashboardEvent
    data class SetExpenseAmount(val amount: String) : DashboardEvent
    data class SortExpenses(val expenseCategory: ExpenseCategory)
    data class Delete(val expense: ExpenseEntity)
}
