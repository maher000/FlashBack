package com.maher.flashback.features.feed.presentatation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.maher.flashback.R
import com.maher.flashback.core.designSystem.components.LoadingWheel
import com.maher.flashback.core.designSystem.theme.FlashBackTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedScreenRoute(viewModel: FeedsViewModel = koinViewModel()) {
    val posts by viewModel.posts.collectAsStateWithLifecycle()
    FeedScreen(
        feedUiState = posts,
        onLikeClick = viewModel::likePost
    )
}

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    feedUiState: FeedUiState,
    onLikeClick: (postId: String, isLiked: Boolean) -> Unit
) {
    when (feedUiState) {
        is FeedUiState.Error -> EmptyState(modifier)
        is FeedUiState.Loading -> LoadingState()
        is FeedUiState.Success -> {
            if (feedUiState.posts.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 20.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(items = feedUiState.posts, key = { it.id }) { item ->
                        PostCard(post = item, onLikeClick = onLikeClick)
                    }
                }
            } else {
                EmptyState(modifier)
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    LoadingWheel(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(),
        contentDesc = "Loading posts",
    )
}

@Composable
fun PostCard(post: PostUiModel, onLikeClick: (postId: String, isLiked: Boolean) -> Unit) {
    Box(
        Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = post.imageUrl,
            contentDescription = "post image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.33f),
                            Color.Transparent
                        )
                    )
                )
        )
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.33f)
                        )
                    )
                )
        )
        BasicText(
            text = post.description,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        )

        var colorFilter = getLikeIconColorFilter(post.isLiked)

        Box(
            Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable(role = Role.Button) {
                    colorFilter = getLikeIconColorFilter(!post.isLiked)
                    onLikeClick(post.id, !post.isLiked)
                }
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                Icons.Outlined.Favorite,
                contentDescription = "Favorite",
                colorFilter = colorFilter
            )
        }
    }
}

private fun getLikeIconColorFilter(isLiked: Boolean): ColorFilter {
    return if (isLiked) {
        ColorFilter.tint(Color.Red)
    } else {
        ColorFilter.tint(Color.LightGray)
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.feature_feed_empty_state),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun EmptyStatePreview() {
    FlashBackTheme {
        EmptyState()
    }
}

@Preview
@Composable
fun PostCardPreview() {
    val postUiModel = PostUiModel(
        id = "1",
        createdAt = "",
        userId = "1",
        imageUrl = "https://loremflickr.com/640/480/animals",
        description = "Lorem ipsum maximum Lorem ipsum maximum Lorem ipsum maximum Lorem"
    )

    FlashBackTheme {
        PostCard(
            postUiModel,
            onLikeClick = { _, _ -> }
        )
    }
}

@Preview
@Composable
fun LoadingStatePreview() {
    FlashBackTheme {
        LoadingState()
    }
}
