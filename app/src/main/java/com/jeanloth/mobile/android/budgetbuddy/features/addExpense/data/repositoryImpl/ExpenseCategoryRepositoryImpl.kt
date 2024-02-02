package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl

import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseCategoryDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.ExpenseCategoryMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.ExpenseCategory
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseCategoryRepositoryImpl(
    private val expenseCategoryDao: ExpenseCategoryDao,
    private val mapper: ExpenseCategoryMapper
) : ExpenseCategoryRepository {

    override suspend fun saveExpenseCategories(categories: List<ExpenseCategory>) {
        val entities = categories.map { mapper.to(pojo = it) }
        expenseCategoryDao.upsertAll(entities)
    }

    override suspend fun getAllCategories(): Flow<List<ExpenseCategory>> {
        return expenseCategoryDao.getAll().map { flow -> flow.map { mapper.from(it) } }
    }

    override suspend fun isEmpty(): Flow<Boolean> {
        return expenseCategoryDao.isEmpty()
    }
}

