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
                    expenseRepo.saveExpense(
                        Expense(amount = _state.value.amount)
                    )
                }
            }

            is DashboardEvent.SetExpenseAmount -> {
               _state.update {
                   it.copy(
                       amount = event.amount.toDouble()
                   )
               }
                viewModelScope.launch {
                    expenseRepo.saveExpense(
                        Expense(amount = _state.value.amount)
                    )
                }
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