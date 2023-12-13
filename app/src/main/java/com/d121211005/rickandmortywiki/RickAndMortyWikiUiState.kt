package com.d121211005.rickandmortywiki

import com.d121211005.rickandmortywiki.data.model.Characters
import com.d121211005.rickandmortywiki.data.model.Result



sealed interface CharacterScreenState {
    object Start:CharacterScreenState
    object Loading:CharacterScreenState
    object Empty:CharacterScreenState
    data class Success(val characters: Characters): CharacterScreenState
    object Failure:CharacterScreenState
}

sealed interface DetailScreenState{
    object Loading:DetailScreenState
    object Empty:DetailScreenState
    data class Success(val characterDetail: Result): DetailScreenState
    object Failure:DetailScreenState
}

data class RickAndMortyWikiUiState(
    val isList:Boolean = false,
    val isLightTheme:Boolean = true,
    val homeScreenState: CharacterScreenState = CharacterScreenState.Start,
    val detailScreenState: DetailScreenState = DetailScreenState.Loading,
    val isSortedAtoZ: Boolean = true,
)
