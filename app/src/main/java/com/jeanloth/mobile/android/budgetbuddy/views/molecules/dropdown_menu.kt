package com.jeanloth.mobile.android.budgetbuddy.views.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Redeem
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanloth.mobile.android.budgetbuddy.theme.LightBlue
import com.jeanloth.mobile.android.budgetbuddy.theme.LightGreen1

val paymentMethods = listOf(
    MenuItem(Icons.Default.CreditCard, "Credit Card"),
    MenuItem(Icons.Default.Money, "Cash"),
    MenuItem(Icons.Default.Savings, "Savings"),
)

val paymentMenuColor = LightGreen1
val categoryMenuColor = LightBlue

val categories = listOf(
    MenuItem(Icons.Rounded.ShoppingBag, "Clothes"),
    MenuItem(Icons.Rounded.Redeem, "Gift"),
    MenuItem(Icons.Rounded.DirectionsCar, "Car"),
    MenuItem(Icons.Rounded.Public, "Online"),
)

class MenuItem(var icon: ImageVector, var label: String)

@Composable
fun PaymentMenu(modifier: Modifier = Modifier) {
    CustomDropDown(modifier = modifier, paymentMethods, backgroundColor = paymentMenuColor)
}

@Composable
fun CategoryMenu(modifier: Modifier = Modifier) {
    CustomDropDown(modifier = modifier, categories, backgroundColor = categoryMenuColor)
}

@Composable
fun CustomDropDown(modifier: Modifier = Modifier, items: List<MenuItem>, backgroundColor: Color = LightGreen1) {
    var isPaymentMethodMenuExpanded by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    var selectedItem by remember { mutableStateOf(items.first()) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(backgroundColor)
            .padding(horizontal = 4.dp)
            .clickable { isExpanded = !isExpanded }
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = selectedItem.icon,
                contentDescription = "More"
            )

            Text(selectedItem.label, textAlign = TextAlign.Center)

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedItem = item
                    isExpanded = false
                }) {
                    DropDownItem(icon = item.icon, label = item.label)
                }
            }
        }
    }
}

@Composable
fun DropDownItem(
    icon: ImageVector = Icons.Rounded.ShoppingCart,
    label: String = "Shopping",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(icon, contentDescription = "")
        Text(label)
    }
}

@Preview
@Composable
fun DropDownPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        PaymentMenu(Modifier.weight(1f))
        CategoryMenu(Modifier.weight(1f))
    }
}
