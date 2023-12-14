package com.d121211005.rickandmortywiki.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211005.rickandmortywiki.RickAndMortyWikiUiState
import com.d121211005.rickandmortywiki.RickAndMortyWikiApplication
import com.d121211005.rickandmortywiki.CharacterScreenState
import com.d121211005.rickandmortywiki.DetailScreenState
import com.d121211005.rickandmortywiki.data.repository.RickAndMortyWikiRepository
import com.d121211005.rickandmortywiki.data.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(private val rickAndMortyWikiRepository: RickAndMortyWikiRepository): ViewModel() {
    private val _uistate = MutableStateFlow(
        RickAndMortyWikiUiState(
            isList = true,
            isLightTheme = true,
            homeScreenState = CharacterScreenState.Start,
            )
    )
    val uistate: StateFlow<RickAndMortyWikiUiState> = _uistate.asStateFlow()

    init {
        getCharacter()
    }

    fun getCharacter(){
        _uistate.update { currentState -> currentState.copy(homeScreenState = CharacterScreenState.Loading) }
        viewModelScope.launch {
            try{
                val data = rickAndMortyWikiRepository.getCharacter();
                println(data.results.size)
                if(data.results.isEmpty()){
                    _uistate.update { currentState -> currentState.copy(homeScreenState = CharacterScreenState.Empty) }
                }else{
                    _uistate.update { currentState ->  currentState.copy(homeScreenState = CharacterScreenState.Success(data))}
                }
            } catch (e: IOException){
                _uistate.update { currentState -> currentState.copy(homeScreenState = CharacterScreenState.Failure)}
            }
        }
    }

    fun getCharacterInfo(character : Result){
        _uistate.update { currentState -> currentState.copy(detailScreenState = DetailScreenState.Loading) }
        viewModelScope.launch {
            try{
                if(character == null){
                    _uistate.update { currentState -> currentState.copy(detailScreenState = DetailScreenState.Empty) }
                }else{
                    _uistate.update { currentState ->  currentState.copy(detailScreenState = DetailScreenState.Success(character))}
                }
            } catch (e: IOException){
                _uistate.update { currentState -> currentState.copy(detailScreenState = DetailScreenState.Failure)}
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RickAndMortyWikiApplication)
                val rickAndMortyWikiRepository = application.container.rickAndMortyWikiRepository
                MainViewModel(rickAndMortyWikiRepository = rickAndMortyWikiRepository)
            }
        }
    }

}