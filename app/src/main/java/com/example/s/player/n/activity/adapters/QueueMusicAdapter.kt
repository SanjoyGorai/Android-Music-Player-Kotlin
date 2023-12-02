package com.example.s.player.n.activity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.databinding.QueueViewBinding
import com.example.s.player.n.activity.activities.PlayerActivity
import com.example.s.player.n.activity.activities.QueueActivity
import com.example.s.player.n.activity.models.Music

class QueueMusicAdapter(
    private val context: Context,
    private val musicList: ArrayList<Music>
) :
    RecyclerView.Adapter<QueueMusicAdapter.MyHolder>() {
    class MyHolder(binding: QueueViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val songTitle = binding.songTitle
        val artisImg = binding.songAristImg
        val songSize = binding.songSize
        val menuMore = binding.more

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(QueueViewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.songTitle.text = musicList[position].title
        holder.songSize.text = musicList[position].size

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PlayerActivity::class.java)
            val title = "title"
            val size = "size"
            intent.putExtra("index", position)
            intent.putExtra("class", "MusicAdapter")
            intent.putExtra(title, musicList[position].title)
            intent.putExtra(size, musicList[position].size)
//            startActivity(context!!, intent, null)
        }


    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}