package com.d121211005.rickandmortywiki.ui.component.appbar


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.d121211005.rickandmortywiki.ui.screen.main.RickAndMortyWikiScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyWikiAppBar(
    currentScreen: RickAndMortyWikiScreen,
    modifier: Modifier = Modifier,
){
    TopAppBar(
        title = {
            Text(
                currentScreen.title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults
            .mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
        modifier = modifier,
    )
}