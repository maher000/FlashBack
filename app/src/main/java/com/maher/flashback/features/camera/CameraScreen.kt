package com.maher.flashback.features.camera

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.maher.flashback.R
import com.maher.flashback.core.navigation.Screen
import java.io.File

@Composable
fun CameraScreenRoute(navController: NavHostController) {

    CameraScreen(
        onImageCaptured = { imageUri->
            val currentRoute = navController.currentBackStackEntry?.destination?.route

            currentRoute?.let { route ->
                navController.navigate(Screen.AddPost(imageUri = imageUri.toString())) {
                    popUpTo(route) { inclusive = true }
                }
            }
        },
        onImageCaptureError = { navController.popBackStack(route = Screen.Feed.route, false) }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    onImageCaptured: (uri: Uri) -> Unit,
    onImageCaptureError: () -> Unit
) {

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var requestPermission by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    val context = LocalContext.current

    val launcherForImageCapture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { result ->
        if (result) {
            println("CameraScreen Image captured successfully: $imageUri")
            onImageCaptured(imageUri)
        } else {
            Toast.makeText(context,
                context.getString(R.string.capture_image_error_toast_message), Toast.LENGTH_SHORT).show()
            onImageCaptureError()
        }
    }

    if (cameraPermissionState.status.isGranted) {
        LaunchedEffect(cameraPermissionState) {
            val photoFile = File(context.externalCacheDir, "captured_image.jpg")
            val photoUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                photoFile
            )
            imageUri = photoUri
            launcherForImageCapture.launch(imageUri)
        }
    } else {
        requestPermission = true
    }

    LaunchedEffect(requestPermission) {
        if (requestPermission) {
            cameraPermissionState.launchPermissionRequest()
        }
    }
}
