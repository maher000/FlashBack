package com.maher.flashback.features.profile.presetation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.maher.flashback.core.data.Authentication
import com.maher.flashback.core.data.model.User
import com.maher.flashback.core.designSystem.theme.FlashBackTheme
import com.maher.flashback.features.feed.presentatation.PostUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenRoute(viewModel: ProfileViewModel = koinViewModel()) {
    val postsUiState by viewModel.posts.collectAsStateWithLifecycle()

    ProfileScreen(postsUiState, viewModel.currentUser)
}

@Composable
fun ProfileScreen(uiState: ProfileUiState, currentUser: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileHeader(
            imageUrl = currentUser.avatar
        )
        when (uiState) {
            is ProfileUiState.Error -> {
                // Handle error state
            }

            ProfileUiState.Loading -> {
                // Handle loading state
            }

            is ProfileUiState.Success -> {
                PostGrid(posts = uiState.posts)
            }
        }
    }
}

@Composable
fun ProfileHeader(imageUrl: String) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = Authentication.CurrentUser.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun PostGrid(posts: List<PostUiModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts) { post ->
            AsyncImage(
                model = post.imageUrl,
                contentDescription = "User Post",
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {
    FlashBackTheme {
        ProfileHeader("")
    }
}

@Preview(showBackground = true)
@Composable
fun PostGridPreview() {
    val posts = listOf(
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", "")
    )
    FlashBackTheme {
        PostGrid(posts)
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    val posts = listOf(
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", ""),
        PostUiModel("", "", "", "", "")
    )
    val uiState = ProfileUiState.Success(posts)
    FlashBackTheme {
        ProfileScreen(uiState, Authentication.CurrentUser)

    }
}