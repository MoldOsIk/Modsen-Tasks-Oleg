package com.example.modsen_tasks_oleg.ui.screen.task2

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.modsen_tasks_oleg.R
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.ui.components.CustomLoader
import com.example.modsen_tasks_oleg.ui.components.PostItem
import com.example.modsen_tasks_oleg.ui.screen.task2.components.parseToString
import com.example.modsen_tasks_oleg.ui.screen.task2.model.CommentsEvent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.CommentsState
import com.example.modsen_tasks_oleg.ui.screen.task2.model.Task2Intent
import kotlinx.coroutines.flow.filterIsInstance
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task3Screen(navController: NavController,post: PostDomainModel) {

    val commentsViewModel: CommentsViewModel = koinViewModel { parametersOf(post) }
    val state by commentsViewModel.state.collectAsState()
    val context = LocalContext.current
    Log.d("screen3", post.toString())
    LaunchedEffect(Unit) {
        commentsViewModel.event.filterIsInstance<CommentsEvent.ShowError>().collect { event ->
            Toast.makeText(context, event.exception.parseToString(context), Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(R.string.comments_title)) }) }) { padding ->
        CommentsContent(state = state, modifier = Modifier.padding(padding))
    }
}

@Composable
private fun CommentsContent(
    state: CommentsState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            state.isLoading -> CustomLoader(modifier = Modifier.align(Alignment.Center))
            state.error != null -> Text(text = state.error.parseToString(LocalContext.current),
                modifier = Modifier.align(Alignment.Center))
            else -> Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                PostItem(post = state.currentPost, onClick = {})
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.comments) { comment ->
                        Text(text = "${comment.name} (${comment.email}): ${comment.body}")
                    }
                }
            }
        }
    }
}