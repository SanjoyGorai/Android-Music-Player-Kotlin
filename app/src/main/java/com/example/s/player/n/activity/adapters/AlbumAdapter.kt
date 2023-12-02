package com.example.s.player.n.activity.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.databinding.AlbumRvLayoutBinding
import com.example.s.player.databinding.FragmentAlbumBinding
import com.example.s.player.n.activity.fragments.AlbumDetailsFragment
import com.example.s.player.n.activity.models.Album
import com.example.s.player.n.activity.models.Folder

class AlbumAdapter(val context: Context, private val albumList: ArrayList<Folder>) :
    RecyclerView.Adapter<AlbumAdapter.VieHolder>() {
    class VieHolder(binding: AlbumRvLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val albumTitle: TextView = binding.albumName
        val totalSong: TextView = binding.totalSong
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VieHolder {
        return VieHolder(AlbumRvLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: VieHolder, position: Int) {
        holder.albumTitle.text = albumList[position].name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AlbumDetailsFragment::class.java)
            val bundle = Bundle()
            bundle.putString("title",albumList[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}