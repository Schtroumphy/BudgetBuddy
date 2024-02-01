package com.jeanloth.mobile.android.budgetbuddy.views.templates

import CurrencyAmountInputVisualTransformation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanloth.mobile.android.budgetbuddy.theme.Blue2
import com.jeanloth.mobile.android.budgetbuddy.theme.Gray1
import com.jeanloth.mobile.android.budgetbuddy.theme.spaceLarge
import com.jeanloth.mobile.android.budgetbuddy.theme.spaceMedium
import com.jeanloth.mobile.android.budgetbuddy.theme.spaceSmall
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.CategoryMenu
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.PaymentMenu
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.categories
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.paymentMethods
import androidx.compose.material3.TextField as TextField1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesBottomSheet(onInputValidated: ((String, Int, Int) -> Unit)?) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
    var paymentMethodSelected: Int by remember { mutableIntStateOf(paymentMethods.first().id ) }
    var categorySelected: Int by remember { mutableIntStateOf(categories.first().id) }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = spaceMedium),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PaymentMenu(Modifier.weight(1f), onItemSelected =  {
                paymentMethodSelected = it
            })
            CategoryMenu(Modifier.weight(1f), onItemSelected = {
                categorySelected = it
            })
        }
        Text(
            "Dépense",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spaceLarge),
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.Gray
            ),
        )
        ExpendedTextField(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(spaceMedium),
            textFieldValue = textFieldValue,
            onTextFieldValueChange = { newValue ->
                textFieldValue = newValue
            })

        Spacer(modifier = Modifier.height(spaceMedium))

        IconButton(modifier = Modifier
            .align(Alignment.End)
            .padding(spaceSmall),
            enabled = textFieldValue.text.isNotEmpty(),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Blue2, contentColor = Color.White,
            ),
            onClick = {
                onInputValidated?.let { it(textFieldValue.text, paymentMethodSelected, categorySelected) }
            },
            content = {
                Icon(Icons.Rounded.Check, contentDescription = null)
            })
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
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField1(
        value = textFieldValue,
        onValueChange = { if (it.text.length < 6) onTextFieldValueChange?.let { function -> function(it) } },
        placeholder = { Text(text = "") },
        modifier = modifier
            .focusRequester(focusRequester)
            .width((screenWidth / 1.8).dp),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.NumberPassword,
        ),
        visualTransformation = CurrencyAmountInputVisualTransformation(),
        textStyle = MaterialTheme.typography.titleLarge.copy(
            fontSize = 50.sp
        ),
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