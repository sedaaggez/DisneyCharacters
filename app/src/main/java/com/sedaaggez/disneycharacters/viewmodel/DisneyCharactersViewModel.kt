package com.sedaaggez.disneycharacters.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.disneycharacters.model.Character
import com.sedaaggez.disneycharacters.model.CharacterList
import com.sedaaggez.disneycharacters.service.DisneyAPIService
import com.sedaaggez.disneycharacters.service.DisneyDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DisneyCharactersViewModel(application: Application) : BaseViewModel(application) {

    val characters = MutableLiveData<List<Character>>()
    val characterError = MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()
    val count = MutableLiveData<Int>()

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
                        storeInSQLite(t.data!!)
                        count.value = t.count
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

    private fun storeInSQLite(list: List<Character>) {
        launch {
            val dao = DisneyDatabase(getApplication()).disneyDao()
            dao.deleteAllCharacters()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCharacters(list)
        }
    }

    private fun showCharacters(characterList: List<Character>) {
        characters.value = characterList
        characterError.value = false
        characterLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}