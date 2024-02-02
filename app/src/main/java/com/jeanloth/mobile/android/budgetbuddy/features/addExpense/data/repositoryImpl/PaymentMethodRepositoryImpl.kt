package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.PaymentMethodDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.PaymentMethodMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.PaymentMethod
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.PaymentMethodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PaymentMethodRepositoryImpl(
    private val paymentMethodDao: PaymentMethodDao,
    private val mapper: PaymentMethodMapper
) : PaymentMethodRepository {

    override suspend fun savePaymentMethods(payments: List<PaymentMethod>) {
        val entities = payments.map { mapper.to(it) }
        paymentMethodDao.upsertAll(entities)
    }

    override suspend fun getAllPaymentMethods(): Flow<List<PaymentMethod>> {
        return paymentMethodDao.getAll().map { flow -> flow.map { mapper.from(it) } }
    }

    override suspend fun isEmpty(): Flow<Boolean> {
        return paymentMethodDao.isEmpty()
    }
}