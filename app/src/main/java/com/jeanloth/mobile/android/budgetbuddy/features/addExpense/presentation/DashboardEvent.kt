package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseEntity
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.ExpenseCategory

sealed interface DashboardEvent {
    data object SaveExpense : DashboardEvent
    data object ShowBottomSheet : DashboardEvent
    data object HideBottomSheet : DashboardEvent
    data class SetExpense(val amount: String, val paymentMethodId: Int, val categoryId: Int) : DashboardEvent
    data class SortExpenses(val expenseCategory: ExpenseCategory)
    data class Delete(val expense: ExpenseEntity)
}
