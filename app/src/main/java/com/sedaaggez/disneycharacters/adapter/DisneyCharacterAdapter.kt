package com.sedaaggez.disneycharacters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.databinding.ItemDisneyCharacterBinding
import com.sedaaggez.disneycharacters.model.Character
import com.sedaaggez.disneycharacters.view.DisneyCharactersFragmentDirections
import kotlinx.android.synthetic.main.item_disney_character.view.*

class DisneyCharacterAdapter(val characterList: ArrayList<Character>) :
    RecyclerView.Adapter<DisneyCharacterAdapter.DisneyCharacterViewHolder>(), CharacterClickListener {

    class DisneyCharacterViewHolder(var view: ItemDisneyCharacterBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDisneyCharacterBinding>(inflater, R.layout.item_disney_character, parent, false)
        return DisneyCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyCharacterViewHolder, position: Int) {
        holder.view.character = characterList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<Character>) {
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    override fun onCharacterClicked(v: View) {
        val uuid = v.characterUuidText.text.toString().toInt()
        val action = DisneyCharactersFragmentDirections.actionDisneyCharactersFragmentToDisneyCharacterDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}