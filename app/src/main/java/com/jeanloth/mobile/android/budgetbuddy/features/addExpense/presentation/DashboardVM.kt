package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.Expense
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardVM(
    private val expenseRepo: ExpenseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state : StateFlow<DashboardState> = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.SaveExpense -> {
                viewModelScope.launch {
                    _state.value.currentExpense?.let { expenseRepo.saveExpense(it) }
                }
            }

            is DashboardEvent.SetExpense -> {
                val expense = Expense(
                    amount = event.amount.toDouble()/100,
                    paymentMethodId = event.paymentMethodId,
                    categoryId = event.categoryId
                )
               _state.update {
                   it.copy(
                       currentExpense = expense
                   )
               }
                onEvent(DashboardEvent.SaveExpense)
            }

            DashboardEvent.ShowBottomSheet -> {
                _state.update {
                    it.copy(
                        isAddingExpense = true
                    )
                }
            }

            DashboardEvent.HideBottomSheet -> {
                _state.update {
                    it.copy(
                        isAddingExpense = false
                    )
                }
            }
        }
    }
}