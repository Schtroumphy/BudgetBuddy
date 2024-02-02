package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.PaymentMethodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentMethodDao {

    @Query("SELECT * FROM paymentMethod")
    fun getAll(): Flow<List<PaymentMethodEntity>>

    @Query("SELECT * FROM paymentMethod WHERE uid IN (:expenseIds)")
    fun loadAllByIds(expenseIds: IntArray): Flow<List<PaymentMethodEntity>>

    @Query("SELECT (SELECT COUNT(*) FROM paymentMethod) = 0")
    fun isEmpty(): Flow<Boolean>

    @Upsert
    suspend fun upsert(paymentMethod: PaymentMethodEntity)

    @Upsert
    suspend fun upsertAll(paymentMethods: List<PaymentMethodEntity>)

    @Delete
    suspend fun delete(paymentMethod: PaymentMethodEntity)

}