package com.ziadmq.markettrader.model

data class BargainState(
    val progress: Float = 0f,
    val isMovingRight: Boolean = true,
    val isRunning: Boolean = false
)

