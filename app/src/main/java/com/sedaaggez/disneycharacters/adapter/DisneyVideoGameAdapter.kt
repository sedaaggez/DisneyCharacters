package com.sedaaggez.disneycharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.databinding.ItemDisneyVideoGameBinding

class DisneyVideoGameAdapter(val videoGameList: ArrayList<String>): RecyclerView.Adapter<DisneyVideoGameAdapter.DisneyVideoGameHolder>()  {

    class DisneyVideoGameHolder(var view: ItemDisneyVideoGameBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyVideoGameHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDisneyVideoGameBinding>(inflater, R.layout.item_disney_video_game, parent, false)
        return DisneyVideoGameHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyVideoGameHolder, position: Int) {
        holder.view.videoGame = videoGameList[position]
    }

    override fun getItemCount(): Int {
        return videoGameList.size
    }

    fun updateVideoGameList(newVideoGameList: List<String>) {
        videoGameList.clear()
        videoGameList.addAll(newVideoGameList)
        notifyDataSetChanged()
    }
}