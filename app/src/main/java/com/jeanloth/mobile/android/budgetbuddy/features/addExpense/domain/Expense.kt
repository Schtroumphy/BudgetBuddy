package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain

import java.time.LocalDate

class Expense(
    val uid: Int = 0,
    val amount: Double,
    val date: LocalDate = LocalDate.now(),
)