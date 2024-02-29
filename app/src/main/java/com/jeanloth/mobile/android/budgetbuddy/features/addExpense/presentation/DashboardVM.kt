package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.Expense
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseCategoryRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.PaymentMethodRepository
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardVM(
    private val expenseRepo: ExpenseRepository,
    private val expenseCategoryRepo: ExpenseCategoryRepository,
    private val paymentMethodRepository: PaymentMethodRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state : StateFlow<DashboardState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            expenseCategoryRepo.getAllCategories().collectLatest { categories ->
                _state.update {
                    it.copy(
                        categories = categories.map { category ->
                            MenuItem(
                                category.uid,
                                category.icon,
                                category.label
                            )
                        }
                    )
                }
            }
        }

        viewModelScope.launch {
            paymentMethodRepository.getAllPaymentMethods().collectLatest { methods ->
                _state.update {
                    it.copy(
                        paymentMethods = methods.map { method ->
                            MenuItem(
                                method.uid,
                                method.icon,
                                method.label
                            )
                        }
                    )
                }
            }
        }

    }
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