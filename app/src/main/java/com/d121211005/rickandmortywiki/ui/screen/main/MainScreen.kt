package com.d121211005.rickandmortywiki.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.d121211005.rickandmortywiki.RickAndMortyWikiUiState
import com.d121211005.rickandmortywiki.R
import com.d121211005.rickandmortywiki.ui.viewmodel.MainViewModel
import com.d121211005.rickandmortywiki.CharacterScreenState
import com.d121211005.rickandmortywiki.ui.component.appbar.RickAndMortyWikiAppBar
import com.d121211005.rickandmortywiki.ui.screen.detail.DetailScreen

enum class RickAndMortyWikiScreen(val title: String) {
    Home(title = "Rick and Morty Wiki"),
    Character(title = "Character Detail")
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun RickAndMortyWikiApp(
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RickAndMortyWikiScreen.valueOf(
        backStackEntry?.destination?.route ?: RickAndMortyWikiScreen.Home.name)
    val uistate by viewModel.uistate.collectAsState()


        RickAndMortyScaffold(
            viewModel = viewModel,
            currentScreen = currentScreen ,
            navController = navController,
            uiState = uistate,
        )

}
@Composable
fun RickAndMortyScaffold(
    viewModel: MainViewModel,
    currentScreen: RickAndMortyWikiScreen,
    navController: NavHostController,
    uiState: RickAndMortyWikiUiState,
    modifier: Modifier = Modifier,
){
    Scaffold(
        topBar = {
            RickAndMortyWikiAppBar(
                currentScreen = currentScreen,
            )
        }
    )
    {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            RickAndMortyContent(
                viewModel = viewModel,
                navController = navController,
                innerPadding = innerPadding,
                uiState = uiState
            )
        }
    }
}

@Composable
fun RickAndMortyContent(
    viewModel: MainViewModel,
    navController:NavHostController,
    innerPadding: PaddingValues,
    uiState: RickAndMortyWikiUiState
){
    NavHost(
        navController = navController,
        startDestination = RickAndMortyWikiScreen.Home.name,
        modifier = Modifier
            .padding(16.dp)
    ){
        composable(
            RickAndMortyWikiScreen.Home.name
        ){
            when(uiState.homeScreenState){
                is CharacterScreenState.Start -> Text("Memulai")
                is CharacterScreenState.Success ->
                    LazyColumn{
                        items(uiState.homeScreenState.characters.results.size){
                            index -> CharacterItem(
                            characterImage = uiState.homeScreenState.characters.results[index].image,
                            characterName = uiState.homeScreenState.characters.results[index].name,
                            characterStatus = uiState.homeScreenState.characters.results[index].status,
                            viewModel = viewModel,
                            onCardClick = {
                                navController.navigate(RickAndMortyWikiScreen.Character.name)
                                viewModel.getCharacterInfo(uiState.homeScreenState.characters.results[index])
                            })
                        }
                    }
                is CharacterScreenState.Loading ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center)
                        )
                    }

                is CharacterScreenState.Empty ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text (
                            modifier = Modifier
                                .size(300.dp)
                                .align(Alignment.Center),
                            text = "Informasi tidak ditemukan"
                        )
                    }

                is CharacterScreenState.Failure ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text (
                            modifier = Modifier
                                .size(300.dp)
                                .align(Alignment.Center),
                            text = "Gagal menemukan data"
                        )
                    }
                else -> {}
            }

        }
        composable(
            RickAndMortyWikiScreen.Character.name
        ){
            DetailScreen(uiState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    characterImage: String?,
    characterName: String?,
    characterStatus: String?,
    onCardClick: () -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
            .border(
                BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                RoundedCornerShape(8.dp)
            ),
        onClick = onCardClick
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CharacterIcon(imageUrl = characterImage)
            CharacterInformation(characterName = characterName, characterStatus = characterStatus)
        }
    }
}

@Composable
fun CharacterIcon(
    imageUrl : String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size_preview)),
        model = imageUrl,
        contentDescription = null
    )
}

@Composable
fun CharacterInformation(
    characterName: String?,
    characterStatus: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(dimensionResource(R.dimen.padding_small))
    ) {
        if (characterName != null) {
            Text(
                text = characterName,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
            )
        }
        Row(modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (characterStatus != null) {
                Text(
                    text = characterStatus,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


