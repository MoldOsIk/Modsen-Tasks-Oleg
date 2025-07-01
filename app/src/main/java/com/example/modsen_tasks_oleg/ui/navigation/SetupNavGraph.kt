package com.example.modsen_tasks_oleg.ui.navigation

import androidx.compose.runtime.Composable
import androidx.core.bundle.Bundle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.ui.screen.task1.Task1Screen
import com.example.modsen_tasks_oleg.ui.screen.task1.Task1SuccessScreen
import com.example.modsen_tasks_oleg.ui.screen.task2.Task2Screen
import com.example.modsen_tasks_oleg.ui.screen.task2.Task3Screen
import com.example.modsen_tasks_oleg.ui.screen.taskList.TaskListScreen
import com.google.gson.Gson

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val gson = Gson()
    NavHost(navController = navController, startDestination = "task_list") {
        composable("task_list") { TaskListScreen(navController = navController) }
        composable("task1_detail") { Task1Screen(navController = navController) }
        composable("success_screen") { Task1SuccessScreen() }
        composable("task2_screen") { Task2Screen(navController = navController) }
        composable(
            "task3/{postJson}",
            arguments = listOf(navArgument("postJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val postJson = backStackEntry.arguments?.getString("postJson") ?: ""
            val post = gson.fromJson(postJson, PostDomainModel::class.java)
            Task3Screen(navController = navController, post = post)
        }
    }
}