package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers

import com.jeanloth.mobile.android.budgetbuddy.core.Mapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseEntity
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.Expense
import java.time.LocalDate


class ExpenseMapper : Mapper<ExpenseEntity, Expense> {
    override fun from(entity: ExpenseEntity): Expense {
        return Expense(
            uid = entity.uid,
            amount = entity.amount,
            paymentMethodId = entity.paymentMethodId,
            categoryId = entity.categoryId,
            date = entity.date ?: LocalDate.now(),
        )
    }

    override fun to(pojo: Expense): ExpenseEntity {
        return ExpenseEntity(
            uid = pojo.uid,
            amount = pojo.amount,
            paymentMethodId = pojo.paymentMethodId,
            categoryId = pojo.categoryId,
            date = pojo.date
        )
    }
}