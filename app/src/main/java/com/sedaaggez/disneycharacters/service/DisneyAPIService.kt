package com.sedaaggez.disneycharacters.service

import com.sedaaggez.disneycharacters.model.CharacterList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DisneyAPIService {

    private val BASE_URL = "https://api.disneyapi.dev/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DisneyAPI::class.java)

    fun getCharacterData(page: Int) : Single<CharacterList> {
        return api.getCharacterList(page)
    }

}