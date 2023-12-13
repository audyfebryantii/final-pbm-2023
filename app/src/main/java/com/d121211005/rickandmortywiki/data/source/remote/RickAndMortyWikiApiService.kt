package com.d121211005.rickandmortywiki.data.source.remote

import com.d121211005.rickandmortywiki.data.model.Characters
import retrofit2.http.GET


interface RickAndMortyWikiApiService{

    @GET("character")
    suspend fun getCharacter(): Characters

}