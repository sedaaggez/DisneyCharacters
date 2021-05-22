package com.sedaaggez.disneycharacters.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.adapter.DisneyCharacterAdapter
import com.sedaaggez.disneycharacters.viewmodel.DisneyCharactersViewModel
import kotlinx.android.synthetic.main.fragment_disney_characters.*

class DisneyCharactersFragment : Fragment() {

    private lateinit var viewModel : DisneyCharactersViewModel
    private val disneyCharacterAdapter = DisneyCharacterAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(DisneyCharactersViewModel::class.java)
        viewModel.getData()

        recyclerViewCharacter.layoutManager = LinearLayoutManager(context)
        recyclerViewCharacter.adapter = disneyCharacterAdapter

        observeLiveData()

        return inflater.inflate(R.layout.fragment_disney_characters, container, false)
    }

    private fun observeLiveData() {

        viewModel.characters.observe(viewLifecycleOwner, Observer {characterList ->
            characterList?.let {
                recyclerViewCharacter.visibility = View.VISIBLE
                disneyCharacterAdapter.updateCharacterList(characterList.data!!)
            }
        })

        viewModel.characterError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if(it) {
                    textViewError.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    recyclerViewCharacter.visibility = View.GONE
                } else {
                    textViewError.visibility = View.GONE

                }
            }
        })

        viewModel.characterLoading.observe(viewLifecycleOwner, Observer {loading ->
            loading?.let {
                if(it) {
                    progressBar.visibility = View.VISIBLE
                    textViewError.visibility = View.GONE
                    recyclerViewCharacter.visibility = View.GONE

                } else {
                    progressBar.visibility = View.GONE

                }
            }
        })
    }

}