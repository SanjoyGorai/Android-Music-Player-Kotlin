package com.example.s.player.n.activity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s.player.R
import com.example.s.player.databinding.FragmentAlbumBinding
import com.example.s.player.n.activity.activities.MainActivity
import com.example.s.player.n.activity.adapters.AlbumAdapter
import com.example.s.player.n.activity.models.Album

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    lateinit var albumList: ArrayList<String>
    lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)

        albumList = ArrayList()
        albumList.add("Albm1")
        albumList.add("Albm2")
        albumList.add("Albm3")
        albumList.add("Albm3")
        albumList.add("Albm123")
        albumList.add("Albm231")
        albumList.add("Albm231")
        albumList.add("Albm1")
        albumList.add("Albm1")
        albumList.add("Albm23")
        albumList.add("Albm23")
        albumList.add("Albm23")
        albumList.add("Albm1")
        albumList.add("Albm1")

        albumAdapter = AlbumAdapter(requireContext(), MainActivity.folderList)
        binding.albumRV.adapter = albumAdapter
        binding.albumRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.totalAlbums.text = MainActivity.folderList.size.toString() + " Albums"

        return binding.root
    }

}