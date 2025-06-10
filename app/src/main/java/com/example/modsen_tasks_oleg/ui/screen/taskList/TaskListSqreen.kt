package com.example.modsen_tasks_oleg.ui.screen.taskList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController) {
    val viewModel: TaskListViewModel = koinViewModel()
    val event: Flow<TaskListEvent> by remember { mutableStateOf(viewModel.event) }

    LaunchedEffect(Unit) {
        event.filterIsInstance<TaskListEvent.Navigate>().collect { event ->
            when (event.task) {
                TaskEnum.TASK_2 -> navController.navigate("task2_screen")
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Tasks List") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(viewModel.tasks) { task ->
                TaskItem(task = task) {
                    viewModel.processIntent(TaskListIntent.Navigate(task))
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskEnum, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.displayName, style = MaterialTheme.typography.titleMedium)
        }
    }
}