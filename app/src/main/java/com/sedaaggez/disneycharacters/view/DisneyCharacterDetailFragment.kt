package com.sedaaggez.disneycharacters.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.adapter.DisneyTvShowAdapter
import com.sedaaggez.disneycharacters.adapter.DisneyVideoGameAdapter
import com.sedaaggez.disneycharacters.databinding.FragmentDisneyCharacterDetailBinding
import com.sedaaggez.disneycharacters.viewmodel.DisneyCharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_disney_character_detail.*

class DisneyCharacterDetailFragment : Fragment() {

    private lateinit var viewModel: DisneyCharacterDetailViewModel
    private var characterUuid = 0
    private lateinit var dataBinding: FragmentDisneyCharacterDetailBinding
    private val disneyTvShowAdapter = DisneyTvShowAdapter(arrayListOf())
    private val disneyVideoGameAdapter = DisneyVideoGameAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_disney_character_detail,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisneyCharacterDetailViewModel::class.java)

        recyclerViewTvShows.layoutManager = LinearLayoutManager(context)
        recyclerViewTvShows.adapter = disneyTvShowAdapter

        recyclerViewVideoGames.layoutManager = LinearLayoutManager(context)
        recyclerViewVideoGames.adapter = disneyVideoGameAdapter

        arguments?.let {
            characterUuid = DisneyCharacterDetailFragmentArgs.fromBundle(it).characterUuid
        }

        viewModel.getDataFromRoom(characterUuid)

        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                dataBinding.character = character
                if (character.videoGames!![0] != "") {
                    disneyVideoGameAdapter.updateVideoGameList(character.videoGames!!)
                    textViewVideoGame.visibility = View.VISIBLE
                }
            }
        })
    }

}