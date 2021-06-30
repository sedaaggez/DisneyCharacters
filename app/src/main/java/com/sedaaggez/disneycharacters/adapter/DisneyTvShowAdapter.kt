package com.sedaaggez.disneycharacters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.disneycharacters.R
import kotlinx.android.synthetic.main.item_disney_tv_show.view.*

class DisneyTvShowAdapter(val tvShowList: ArrayList<String>): RecyclerView.Adapter<DisneyTvShowAdapter.DisneyTvShowViewHolder>()  {

    class DisneyTvShowViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyTvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_disney_tv_show, parent, false)
        return DisneyTvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyTvShowViewHolder, position: Int) {
        holder.view.textViewTvShowName.text = tvShowList[position]
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