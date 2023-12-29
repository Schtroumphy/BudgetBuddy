package com.jeanloth.mobile.android.budgetbuddy.views.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanloth.mobile.android.budgetbuddy.ui.theme.BudgetBuddyTheme
import com.jeanloth.mobile.android.budgetbuddy.ui.theme.DarkBlue
import com.jeanloth.mobile.android.budgetbuddy.ui.theme.LightBlue

@Composable
fun RoundedArrow(isPrevious: Boolean = false) {
    Box(modifier = Modifier
        .clip(CircleShape)
        .background(LightBlue)) {
        Icon(
            if (isPrevious) Icons.Rounded.ArrowBack else Icons.Rounded.ArrowForward,
            contentDescription = "",
            tint = DarkBlue,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoundedArrowPreview() {
    BudgetBuddyTheme {
        RoundedArrow(isPrevious = false)
    }
}