package com.jeanloth.mobile.android.budgetbuddy.views.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanloth.mobile.android.budgetbuddy.R
import com.jeanloth.mobile.android.budgetbuddy.theme.Blue2

@Composable
fun RoundedButton(
    backgroundColor: Color = Blue2,
    label: String = "Budget"
) {
    Button(
        modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = Color.White
        ),
        onClick = {},
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.budget),
                contentDescription = "",
                modifier = Modifier.size(40.dp),
            )
            Text(
                text = label,
                fontSize = 17.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoundedButtonPreview() {
    RoundedButton()
}