package uk.ac.tees.mad.e4294395.newsapp.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.SmartScreen
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithOutIconUI(
    title: String
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        title = {
            Text(text = title)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackIconUI(
    title: String,
    onIconClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = { onIconClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "null"
                )
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithActionIconUI(
    title: String,
    onActionClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = {
                onActionClick()
            }) {
                Icon(
                    imageVector = Icons.Default.SmartScreen,
                    contentDescription = "null"
                )
            }
        })
}