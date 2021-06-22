package com.sedaaggez.disneycharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.databinding.ItemDisneyCharacterBinding
import com.sedaaggez.disneycharacters.model.Character

class DisneyCharacterAdapter(val characterList: ArrayList<Character>) :
    RecyclerView.Adapter<DisneyCharacterAdapter.DisneyCharacterViewHolder>() {

    class DisneyCharacterViewHolder(var view: ItemDisneyCharacterBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDisneyCharacterBinding>(inflater, R.layout.item_disney_character, parent, false)
        return DisneyCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyCharacterViewHolder, position: Int) {
        holder.view.character = characterList[position]
        // TODO: Listener bindings
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