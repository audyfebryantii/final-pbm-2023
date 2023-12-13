package com.d121211005.rickandmortywiki.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d121211005.rickandmortywiki.ui.theme.RickAndMortyWikiTheme
import com.d121211005.rickandmortywiki.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
            val uiState by viewModel.uistate.collectAsState();
            RickAndMortyWikiTheme(){
                Surface(
                    modifier = Modifier,
                ) {
                    RickAndMortyWikiApp(viewModel = viewModel)
                }
            }
        }
    }
}