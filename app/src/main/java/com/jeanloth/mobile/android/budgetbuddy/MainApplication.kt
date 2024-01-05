package com.jeanloth.mobile.android.budgetbuddy

import android.app.Application
import androidx.room.Room
import com.jeanloth.mobile.android.budgetbuddy.core.database.AppRoomDatabase
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.ExpenseMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl.ExpenseRepositoryImpl
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.ExpenseRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation.DashboardVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)

            modules(appModule, dataBaseModule, dataModule)
        }
    }
}

val appModule = module {
    viewModelOf(::DashboardVM)
}

val dataModule = module {
    single { ExpenseMapper() }
    single<ExpenseRepository> { ExpenseRepositoryImpl(get(), get()) }
}

fun provideDataBase(application: Application): AppRoomDatabase =
    Room.databaseBuilder(
        application,
        AppRoomDatabase::class.java,
        AppRoomDatabase.DATABASE_NAME
    ).
    fallbackToDestructiveMigration().build()

fun provideDao(postDataBase: AppRoomDatabase): ExpenseDao = postDataBase.expenseDao()


val dataBaseModule= module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}