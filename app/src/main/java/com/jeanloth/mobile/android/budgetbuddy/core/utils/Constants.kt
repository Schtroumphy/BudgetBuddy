import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Redeem
import androidx.compose.material.icons.rounded.ShoppingBag
import com.jeanloth.mobile.android.budgetbuddy.theme.LightBlue
import com.jeanloth.mobile.android.budgetbuddy.theme.LightGreen1
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.MenuItem


val methods = listOf(
    MenuItem(1, Icons.Default.CreditCard, "Credit Card"),
    MenuItem(2, Icons.Default.Money, "Cash"),
    MenuItem(3, Icons.Default.Savings, "Savings"),
)

val categories = listOf(
    MenuItem(1, Icons.Rounded.ShoppingBag, "Clothes"),
    MenuItem(2, Icons.Rounded.Redeem, "Gift"),
    MenuItem(3, Icons.Rounded.DirectionsCar, "Car"),
    MenuItem(4, Icons.Rounded.Public, "Online"),
)

val paymentMenuColor = LightGreen1
val categoryMenuColor = LightBlue