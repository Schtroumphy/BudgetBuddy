package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.ExpenseMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.Expense
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao,
    private val mapper: ExpenseMapper
) : ExpenseRepository {

    override suspend fun saveExpense(expense: Expense) {
        val entity = mapper.to(expense)
        expenseDao.upsert(entity)
    }

    override suspend fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAll().map { flow -> flow.map { mapper.from(it) } }
    }
}