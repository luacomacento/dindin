package dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.ui.LocalNavControllerProvider
import dev.luaoctaviano.dindin.core.ui.composable.DinButton
import dev.luaoctaviano.dindin.core.ui.composable.DinSwitch
import dev.luaoctaviano.dindin.core.ui.composable.DinTextField
import dev.luaoctaviano.dindin.core.ui.composable.DropDownSelector
import dev.luaoctaviano.dindin.core.ui.extension.CoreDrawables
import dev.luaoctaviano.dindin.core.ui.extension.CoreIcons
import dev.luaoctaviano.dindin.core.ui.extension.CoreStrings
import dev.luaoctaviano.dindin.core.ui.extension.StatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.extension.forceStatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme
import dev.luaoctaviano.dindin.feature.transactions.R
import dev.luaoctaviano.dindin.feature.transactions.extension.TransactionStrings
import dev.luaoctaviano.dindin.feature.transactions.extension.getBackgroundColor
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.DummyNewTransactionListener
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.NewTransactionListener
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.NewTransactionUiState
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.NewTransactionViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionScreen(
    viewModel: NewTransactionViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = LocalNavControllerProvider.current
    val hapticFeedback = LocalHapticFeedback.current
    val coroutineScope = rememberCoroutineScope()

    val accountSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAccountSheet by remember { mutableStateOf(false) }

    val categorySheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showCategorySheet by remember { mutableStateOf(false) }

    forceStatusBarIconColor(StatusBarIconColor.LIGHT)

    NewTransactionScreenContent(
        modifier = modifier,
        uiState = uiState,
        listener = object : NewTransactionListener {
            override fun onBackClick() {
                navController?.navigateUp()
            }

            override fun onChangeTransactionValue(newValue: String) {
                viewModel.changeTransactionValueField(newValue)
            }

            override fun onChangeTransactionType(newType: TransactionType) {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                viewModel.changeTransactionType(newType)
            }

            override fun onChangeTransactionDescription(newValue: String) {
                viewModel.changeTransactionDescription(newValue)
            }

            override fun onChangeTransactionPaidStatus(isPaid: Boolean) {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                viewModel.changeTransactionPaidStatus(isPaid)
            }

            override fun onSetDefaultAccountClick(account: BankAccount?) {
                account?.let {
                    viewModel.setDefaultAccount(account)
                }
            }

            override fun onToggleAccountSelector(open: Boolean) {
                showAccountSheet = open
            }

            override fun onToggleCategorySelector(open: Boolean) {
                showCategorySheet = open
            }
        }
    )

    if (showAccountSheet) {
        SelectAccountBottomSheet(
            accountSheetState = accountSheetState,
            accountList = uiState.accountList,
            onSelectAccount = {
                viewModel.changeAssociatedAccount(it)
                coroutineScope
                    .launch {
                        accountSheetState.hide()
                    }.invokeOnCompletion {
                        showAccountSheet = false
                    }
            },
            onToggleBottomSheet = { showAccountSheet = it },
            onCleanAccount = {
                viewModel.changeAssociatedAccount(null)
                coroutineScope
                    .launch {
                        accountSheetState.hide()
                    }.invokeOnCompletion {
                        showAccountSheet = false
                    }
            },
        )
    }

    if (showCategorySheet) {
        SelectCategoryBottomSheet(
            categorySheetState = categorySheetState,
            categoryList = uiState.categoryList,
            onSelectCategory = {
                viewModel.changeCategory(it)
                coroutineScope
                    .launch {
                        categorySheetState.hide()
                    }.invokeOnCompletion {
                        showCategorySheet = false
                    }
            },
            onShowBottomSheet = {
                showCategorySheet = it
            },
            onCleanCategory = {
                viewModel.changeCategory(null)
                coroutineScope
                    .launch {
                        categorySheetState.hide()
                    }.invokeOnCompletion {
                        showCategorySheet = false
                    }
            },
        )
    }
}

@Composable
fun NewTransactionScreenContent(
    uiState: NewTransactionUiState,
    listener: NewTransactionListener,
    modifier: Modifier = Modifier,
//    datePickerState: DatePickerState,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier =
            Modifier
                .background(uiState.transactionType.getBackgroundColor())
                .then(modifier)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus(true)
                    })
                },
    ) {
        NewTransactionHeader(
            transactionValue = uiState.value,
            transactionType = uiState.transactionType,
            listener = listener,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.small),
            modifier =
                Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(Dimens.huge, Dimens.huge, Dimens.none, Dimens.none),
                    )
                    .padding(vertical = Dimens.large, horizontal = Dimens.extraLarge)
                    .fillMaxSize(),
        ) {
            var openDialog by remember { mutableStateOf(false) }

//                DatePickerRow(
//                    datePickerState = datePickerState,
//                    openDialog = openDialog,
//                    operationTypeColor = transactionData.type.backgroundColor,
//                    onToggleDialog = {
//                        openDialog = it
//                        transactionData.onChangePaidStatus(verifyIfIsPaid(datePickerState.selectedDateMillis!!))
//                    },
//                )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
            ) {
                Icon(
                    painter = painterResource(CoreDrawables.ic_check_circle),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    modifier = Modifier.weight(1F),
                    text =
                        stringResource(
                            if (uiState.isPaid) {
                                R.string.transaction_paid
                            } else {
                                R.string.transaction_pending
                            },
                        ),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                DinSwitch(
                    checked = uiState.isPaid,
                    tint = uiState.transactionType.getBackgroundColor(),
                ) {
                    listener.onChangeTransactionPaidStatus(it)
                }
            }

            DinTextField(
                value = uiState.description,
                onValueChange = { listener.onChangeTransactionDescription(it) },
                leadingIcon = CoreDrawables.ic_edit,
                placeholder = R.string.placeholder_add_description,
            )

            DropDownSelector(
                leadingIcon = CoreDrawables.ic_bank,
                selectedIcon = CoreIcons.getIconByIdOrNull(uiState.associatedAccount?.iconId),
                placeholder = stringResource(TransactionStrings.action_select_account),
                selectedText = uiState.associatedAccount?.name,
                borderColor = MaterialTheme.colorScheme.primary,
                trailingAction = {
                    AnimatedVisibility(
                        visible = uiState.associatedAccount?.isDefault == false,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        TextButton(
                            onClick = { listener.onSetDefaultAccountClick(uiState.associatedAccount) },
                        ) {
                            Text(
                                text = stringResource(R.string.action_make_default),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                },
                onOpen = { listener.onToggleAccountSelector(true) },
            )

                DropDownSelector(
                    leadingIcon = CoreDrawables.ic_tag,
                    selectedIcon = CoreIcons.getIconByIdOrNull(uiState.category?.icon),
                    placeholder = stringResource(TransactionStrings.action_select_category),
                    selectedText = uiState.category?.name,
                    onOpen = { listener.onToggleCategorySelector(true) },
                )

            Spacer(modifier = Modifier.weight(1F))

            DinButton(
                modifier = Modifier.padding(bottom = Dimens.medium),
                enabled = uiState.associatedAccount != null && uiState.category != null,
                enabledColor = uiState.transactionType.getBackgroundColor(),
                onClick = {
//                        onCreateTransaction(transactionData)
//                        onDismissBottomSheet()
                },
            ) {
                Text(
                    text = stringResource(CoreStrings.action_save),
                    color = Color.White,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NewTransactionScreenContentPreview(
    @PreviewParameter(NewTransactionPreviewParameterProvider::class)
    uiState: NewTransactionUiState,
) {
    DinDinTheme {
        NewTransactionScreenContent(
            uiState = uiState,
            listener = DummyNewTransactionListener,
        )
    }
}