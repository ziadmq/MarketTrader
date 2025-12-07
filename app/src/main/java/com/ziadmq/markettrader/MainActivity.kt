package com.ziadmq.markettrader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ziadmq.markettrader.view.HomeScreen
import com.ziadmq.markettrader.view.BargainMiniGame
import com.ziadmq.markettrader.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val nav = rememberNavController()
            val vm: GameViewModel = viewModel()

            NavHost(
                navController = nav,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreen(
                        vm = vm,
                        onStartBargain = {
                            vm.generateCustomer()
                            nav.navigate("bargain")
                        }
                    )
                }

                composable("bargain") {
                    BargainMiniGame(
                        vm = vm,
                        onFinish = { price ->
                            vm.money += price
                            nav.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
