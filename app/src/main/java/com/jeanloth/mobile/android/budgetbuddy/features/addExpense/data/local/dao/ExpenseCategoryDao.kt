package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseCategoryDao {

    @Query("SELECT * FROM expenseCategory")
    fun getAll(): Flow<List<ExpenseCategoryEntity>>

    @Query("SELECT * FROM expenseCategory WHERE uid IN (:expenseCategoryIds)")
    fun loadAllByIds(expenseCategoryIds: IntArray): Flow<List<ExpenseCategoryEntity>>

    @Query("SELECT (SELECT COUNT(*) FROM expenseCategory) = 0")
    fun isEmpty(): Flow<Boolean>

    @Upsert
    suspend fun upsert(expenseCategory: ExpenseCategoryEntity)

    @Upsert
    suspend fun upsertAll(categories: List<ExpenseCategoryEntity>)

    @Delete
    suspend fun delete(expenseCategory: ExpenseCategoryEntity)

}