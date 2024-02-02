package com.jeanloth.mobile.android.budgetbuddy.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseEntity
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseCategoryDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.PaymentMethodDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseCategoryEntity
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.PaymentMethodEntity

@Database(
    entities = [ExpenseEntity::class, ExpenseCategoryEntity::class, PaymentMethodEntity::class],
    version = 3
)
@TypeConverters(DateTypeConverter::class, ImageVectorConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "budget-buddy-db"
    }

    // Define all DAOs
    abstract fun expenseDao(): ExpenseDao
    abstract fun expenseCategoryDao(): ExpenseCategoryDao
    abstract fun paymentMethodDao(): PaymentMethodDao
}