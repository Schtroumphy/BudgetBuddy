package com.jeanloth.mobile.android.budgetbuddy

import android.app.Application
import androidx.room.Room
import com.jeanloth.mobile.android.budgetbuddy.core.database.AppRoomDatabase
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseCategoryDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.ExpenseDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.dao.PaymentMethodDao
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.ExpenseCategoryMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.ExpenseMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers.PaymentMethodMapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl.ExpenseCategoryRepositoryImpl
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl.ExpenseRepositoryImpl
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.repositoryImpl.PaymentMethodRepositoryImpl
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseCategoryRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.ExpenseRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.repositories.PaymentMethodRepository
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation.DashboardVM
import com.jeanloth.mobile.android.budgetbuddy.features.splash.presentation.SplashVM
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
    viewModelOf(::SplashVM)
}

val dataModule = module {
    single { ExpenseMapper() }
    single { ExpenseCategoryMapper() }
    single { PaymentMethodMapper() }
    single<ExpenseRepository> { ExpenseRepositoryImpl(get(), get()) }
    single<ExpenseCategoryRepository> { ExpenseCategoryRepositoryImpl(get(), get()) }
    single<PaymentMethodRepository> { PaymentMethodRepositoryImpl(get(), get()) }
}

fun provideDataBase(application: Application): AppRoomDatabase =
    Room.databaseBuilder(
        application,
        AppRoomDatabase::class.java,
        AppRoomDatabase.DATABASE_NAME
    ).
    fallbackToDestructiveMigration().build()

fun provideExpenseDao(postDataBase: AppRoomDatabase): ExpenseDao = postDataBase.expenseDao()
fun provideExpenseCategoryDao(postDataBase: AppRoomDatabase): ExpenseCategoryDao = postDataBase.expenseCategoryDao()
fun providePaymentMethodDao(postDataBase: AppRoomDatabase): PaymentMethodDao = postDataBase.paymentMethodDao()


val dataBaseModule= module {
    single { provideDataBase(get()) }
    single { provideExpenseDao(get()) }
    single { provideExpenseCategoryDao(get()) }
    single { providePaymentMethodDao(get()) }
}