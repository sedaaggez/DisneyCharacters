package com.sedaaggez.disneycharacters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.model.Character
import com.sedaaggez.disneycharacters.util.downloadFromUrl
import com.sedaaggez.disneycharacters.util.placeholderProgressBar
import kotlinx.android.synthetic.main.item_disney_character.view.*

class DisneyCharacterAdapter(val characterList: ArrayList<Character>): RecyclerView.Adapter<DisneyCharacterAdapter.DisneyCharacterViewHolder>()  {

    class DisneyCharacterViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_disney_character, parent, false)
        return DisneyCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyCharacterViewHolder, position: Int) {
        holder.view.textViewCharacter.text = characterList[position].name
        holder.view.imageViewCharacter.downloadFromUrl(characterList[position].imageUrl, placeholderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<Character>) {
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }
}