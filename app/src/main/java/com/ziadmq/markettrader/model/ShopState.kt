package com.ziadmq.markettrader.model

import com.ziadmq.markettrader.model.Product
import com.ziadmq.markettrader.model.Customer

data class ShopState(
    val money: Int = 100,
    val products: List<Product> = emptyList(),
    val currentCustomer: Customer? = null,
    val shopLevel: Int = 1
)