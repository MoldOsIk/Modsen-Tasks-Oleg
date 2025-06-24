package com.example.modsen_tasks_oleg.ui.screen.task2

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsEvent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsIntent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsState
import kotlinx.coroutines.flow.filterIsInstance
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import com.example.modsen_tasks_oleg.R
import com.example.modsen_tasks_oleg.ui.screen.task2.components.parseToString
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task2Screen(navController: NavController) {
    val viewModel: PostsViewModel = koinViewModel()
    val state: PostsState by viewModel.state.collectAsState()
    val intent: (PostsIntent) -> Unit by remember { mutableStateOf(viewModel::processIntent) }
    val event: Flow<PostsEvent> by remember { mutableStateOf(viewModel.event) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        event.filterIsInstance<PostsEvent.ShowError>().collect { event ->
            Toast.makeText(context, event.exception.parseToString(context), Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.task2_title)) }) }
    ) { padding ->
        PostsContent(
            state = state,
            intent = intent,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
private fun PostsContent(
    state: PostsState,
    intent: (PostsIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CustomLoader(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Text(
                    text = state.error.parseToString(context),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.posts) { post ->
                        PostItem(
                            post = post,
                            onClick = { intent(PostsIntent.SelectPost(post)) }
                        )
                    }
                }
            }
        }
    }
}