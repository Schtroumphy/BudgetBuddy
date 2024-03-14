package com.jeanloth.mobile.android.budgetbuddy.views.atoms

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CircularIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0.75f,
) {
    CircularProgressIndicator(
        progress = progress,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CircularIndicatorPreview(){
    CircularIndicator(progress = 0.75f)
}