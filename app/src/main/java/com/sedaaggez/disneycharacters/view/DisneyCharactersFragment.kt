package com.sedaaggez.disneycharacters.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.adapter.DisneyCharacterAdapter
import com.sedaaggez.disneycharacters.viewmodel.DisneyCharactersViewModel
import kotlinx.android.synthetic.main.fragment_disney_characters.*

class DisneyCharactersFragment : Fragment() {

    private lateinit var viewModel : DisneyCharactersViewModel
    private val disneyCharacterAdapter = DisneyCharacterAdapter(arrayListOf())
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var page = 1
    private var count = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disney_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DisneyCharactersViewModel::class.java)
        viewModel.getData(page)

        recyclerViewCharacters.layoutManager = GridLayoutManager(context, 2)
        recyclerViewCharacters.adapter = disneyCharacterAdapter

        linearLayoutManager = recyclerViewCharacters.layoutManager as LinearLayoutManager

        observeLiveData()

        recyclerViewCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    var findLastCompletelyVisibleItemPosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    var itemCount = linearLayoutManager.itemCount
                    if (count == 50 && findLastCompletelyVisibleItemPosition == itemCount - 1) {
                        page++
                        viewModel.getData(page)
                    }
                }

                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    private fun observeLiveData() {

        viewModel.characters.observe(viewLifecycleOwner, Observer {characters ->
            characters?.let {
                recyclerViewCharacters.visibility = View.VISIBLE
                disneyCharacterAdapter.updateCharacterList(characters)
            }
        })

        viewModel.count.observe(viewLifecycleOwner, Observer { countLiveData ->
            countLiveData.let {
                count = countLiveData
            }
        })

        viewModel.characterError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if(it) {
                    textViewError.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    recyclerViewCharacters.visibility = View.GONE
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
                    recyclerViewCharacters.visibility = View.GONE

                } else {
                    progressBar.visibility = View.GONE

                }
            }
        })
    }

}