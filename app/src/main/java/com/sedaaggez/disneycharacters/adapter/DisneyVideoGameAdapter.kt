package com.sedaaggez.disneycharacters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import kotlinx.android.synthetic.main.item_disney_video_game.view.*

class DisneyVideoGameAdapter(val videoGameList: ArrayList<String>): RecyclerView.Adapter<DisneyVideoGameAdapter.DisneyVideoGameHolder>()  {

    class DisneyVideoGameHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyVideoGameHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_disney_video_game, parent, false)
        return DisneyVideoGameHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyVideoGameHolder, position: Int) {
        holder.view.textViewVideoGameName.text = videoGameList[position]
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