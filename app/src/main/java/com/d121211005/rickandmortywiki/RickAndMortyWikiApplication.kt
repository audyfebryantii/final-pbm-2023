package com.d121211005.rickandmortywiki

import android.app.Application
import com.d121211005.rickandmortywiki.data.DefaultAppContainer
import com.d121211005.rickandmortywiki.data.RickAndMortyWikiContainer


class RickAndMortyWikiApplication():Application() {
    lateinit var container : RickAndMortyWikiContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}