package com.ziadmq.markettrader.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ziadmq.markettrader.viewmodel.GameViewModel
import com.ziadmq.markettrader.model.Product

@Composable
fun HomeScreen(
    vm: GameViewModel,
    onStartBargain: () -> Unit
) {
    val products = vm.products
    val money = vm.money

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "💰 رصيدك: $money دينار",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        Text(
            "📦 بضائعك:",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn {
            items(products) { product ->
                ProductItem(product)
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { onStartBargain() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("👤 زبون جديد (ابدأ المكاسرة)")
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("${product.name} • كمية: ${product.quantity}")
        Text("سعر البيع: ${product.baseSellPrice}")
    }
}
