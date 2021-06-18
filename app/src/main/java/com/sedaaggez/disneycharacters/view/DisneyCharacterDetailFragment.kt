package com.sedaaggez.disneycharacters.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.util.downloadFromUrl
import com.sedaaggez.disneycharacters.util.placeholderProgressBar
import com.sedaaggez.disneycharacters.viewmodel.DisneyCharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_disney_character_detail.*

class DisneyCharacterDetailFragment : Fragment() {
    private lateinit var viewModel : DisneyCharacterDetailViewModel
    private var characterUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disney_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisneyCharacterDetailViewModel::class.java)

        arguments?.let {
            characterUuid = DisneyCharacterDetailFragmentArgs.fromBundle(it).characterUuid
        }

        viewModel.getDataFromRoom(characterUuid)

        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                textViewDetailCharacterName.text = character.name
                context?.let {
                    imageViewDetailCharacter.downloadFromUrl(character.imageUrl, placeholderProgressBar(it))
                }
            }
        })
    }

}