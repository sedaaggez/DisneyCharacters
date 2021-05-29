package com.sedaaggez.disneycharacters.service

import com.sedaaggez.disneycharacters.model.CharacterList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DisneyAPI {

    @GET("characters")
    fun getCharacterList(@Query("page") page: Int): Single<CharacterList>
}