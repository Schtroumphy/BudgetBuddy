package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.ExpenseMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.Expense
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.ExpenseRepository

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao,
    private val mapper: ExpenseMapper
) : ExpenseRepository {

    override suspend fun saveExpense(expense : Expense) {
        val entity = mapper.to(expense)
        expenseDao.upsert(entity)
    }
}