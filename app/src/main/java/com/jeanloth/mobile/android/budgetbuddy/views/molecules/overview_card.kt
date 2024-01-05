package com.jeanloth.mobile.android.budgetbuddy.views.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanloth.mobile.android.budgetbuddy.theme.BudgetBuddyTheme
import com.jeanloth.mobile.android.budgetbuddy.theme.Gray1

@Composable
fun OverviewCard() {
    Card(
        shape = RoundedCornerShape(40.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                "Vue d'ensemble",
                style = MaterialTheme.typography.titleMedium,
                color = Gray1
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(150.dp)
                    .width(150.dp)
                    .background(Color.Gray)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun OverviewCardPreview() {
    BudgetBuddyTheme {
        OverviewCard()
    }
}