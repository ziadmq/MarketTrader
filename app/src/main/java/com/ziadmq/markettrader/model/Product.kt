package com.ziadmq.markettrader.model

data class Product(
    val id: Int,
    val name: String,
    val buyPrice: Int,
    val baseSellPrice: Int,
    var quantity: Int = 0,
    var level: Int = 1
)