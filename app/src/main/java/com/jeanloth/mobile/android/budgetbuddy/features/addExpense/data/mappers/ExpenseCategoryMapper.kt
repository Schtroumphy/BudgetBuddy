package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers

import com.jeanloth.mobile.android.budgetbuddy.core.Mapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.ExpenseCategoryEntity
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.ExpenseCategory

class ExpenseCategoryMapper : Mapper<ExpenseCategoryEntity, ExpenseCategory> {
    override fun from(entity: ExpenseCategoryEntity): ExpenseCategory {
        return ExpenseCategory(
            uid = entity.uid,
            icon = entity.icon,
            label = entity.label,
        )
    }

    override fun to(pojo: ExpenseCategory): ExpenseCategoryEntity {
        return ExpenseCategoryEntity(
            uid = pojo.uid,
            icon = pojo.icon,
            label = pojo.label,
        )
    }
}