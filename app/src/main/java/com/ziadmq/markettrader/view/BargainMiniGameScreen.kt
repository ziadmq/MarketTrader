package com.ziadmq.markettrader.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ziadmq.markettrader.model.BargainState
import com.ziadmq.markettrader.model.Customer
import com.ziadmq.markettrader.model.Product
import com.ziadmq.markettrader.viewmodel.GameViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun BargainMiniGame(
    vm: GameViewModel = viewModel(),
    onFinish: (Int) -> Unit
) {
    val state = vm.bargainState

    Column(modifier = Modifier.padding(16.dp)) {

        Text("المكاسرة", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.DarkGray)
        ) {
            // الأخضر
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.3f)
                    .align(Alignment.Center)
                    .background(Color.Green.copy(alpha = 0.6f))
            )

            // المؤشر
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(10.dp)
                    .offset(x = (state.progress * 300).dp)
                    .background(Color.Yellow)
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            val price = vm.stopBargain()
            onFinish(price)
        }) {
            Text("وقف")
        }
    }
}
