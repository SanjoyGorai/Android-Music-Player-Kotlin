package com.example.s.player.n.activity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.R
import com.example.s.player.databinding.FolderViewBinding
import com.example.s.player.databinding.RvSongItemBinding
import com.example.s.player.n.activity.activities.FolderActivity
import com.example.s.player.n.activity.models.Folder
import com.example.s.player.n.activity.models.Music
import com.google.android.material.bottomsheet.BottomSheetDialog

class RecentPlayedAdapter(private val context: Context, private val songList: ArrayList<Music>) :
    RecyclerView.Adapter<RecentPlayedAdapter.MyViewHolder>() {
    class MyViewHolder(binding: RvSongItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val songName = binding.songTitle
        val more = binding.popupMenu
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPlayedAdapter.MyViewHolder {
        return MyViewHolder(RvSongItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecentPlayedAdapter.MyViewHolder, position: Int) {
        holder.songName.text = songList[position].title


        holder.root.setOnClickListener {
            val intent = Intent(context, FolderActivity::class.java)
            intent.putExtra("title", songList[position].title)
            intent.putExtra("index", position)
            context.startActivity(intent)
        }


        holder.more.setOnClickListener {

            val bottomSheetDialog = BottomSheetDialog(context)
            bottomSheetDialog.setContentView(R.layout.folder_bottom_sheet)

            val folderName = bottomSheetDialog.findViewById<TextView>(R.id.folder_name)
            folderName!!.text = songList[position].title
            val playLayout = bottomSheetDialog.findViewById<LinearLayout>(R.id.play_layout)!!
                .setOnClickListener {

                    bottomSheetDialog.dismiss()
                }
            val playNextLayout =
                bottomSheetDialog.findViewById<LinearLayout>(R.id.play_next_layout)!!
                    .setOnClickListener {
                        bottomSheetDialog.dismiss()
                    }
            val addToqQueue = bottomSheetDialog.findViewById<LinearLayout>(R.id.add_to_queue)!!
                .setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
            val addToPlaylist = bottomSheetDialog.findViewById<LinearLayout>(R.id.add_to_playlist)!!
                .setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
            bottomSheetDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }
}