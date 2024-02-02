package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.PaymentMethod
import kotlinx.coroutines.flow.Flow

interface PaymentMethodRepository {

    suspend fun savePaymentMethods(payments : List<PaymentMethod>)

    suspend fun getAllPaymentMethods() : Flow<List<PaymentMethod>>

    suspend fun isEmpty() : Flow<Boolean>

}