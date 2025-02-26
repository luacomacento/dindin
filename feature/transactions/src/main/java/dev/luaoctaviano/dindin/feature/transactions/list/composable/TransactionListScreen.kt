package dev.luaoctaviano.dindin.feature.transactions.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.ui.composable.TransactionItem
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.feature.transactions.extension.TransactionStrings
import dev.luaoctaviano.dindin.feature.transactions.list.TransactionListListener
import dev.luaoctaviano.dindin.feature.transactions.list.TransactionListUiState
import dev.luaoctaviano.dindin.feature.transactions.list.TransactionListViewModel

@Composable
fun TransactionListScreen(
    viewModel: TransactionListViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    TransactionListScreenContent(
        modifier = modifier,
        uiState = uiState,
        listener = object : TransactionListListener {
            override fun onDeleteTransactionClick(transactionId: Long) {
                viewModel.deleteTransaction(transactionId)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreenContent(
    uiState: TransactionListUiState,
    listener: TransactionListListener,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(TransactionStrings.title_transactions)
                    )
                },
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .padding(Dimens.extraLarge),
            verticalArrangement = Arrangement.spacedBy(Dimens.medium),
        ) {
            itemsIndexed(uiState.transactionList) { index, item ->
                TransactionItem(
                    transaction = item,
                    hideValues = false,
                    listener = listener,
                )
            }
        }
    }
}

