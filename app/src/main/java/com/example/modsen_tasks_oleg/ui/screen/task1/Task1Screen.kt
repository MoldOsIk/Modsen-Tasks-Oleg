package com.example.modsen_tasks_oleg.ui.screen.task1

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.modsen_tasks_oleg.R
import com.example.modsen_tasks_oleg.ui.screen.task1.model.Task1Event
import com.example.modsen_tasks_oleg.ui.screen.task1.model.Task1Intent
import com.example.modsen_tasks_oleg.ui.screen.task1.model.Task1ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task1Screen(navController: NavController) {
    val viewModel: Task1ViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is Task1Event.NavigateToSuccess -> {
                    navController.navigate("success_screen")
                }
                is Task1Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.task1_details_title)) }) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.login,
                onValueChange = { viewModel.processIntent(Task1Intent.UpdateLogin(it)) },
                label = { Text(stringResource(R.string.login)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.processIntent(Task1Intent.UpdatePassword(it)) },
                label = { Text(stringResource(R.string.password)) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.processIntent(Task1Intent.Submit)
                          },
                enabled = state.login.isNotBlank() && state.password.isNotBlank() && !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(stringResource(R.string.log_In_button))
                }
            }
        }
    }
}