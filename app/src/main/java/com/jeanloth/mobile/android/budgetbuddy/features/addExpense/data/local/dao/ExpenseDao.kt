package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense")
    fun getAll(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expense WHERE uid IN (:expenseIds)")
    fun loadAllByIds(expenseIds: IntArray): Flow<List<ExpenseEntity>>

    @Upsert
    suspend fun upsert(expense: ExpenseEntity)

    @Delete
    suspend fun delete(expense: ExpenseEntity)

}