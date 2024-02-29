package com.jeanloth.mobile.android.budgetbuddy.features.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import categories
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.ExpenseCategory
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.PaymentMethod
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseCategoryRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.PaymentMethodRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import methods

class SplashVM(
    private val paymentMethodRepository: PaymentMethodRepository,
    private val expenseCategoryRepository: ExpenseCategoryRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            saveDefaultData()
            delay(1000)
            _isLoading.value = false
        }
    }

    private fun saveDefaultData() {
        saveDefaultCategoriesIfNotPopulated()
        saveDefaultPaymentMethodsIfNotPopulated()
    }

    private fun saveDefaultCategoriesIfNotPopulated() {
        viewModelScope.launch {
            expenseCategoryRepository.isEmpty().collect { isEmpty ->
                if (isEmpty)
                    expenseCategoryRepository.saveExpenseCategories(
                        categories = categories.map {
                            ExpenseCategory(
                                icon = it.icon,
                                label = it.label
                            )
                        }
                    )
            }
        }
    }

    private fun saveDefaultPaymentMethodsIfNotPopulated() {
        viewModelScope.launch {
            paymentMethodRepository.isEmpty().collect { isEmpty ->
                if (isEmpty)
                    paymentMethodRepository.savePaymentMethods(
                        methods.map {
                            PaymentMethod(
                                icon = it.icon,
                                label = it.label
                            )
                        }
                    )
            }
        }
    }
}