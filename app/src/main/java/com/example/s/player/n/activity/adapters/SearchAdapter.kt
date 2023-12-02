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
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.s.player.R
import com.example.s.player.databinding.RvSongItemBinding
import com.example.s.player.n.activity.SongDetailDialog
import com.example.s.player.n.activity.activities.MainActivity
import com.example.s.player.n.activity.activities.PlayerActivity
import com.example.s.player.n.activity.models.Music
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File

class SearchAdapter(var context: Context?, private var searchList: ArrayList<String>) :
    RecyclerView.Adapter<SearchAdapter.MyHolder>() {
    class MyHolder(binding: RvSongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val songTitle = binding.songTitle
        val visualizer = binding.visualizer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(RvSongItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.songTitle.text = searchList[position]
        holder.songTitle.setTextColor(Color.GREEN)
        holder.visualizer.visibility = View.GONE

    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}



