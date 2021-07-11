package com.sedaaggez.disneycharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import com.sedaaggez.disneycharacters.databinding.ItemDisneyTvShowBinding

class DisneyTvShowAdapter(val tvShowList: ArrayList<String>) :
    RecyclerView.Adapter<DisneyTvShowAdapter.DisneyTvShowViewHolder>() {

    class DisneyTvShowViewHolder(var view: ItemDisneyTvShowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyTvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDisneyTvShowBinding>(inflater, R.layout.item_disney_tv_show, parent, false)
        return DisneyTvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyTvShowViewHolder, position: Int) {
        holder.view.tvShow = tvShowList[position]
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    fun updateTvShowList(newTvShowList: List<String>) {
        tvShowList.clear()
        tvShowList.addAll(newTvShowList)
        notifyDataSetChanged()
    }
}