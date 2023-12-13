package com.d121211005.rickandmortywiki.data.repository

import com.d121211005.rickandmortywiki.data.model.Characters
import com.d121211005.rickandmortywiki.data.source.remote.RickAndMortyWikiApiService

interface RickAndMortyWikiRepository {
    suspend fun getCharacter(): Characters
}

class NetworkRickAndMortyWikiRepository(
    private val rickAndMortyWikiApiService : RickAndMortyWikiApiService
): RickAndMortyWikiRepository {
    override suspend fun getCharacter(): Characters = rickAndMortyWikiApiService.getCharacter()

}