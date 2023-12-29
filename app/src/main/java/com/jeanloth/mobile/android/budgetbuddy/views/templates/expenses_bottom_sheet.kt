package com.jeanloth.mobile.android.budgetbuddy.views.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EuroSymbol
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanloth.mobile.android.budgetbuddy.ui.theme.Blue2
import com.jeanloth.mobile.android.budgetbuddy.ui.theme.Gray1
import com.jeanloth.mobile.android.budgetbuddy.ui.theme.spaceMedium
import com.jeanloth.mobile.android.budgetbuddy.ui.theme.spaceSmall
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.CategoryMenu
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.PaymentMenu
import androidx.compose.material3.TextField as TextField1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesBottomSheet(onInputValidated: ((String) -> Unit)?) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    Column(Modifier.padding(horizontal = 16.dp, vertical = spaceMedium)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PaymentMenu(Modifier.weight(1f))
            CategoryMenu(Modifier.weight(1f))
        }
        Text(
            "Dépenses",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spaceSmall),
            style = MaterialTheme.typography.titleSmall
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExpendedTextField(modifier = Modifier
                .weight(4f)
                .padding(spaceMedium),
                textFieldValue = textFieldValue,
                onTextFieldValueChange = { newValue ->
                    textFieldValue = newValue
                })

            IconButton(modifier = Modifier.weight(1f),
                enabled = textFieldValue.text.isNotEmpty(),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Blue2, contentColor = Color.White,
                ),
                onClick = {
                    onInputValidated?.let { it(textFieldValue.text) }
                },
                content = {
                    Icon(Icons.Rounded.Check, contentDescription = null)
                })
        }

        Spacer(modifier = Modifier.height(spaceMedium))
    }
}

@Preview
@Composable
fun ExpensesBottomSheetPreview() {
    ExpensesBottomSheet(onInputValidated = null)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ExpendedTextFieldPreview() {
    ExpendedTextField(textFieldValue = TextFieldValue())
}

@ExperimentalMaterial3Api
@Composable
fun ExpendedTextField(
    modifier: Modifier = Modifier,
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: ((TextFieldValue) -> Unit)? = null,
) {

    TextField1(
        value = textFieldValue,
        onValueChange = { onTextFieldValueChange?.let { function -> function(it) } },
        placeholder = { Text(text = "255,00") },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Number,
        ),
        textStyle = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Icon(Icons.Filled.EuroSymbol, contentDescription = "", tint = Gray1)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}