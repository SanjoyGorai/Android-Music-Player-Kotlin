package com.example.s.player.n.activity.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaScannerConnection
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.s.player.R
import com.example.s.player.databinding.FragmentSongBinding
import com.example.s.player.databinding.RvSongItemBinding
import com.example.s.player.databinding.SongGridViewBinding
import com.example.s.player.n.activity.SongDetailDialog
import com.example.s.player.n.activity.activities.MainActivity
import com.example.s.player.n.activity.activities.PlayerActivity
import com.example.s.player.n.activity.activities.PlayerActivity.Companion.songPosition
import com.example.s.player.n.activity.activities.SettingsActivity
import com.example.s.player.n.activity.fragments.FavouriteFragment
import com.example.s.player.n.activity.fragments.SongFragment
import com.example.s.player.n.activity.models.Music
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File


class MusicAdapter(private val context: Context?, private var musicList: ArrayList<Music>) :
    RecyclerView.Adapter<MusicAdapter.MyHolder>() {
    class MyHolder(binding: RvSongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val songTitle = binding.songTitle
        val artisImg = binding.songAristImg
        val songSize = binding.songSize
        val menuMore = binding.popupMenu
        val visualizer = binding.visualizer
        val checkbox = binding.checkbox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(RvSongItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.songTitle.text = musicList[position].title
        holder.songSize.text = musicList[position].size

        if (PlayerActivity.isPlaying) {
            holder.visualizer.setColor(Color.BLUE)
            holder.visualizer.visibility = View.VISIBLE
        } else {
            holder.visualizer.visibility = View.GONE
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(context, PlayerActivity::class.java)
            val title = "title"
            val size = "size"
            intent.putExtra("index", position)
            intent.putExtra("class", "MusicAdapter")
            intent.putExtra(title, musicList[position].title)
            intent.putExtra(size, musicList[position].size)
//            context?.
//
//            startActivity(intent)
            startActivity(context!!, intent, null)

//            Animatoo.animateSlideLeft(context)
        }

        holder.menuMore.setOnClickListener {
            val dialog = BottomSheetDialog(context!!)
            dialog.setContentView(R.layout.song_bottom_sheet_view)

            val songName = dialog.findViewById<TextView>(R.id.songTitle)
            songName!!.text = musicList[position].title

            val favourite = dialog.findViewById<ImageView>(R.id.favourite)
            val playNext = dialog.findViewById<LinearLayout>(R.id.play_next_layout)
            val addQueue = dialog.findViewById<LinearLayout>(R.id.add_to_queue)
            val details = dialog.findViewById<LinearLayout>(R.id.details)
            val rename = dialog.findViewById<LinearLayout>(R.id.rename)
            val delete = dialog.findViewById<LinearLayout>(R.id.delete)
            val share = dialog.findViewById<LinearLayout>(R.id.share)
            val ringtone = dialog.findViewById<LinearLayout>(R.id.set_as_ringtone)
            favourite!!.setOnClickListener {
                if (PlayerActivity.isFavourite) {
                    favourite.setImageResource(R.drawable.love_outline)
                    PlayerActivity.isFavourite = false
//                    FavouriteFragment.fabSongs.removeAt(PlayerActivity.fIndex)
                } else {
                    favourite.setImageResource(R.drawable.heart_fill_green)
//                    PlayerActivity.binding.favourite.setImageResource(R.drawable.heart_fill_green)
                    PlayerActivity.isFavourite = true
//                    FavouriteFragment.fabSongs.add(PlayerActivity.musicListPA[songPosition])
                }

            }
            dialog.findViewById<LinearLayout>(R.id.play_next_layout)!!.setOnClickListener {
                Toast.makeText(
                    context, "Clicked", Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
            val alertDialog = MaterialAlertDialogBuilder(context)
            details!!.setOnClickListener {
                dialog.dismiss()

                SongDetailDialog.create(musicList[position]).showsDialog

//                val title = musicList[position].title
//                val duration = musicList[position].duration
//                val size = musicList[position].size
//                val artist = musicList[position].artist
//                val album = musicList[position].album
//                val location = musicList[position].path
//                val format = musicList[position].
//                alertDialog.setTitle("Details")
//                    .setMessage("$title \n $duration \n $size \n $artist \n $album \n $location")
//                    .setCancelable(true)
//                    .setPositiveButton(
//                        "ok"
//                    ) { dialogInterface, i ->
//                        dialogInterface.dismiss()
//                    }
//                    .setPositiveButton("Edit") { dialogInterface, i ->
//                        dialog.dismiss()
//                        alertDialog.setTitle("Edit")
//                            .setMessage("$title \n $duration \n $size \n $artist \n $album \n $location")
//
//                    }
//                    .create().show()
            }
            rename!!.setOnClickListener {
                val renameEText = EditText(context)
                renameEText.setText(musicList[position].title)
                renameEText.selectAll()
                alertDialog.setTitle("Rename")
                alertDialog.setView(renameEText)
                alertDialog.setPositiveButton("ok") { dialogInterface, i ->
                    Toast.makeText(context, "Rename will implement soon", Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
                alertDialog.setNegativeButton("cancel") { dialogInterface, i -> dialogInterface.dismiss() }
                    .create().show()


            }
            delete!!.setOnClickListener {
                dialog.dismiss()
                requestPermission()
                alertDialog.setTitle("Delete")
                alertDialog.setMessage("Are you sure Delete?")
                alertDialog.setPositiveButton("ok") { dialogInterface, i ->
//                    requestDeleteR(position = position)
                    val file = File(musicList[position].path)
                    if (file.exists() && file.delete()) {
                        MediaScannerConnection.scanFile(
                            context,
                            arrayOf(file.path),
                            arrayOf("video/*"),
                            null
                        )
                        MainActivity.MusicListMA.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(context, "Delete successfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Delete fail!", Toast.LENGTH_SHORT).show()
                    }
                    dialogInterface.dismiss()
                }
                alertDialog.setNegativeButton("cancel") { dialogInterface, i -> dialogInterface.dismiss() }
                    .create().show()
            }
            share!!.setOnClickListener {
                dialog.dismiss()
                alertDialog.setTitle("Share via")
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "audio/*"
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(musicList[songPosition].path))
                context.startActivity(Intent.createChooser(shareIntent, "Sharing Music File!!"))
            }

            playNext!!.setOnClickListener {
                Toast.makeText(context, "Play next", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            addQueue!!.setOnClickListener {
                Toast.makeText(context, "Add Queue", Toast.LENGTH_SHORT).show()
                dialog.show()
            }
            ringtone!!.setOnClickListener {
                setAsRingtone()
            }

            dialog.show()
        }

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(sList: ArrayList<Music>) {
        musicList = ArrayList()
        musicList.addAll(sList)
        notifyDataSetChanged()

    }

    private fun requestDeleteR(position: Int) {
        //list of videos to delete
        val uriList: List<Uri> = listOf(
            Uri.withAppendedPath(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                musicList[position].id
            )
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            //requesting for delete permission
            val pi = MediaStore.createDeleteRequest(context!!.contentResolver, uriList)
            (context as Activity).startIntentSenderForResult(
                pi.intentSender, 123,
                null, 0, 0, 0, null
            )
        } else {
            //for devices less than android 11
            val file = File(musicList[position].path)
            val builder = MaterialAlertDialogBuilder(context!!)
            builder.setTitle("Delete Video?")
                .setMessage(musicList[position].title)
                .setPositiveButton("Yes") { self, _ ->
                    if (file.exists() && file.delete()) {
                        MediaScannerConnection.scanFile(context, arrayOf(file.path), null, null)
                        updateDeleteUI(position = position)
                    }
                    self.dismiss()
                }
                .setNegativeButton("No") { self, _ -> self.dismiss() }
            val delDialog = builder.create()
            delDialog.show()
            delDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(
                MaterialColors.getColor(context, R.attr.themeColor, Color.RED)
            )
            delDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(
                MaterialColors.getColor(context, R.attr.themeColor, Color.RED)
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateDeleteUI(position: Int) {
        when {
            MainActivity.search -> {
//                MainActivity.dataChanged = true
//                MainActivity. = getAllVideos(context)
                musicList.removeAt(position)
                notifyDataSetChanged()
            }
//            isFolder -> {
////                MainActivity.dataChanged = true
//                MainActivity.videoList = getAllVideos(context)
//                FoldersActivity.currentFolderVideos.removeAt(position)
//                notifyDataSetChanged()
//            }
//            else -> {
//                MainActivity.videoList.removeAt(position)
//                notifyDataSetChanged()
//            }
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse("package:${context!!.applicationContext.packageName}")
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    private fun setAsRingtone() {

        if (Settings.System.canWrite(context)) {
            val uri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 2L
            )
            RingtoneManager.setActualDefaultRingtoneUri(
                context,
                RingtoneManager.TYPE_RINGTONE, uri
            )
            Toast.makeText(context, "Ringtone set successful!", Toast.LENGTH_SHORT).show()

        } else Toast.makeText(context, "Set fail!", Toast.LENGTH_SHORT).show()
    }
}



