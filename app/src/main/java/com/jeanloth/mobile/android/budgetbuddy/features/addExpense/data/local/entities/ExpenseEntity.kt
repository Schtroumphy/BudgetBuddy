package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jeanloth.mobile.android.budgetbuddy.core.database.DateTypeConverter
import java.time.LocalDate

@Entity("expense")
@TypeConverters(DateTypeConverter::class)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val amount: Double,
    val paymentMethodId: Int,
    val categoryId: Int,
    val date: LocalDate?
)