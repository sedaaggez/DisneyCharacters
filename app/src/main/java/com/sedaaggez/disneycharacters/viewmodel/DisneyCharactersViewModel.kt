package com.sedaaggez.disneycharacters.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.disneycharacters.model.Character
import com.sedaaggez.disneycharacters.model.CharacterList
import com.sedaaggez.disneycharacters.service.DisneyAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DisneyCharactersViewModel(application: Application) : BaseViewModel(application){

    val characters = MutableLiveData<List<Character>>()
    val characterError = MutableLiveData<Boolean>()
    val  characterLoading = MutableLiveData<Boolean>()

    private val disneyAPIService = DisneyAPIService()
    private val disposable = CompositeDisposable()

    fun getData(page: Int) {
        characterError.value = false
        disposable.add(
            disneyAPIService.getCharacterData(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CharacterList>() {
                    override fun onSuccess(t: CharacterList) {
                        showCharacters(t)
                    }

                    override fun onError(e: Throwable) {
                        characterError.value = true
                        characterLoading.value = false
                        e.printStackTrace()
                        println(e.message)
                    }

                })
        )
    }

    private fun showCharacters(characterList: CharacterList) {
        characters.value = characterList.data
        characterError.value = false
        characterLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}