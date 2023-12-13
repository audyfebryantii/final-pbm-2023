package com.d121211005.rickandmortywiki.data

import com.d121211005.rickandmortywiki.data.source.remote.RickAndMortyWikiApiService
import com.d121211005.rickandmortywiki.data.repository.RickAndMortyWikiRepository
import com.d121211005.rickandmortywiki.data.repository.NetworkRickAndMortyWikiRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface RickAndMortyWikiContainer {
    val rickAndMortyWikiRepository: RickAndMortyWikiRepository
}

class DefaultAppContainer : RickAndMortyWikiContainer{
    private val baseUrl = "https://rickandmortyapi.com/api/"

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: RickAndMortyWikiApiService by lazy{
        retrofit.create(RickAndMortyWikiApiService::class.java)
    }

    override val rickAndMortyWikiRepository: RickAndMortyWikiRepository by lazy{
        NetworkRickAndMortyWikiRepository(retrofitService)
    }
}