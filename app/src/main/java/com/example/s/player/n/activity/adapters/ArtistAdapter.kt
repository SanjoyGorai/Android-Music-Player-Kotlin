package com.example.s.player.n.activity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.databinding.AlbumRvLayoutBinding
import com.example.s.player.n.activity.activities.MainActivity.Companion.albumList
import com.example.s.player.n.activity.fragments.AlbumDetailsFragment
import com.example.s.player.n.activity.fragments.ArtistDetailsFragment
import com.example.s.player.n.activity.models.Artist

class ArtistAdapter(val context: Context, private val artistList: ArrayList<String>) :
    RecyclerView.Adapter<ArtistAdapter.VieHolder>() {
    class VieHolder(binding: AlbumRvLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val albumTitle: TextView = binding.albumName
        val totalSong: TextView = binding.totalSong
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VieHolder {
        return VieHolder(AlbumRvLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: VieHolder, position: Int) {
        holder.albumTitle.text = artistList[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ArtistDetailsFragment::class.java)
//            intent.putExtra("title", albumList[position].title)
            startActivity(context,intent,null)
        }

    }

    override fun getItemCount(): Int {
        return artistList.size
    }
}