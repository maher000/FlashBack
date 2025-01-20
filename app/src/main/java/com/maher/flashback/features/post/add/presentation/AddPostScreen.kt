package com.maher.flashback.features.post.add.presentation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.maher.flashback.R
import com.maher.flashback.core.designSystem.theme.FlashBackTheme
import com.maher.flashback.core.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddPostScreenRoute(
    navController: NavHostController,
    viewModel: AddPostViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState is AddPostUiState.Success) {
        navController.navigate(
            route = Screen.Feed.route,
           // navOptions = navOptions
        )
       /* navigateToMainDestinations(
            screen = Screen.Feed,
            navController = navController
        )

        */
    }


    AddPostScreen(
        uiState = uiState,
        imageUri = viewModel.imageUri,
        onSubmit = viewModel::addPost
    )
}

@Composable
fun AddPostScreen(
    uiState: AddPostUiState,
    imageUri: Uri,
    onSubmit: (String) -> Unit
) {
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(R.string.add_post_description)) },
            placeholder = { Text(stringResource(R.string.add_post_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )

        AsyncImage(
            model = imageUri,
            contentDescription = "Selected Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)

        )

        Button(
            onClick = {
                onSubmit(description)
            },
            enabled = uiState !is AddPostUiState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState is AddPostUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(stringResource(R.string.add_post_submit_button))
            }
        }
    }
}

@Preview
@Composable
fun AddPostScreenPreview() {
    FlashBackTheme {
        AddPostScreen(
            imageUri = Uri.EMPTY,
            onSubmit = { description -> },
            uiState = AddPostUiState.Success
        )
    }
}