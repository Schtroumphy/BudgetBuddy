package com.jeanloth.mobile.android.budgetbuddy.features.splash.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Redeem
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.ExpenseCategory
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.PaymentMethod
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseCategoryRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.PaymentMethodRepository
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.MenuItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


val _paymentMethods = listOf(
    MenuItem(1, Icons.Default.CreditCard, "Credit Card"),
    MenuItem(2, Icons.Default.Money, "Cash"),
    MenuItem(3, Icons.Default.Savings, "Savings"),
)

val _categories = listOf(
    MenuItem(1, Icons.Rounded.ShoppingBag, "Clothes"),
    MenuItem(2, Icons.Rounded.Redeem, "Gift"),
    MenuItem(3, Icons.Rounded.DirectionsCar, "Car"),
    MenuItem(4, Icons.Rounded.Public, "Online"),
)

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
                        categories = _categories.map {
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
                        _paymentMethods.map {
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