package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.mappers

import com.jeanloth.mobile.android.budgetbuddy.core.Mapper
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities.PaymentMethodEntity
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.domain.models.PaymentMethod

class PaymentMethodMapper : Mapper<PaymentMethodEntity, PaymentMethod> {
    override fun from(entity: PaymentMethodEntity): PaymentMethod {
        return PaymentMethod(
            uid = entity.uid,
            icon = entity.icon,
            label = entity.label,
        )
    }

    override fun to(pojo: PaymentMethod): PaymentMethodEntity {
        return PaymentMethodEntity(
            uid = pojo.uid,
            icon = pojo.icon,
            label = pojo.label,
        )
    }
}