package com.jeanloth.mobile.android.budgetbuddy.views.atoms

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CircularIndicator(
    progress: Float = 0.75f,
    modifier: Modifier
) {
    CircularProgressIndicator(
        progress = progress,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CircularIndicatorPreview(){
    CircularProgressIndicator(progress = 0.75f)
}