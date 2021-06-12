package com.sedaaggez.disneycharacters.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.disneycharacters.model.Character
import com.sedaaggez.disneycharacters.service.DisneyDatabase
import kotlinx.coroutines.launch

class DisneyCharacterDetailViewModel (application: Application) : BaseViewModel(application) {

    val characterLiveData = MutableLiveData<Character>()

    fun getDataFromRoom(uuid: Int) {
        launch {
            val dao = DisneyDatabase(getApplication()).disneyDao()
            val character = dao.getCharacter(uuid)
            characterLiveData.value = character
        }
    }

}