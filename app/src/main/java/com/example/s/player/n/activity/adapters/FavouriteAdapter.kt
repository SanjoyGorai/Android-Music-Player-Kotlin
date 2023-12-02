package com.example.s.player.n.activity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.R
import com.example.s.player.databinding.FavouriteViewBinding
import com.example.s.player.databinding.RvSongItemBinding
import com.example.s.player.n.activity.activities.PlayerActivity
import com.example.s.player.n.activity.fragments.FavouriteFragment
import com.example.s.player.n.activity.models.Music

class FavouriteAdapter(private val context: Context?, private val fabMusicList: ArrayList<Music>) :
    RecyclerView.Adapter<FavouriteAdapter.MyHolder>() {
    class MyHolder(binding: FavouriteViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val songTitle = binding.songTitle

        //        val artisImg = binding.songAristImg
//        val songSize = binding.songSize
//        val menuMore = binding.more
        val favourite = binding.favourite

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.MyHolder {
        return FavouriteAdapter.MyHolder(
            FavouriteViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: FavouriteAdapter.MyHolder, position: Int) {
        holder.songTitle.text = fabMusicList[position].title

        holder.favourite.setOnClickListener {

        }
    }


    override fun getItemCount(): Int {
        return fabMusicList.size
    }

}