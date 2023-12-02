package com.example.s.player.n.activity.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.R
import com.example.s.player.databinding.PlaylistViewBinding
import com.example.s.player.n.activity.activities.PlayerActivity
import com.example.s.player.n.activity.activities.playlist.PlaylistDetailsActivity
import com.example.s.player.n.activity.fragments.PlaylistFragment
import com.example.s.player.n.activity.models.Playlist
import com.google.android.material.bottomsheet.BottomSheetDialog

class PlaylistAdapter(val context: Context?, private var playlistItems: ArrayList<Playlist>) :
    RecyclerView.Adapter<PlaylistAdapter.MyHolder>() {
    class MyHolder(binding: PlaylistViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val playListName = binding.playListName

        //        val artisImg = binding.songAristImg
//        val songSize = binding.songSize
        val menuMore = binding.more

    }

    fun refreshPlaylist() {
        playlistItems = ArrayList()
        playlistItems.addAll(PlaylistFragment.musicPlaylist.ref)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            PlaylistViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.playListName.text = playlistItems[position].name
        holder.playListName.isSelected = true

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PlaylistDetailsActivity::class.java)
            intent.putExtra("title", playlistItems[position].name)
            context!!.startActivity(intent)
        }

        holder.menuMore.setOnClickListener {
            val dialog = BottomSheetDialog(context!!)
            dialog.setContentView(R.layout.playlist_items_bottomsheet)

            val title = dialog.findViewById<TextView>(R.id.playlistName)
            title!!.text = playlistItems[position].name

            val play_layout = dialog.findViewById<LinearLayout>(R.id.play_layout)
            val play_next_layout = dialog.findViewById<LinearLayout>(R.id.play_next_layout)
            val add_to_queue = dialog.findViewById<LinearLayout>(R.id.add_to_queue)
            val add_to_playlist = dialog.findViewById<LinearLayout>(R.id.add_to_playlist)
            val rename = dialog.findViewById<LinearLayout>(R.id.rename)
            val delete = dialog.findViewById<LinearLayout>(R.id.delete)

            play_layout!!.setOnClickListener {
                dialog.dismiss()
                Toast.makeText(context, "play_layout", Toast.LENGTH_SHORT).show()

            }
            play_next_layout!!.setOnClickListener {
                dialog.dismiss()
                Toast.makeText(context, "play_next_layout", Toast.LENGTH_SHORT).show()

            }
            add_to_queue!!.setOnClickListener {
                dialog.dismiss()
                Toast.makeText(context, "add_to_queue", Toast.LENGTH_SHORT).show()

            }
            add_to_playlist!!.setOnClickListener {
                dialog.dismiss()
                Toast.makeText(context, "add_to_playlist", Toast.LENGTH_SHORT).show()

            }
            delete!!.setOnClickListener {
                dialog.dismiss()
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.setTitle("Delete")
                alertDialog.setMessage("Are you sure to delete this?")
                alertDialog.setPositiveButton("ok") { di, _ ->
                        di.dismiss()
                }
                alertDialog.setNegativeButton("cancel") { di, _ ->
                    di.dismiss()

                }

                alertDialog.create().show()
            }

            dialog.create()
            dialog.show()
        }

    }

    override fun getItemCount(): Int {
        return playlistItems.size
    }
}
