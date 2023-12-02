package com.example.s.player.n.activity.fragments

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s.player.R
import com.example.s.player.databinding.AddPlaylistDialogBinding
import com.example.s.player.databinding.DialogPlaylistBinding
import com.example.s.player.databinding.FragmentPlaylistBinding
import com.example.s.player.databinding.HeaderLayoutBinding
import com.example.s.player.n.activity.activities.MainActivity
import com.example.s.player.n.activity.activities.SettingsActivity
import com.example.s.player.n.activity.activities.ThemeActivity
import com.example.s.player.n.activity.adapters.PlaylistAdapter
import com.example.s.player.n.activity.adapters.TabsAdapters
import com.example.s.player.n.activity.models.MusicPlaylist
import com.example.s.player.n.activity.models.Playlist
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.Locale

class

PlaylistFragment : Fragment() {
    private lateinit var _binding: FragmentPlaylistBinding
    private val binding get() = _binding
    lateinit var playListAdapter: PlaylistAdapter

    companion object {
        var musicPlaylist: MusicPlaylist = MusicPlaylist()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        playListAdapter = PlaylistAdapter(requireContext(), musicPlaylist.ref)
        binding.playlistRV.adapter = playListAdapter
        binding.playlistRV.layoutManager = LinearLayoutManager(requireContext())

        val editText = EditText(requireContext())
        editText.hint = "Enter Playlist Name"
//        binding.createNewPlaylist.setOnClickListener {
//            val alertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
//            alertDialogBuilder.setTitle("Create New Playlist")
//            alertDialogBuilder.setView(editText)
//            alertDialogBuilder.create().show()
//            alertDialogBuilder.setPositiveButton("Ok") { d, i -> d.dismiss() }
//            alertDialogBuilder.setNegativeButton("Cancel") { d, i -> d.dismiss() }
//        }

        binding.createNewPlaylist.setOnClickListener {
            customAlertDialog()
        }
        binding.mostPlayed.setOnClickListener {  }
        binding.recentPlayed.setOnClickListener {  }
        return binding.root
    }

    private fun customAlertDialog() {
        val customDialog = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_playlist, binding.root, false)
        val binder = DialogPlaylistBinding.bind(customDialog)
        val builder = MaterialAlertDialogBuilder(requireContext())
        val dialog = builder.setView(customDialog)
            .setTitle("New Playlist").setNegativeButton("Cancel") { di, _ ->
                di.dismiss()
            }
            .setPositiveButton("Create") { dialog, _ ->
                val playlistName = binder.playlistName.text
                if (playlistName != null)
                    if (playlistName.isNotEmpty()) {
                        addPlaylist(playlistName.toString())
                        Toast.makeText(
                            requireContext(),
                            playlistName.toString() + "is created",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                dialog.dismiss()
            }.create()
        dialog.show()

    }

    private fun addPlaylist(name: String) {
        var playlistExists = false
        for (i in musicPlaylist.ref) {
            if (name == i.name) {
                playlistExists = true
                break
            }
        }
        if (playlistExists) Toast.makeText(requireContext(), "Playlist Exist!!", Toast.LENGTH_SHORT)
            .show()
        else {
            val tempPlaylist = Playlist()
            tempPlaylist.name = name
            tempPlaylist.playlist = ArrayList()
            val calendar = Calendar.getInstance().time
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            tempPlaylist.createdOn = sdf.format(calendar)
            musicPlaylist.ref.add(tempPlaylist)
            playListAdapter.refreshPlaylist()
        }
    }

    override fun onResume() {
        super.onResume()
        val editor =
            requireContext().getSharedPreferences("pref", AppCompatActivity.MODE_PRIVATE).edit()
//        val jsonString = GsonBuilder().create().toJson(FavouriteFragment.fabSongs)
//        editor.putString("jsonString", jsonString)
        val jsonStringPlaylist = GsonBuilder().create().toJson(musicPlaylist)
        editor.putString("jsonStringPlaylist", jsonStringPlaylist)
        editor.apply()
    }
}