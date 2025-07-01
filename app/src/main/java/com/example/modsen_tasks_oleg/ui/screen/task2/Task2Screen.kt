package com.example.modsen_tasks_oleg.ui.screen.task2

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.modsen_tasks_oleg.ui.components.CustomLoader
import com.example.modsen_tasks_oleg.ui.components.PostItem
import com.example.modsen_tasks_oleg.ui.screen.task2.model.Task2Intent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsState
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.modsen_tasks_oleg.R
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.ui.screen.task2.components.SearchField
import com.example.modsen_tasks_oleg.ui.screen.task2.components.parseToString
import com.example.modsen_tasks_oleg.ui.screen.task2.model.Task2Event
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task2Screen(navController: NavController) {
    val viewModel: Task2ViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val intent: (Task2Intent) -> Unit by remember { mutableStateOf(viewModel::processIntent) }
    val context = LocalContext.current
    val gson = remember { Gson() }

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is Task2Event.ShowError -> {
                    Toast.makeText(
                        context,
                        event.exception.parseToString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Task2Event.SelectPost -> {
                    val postJson = gson.toJson(event.post)
                    navController.navigate("task3/$postJson")
                }
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.posts_list_title))}) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            SearchField(
                onSearch = { query ->
                    intent(Task2Intent.UpdateSearchQuery(query))
                },
                modifier = Modifier.padding(16.dp)
            )
            PostsContent(state = state,
                intent = intent,
                modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PostsContent(
    state: PostsState,
    intent: (Task2Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        when {
            state.isLoading -> CustomLoader(modifier = Modifier.align(Alignment.Center))
            state.error != null -> Text(
                text = state.error.parseToString(context),
                modifier = Modifier.align(Alignment.Center)
            )
            else -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.posts) { post ->
                    PostItem(
                        post = post,
                        onClick = { intent(Task2Intent.SelectPost(post)) }
                    )
                }
            }
        }
    }
}