package com.jeanloth.mobile.android.budgetbuddy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation.DashboardEvent
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation.DashboardState
import com.jeanloth.mobile.android.budgetbuddy.features.addExpense.presentation.DashboardVM
import com.jeanloth.mobile.android.budgetbuddy.features.splash.presentation.SplashVM
import com.jeanloth.mobile.android.budgetbuddy.theme.BudgetBuddyTheme
import com.jeanloth.mobile.android.budgetbuddy.views.molecules.Header
import com.jeanloth.mobile.android.budgetbuddy.views.templates.ExpensesBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val splashVM: SplashVM by viewModel()
    private val dashboardVM: DashboardVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { splashVM.isLoading.value }

        setContent {
            val dashboardState by dashboardVM.state.collectAsState()

            BudgetBuddyTheme {
                DashboardScreen(
                    state = dashboardState,
                    onEvent = dashboardVM::onEvent
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit
) {
    val mContext = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            Header()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(DashboardEvent.ShowBottomSheet)
            }) {
                Text("+")
            }
        }
    ) {
        if (state.isAddingExpense) {
            ModalBottomSheet(
                onDismissRequest = {
                    onEvent(DashboardEvent.HideBottomSheet)
                },
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                tonalElevation = 16.dp,
                containerColor = Color.White
            ) {
                ExpensesBottomSheet(
                    paymentMethods = state.paymentMethods,
                    categories = state.categories,
                    onInputValidated = { amount, paymentMethodId, categoryId ->
                    onEvent(DashboardEvent.SetExpense(amount, paymentMethodId, categoryId))
                    onEvent(DashboardEvent.HideBottomSheet)

                    Toast.makeText(mContext, "Expense saved !", Toast.LENGTH_LONG).show()
                })
            }
        } else {
            keyboardController?.hide()
        }
    }
}