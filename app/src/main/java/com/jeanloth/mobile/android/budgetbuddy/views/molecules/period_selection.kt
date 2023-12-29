package com.jeanloth.mobile.android.budgetbuddy.views.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanloth.mobile.android.budgetbuddy.views.atoms.RoundedArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment

@Composable
fun PeriodSelection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedArrow(isPrevious = true)
        Text(
            "Cette semaine",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.labelMedium
        )
        RoundedArrow(isPrevious = false)
    }
}

@Preview
@Composable
fun PeriodSelectionPreview() {
    PeriodSelection()
}