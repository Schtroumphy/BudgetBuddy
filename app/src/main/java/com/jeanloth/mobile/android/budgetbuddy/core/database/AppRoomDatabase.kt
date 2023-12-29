package com.jeanloth.mobile.android.budgetbuddy.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseEntity

@Database(
    entities = [ExpenseEntity::class],
    version = 1
)
@TypeConverters(DateTypeConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "budget-buddy-db"
    }

    // Define all DAOs like this
    abstract fun expenseDao(): ExpenseDao
}