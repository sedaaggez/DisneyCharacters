package com.sedaaggez.disneycharacters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedaaggez.disneycharacters.model.CharacterList

class DisneyCharactersViewModel : ViewModel(){

    val characters = MutableLiveData<CharacterList>()
    val characterError = MutableLiveData<Boolean>()
    val  characterLoading = MutableLiveData<Boolean>()

    fun getData() {
        // TODO: get data
    }
}