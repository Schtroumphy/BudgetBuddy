package com.jeanloth.mobile.android.budgetbuddy.features.addExpense.data.local.entities

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("paymentMethod")
class PaymentMethodEntity (
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val icon: ImageVector,
    val label: String,
)