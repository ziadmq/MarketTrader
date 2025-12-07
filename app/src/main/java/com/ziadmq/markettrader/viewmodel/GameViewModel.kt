package com.ziadmq.markettrader.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziadmq.markettrader.model.BargainState
import com.ziadmq.markettrader.model.Customer
import com.ziadmq.markettrader.model.Product
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {

    var money by mutableStateOf(100)
    var products = mutableStateListOf<Product>()
    var currentCustomer by mutableStateOf<Customer?>(null)
    var bargainState by mutableStateOf(BargainState())

    private var timer: Job? = null

    init {
        products.add(Product(1, "سجاد", 20, 35))
        products.add(Product(2, "فوانيس", 15, 25))
        products.add(Product(3, "بهارات", 10, 18))
    }

    fun generateCustomer() {
        val p = products.random()
        currentCustomer = Customer(
            p.id,
            p.baseSellPrice,
            p.baseSellPrice + Random.nextInt(10, 20)
        )
    }

    fun startBargain() {
        bargainState = bargainState.copy(isRunning = true)
        timer = viewModelScope.launch {
            while (bargainState.isRunning) {
                delay(10)
                moveBar()
            }
        }
    }

    private fun moveBar() {
        var p = bargainState.progress
        var dir = bargainState.isMovingRight

        if (dir) p += 0.01f else p -= 0.01f

        if (p >= 1f) dir = false
        if (p <= 0f) dir = true

        bargainState = bargainState.copy(progress = p, isMovingRight = dir)
    }

    fun stopBargain() : Int {
        bargainState = bargainState.copy(isRunning = false)
        timer?.cancel()

        val percent = bargainState.progress
        val customer = currentCustomer!!

        return when {
            percent > 0.7f -> customer.maxOffer
            percent > 0.4f -> (customer.baseOffer * 1.2).toInt()
            else -> customer.baseOffer
        }
    }
}
