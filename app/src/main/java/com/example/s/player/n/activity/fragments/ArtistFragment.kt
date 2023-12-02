package com.example.s.player.n.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s.player.R
import com.example.s.player.databinding.FragmentArtistBinding
import com.example.s.player.n.activity.adapters.ArtistAdapter

class ArtistFragment : Fragment() {
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding.inflate(inflater, container, false)
        val items = ArrayList<String>()
        items.add("Song1")
        items.add("Song2")
        items.add("Song3")
        items.add("Song4")
        items.add("Song4")
        items.add("Song5")
        items.add("Song5")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        items.add("Song1")
        val artistAdapter = ArtistAdapter(binding.root.context, items)
        binding.artistRV.adapter = artistAdapter
        binding.artistRV.layoutManager = LinearLayoutManager(binding.root.context)
        binding.totalSong.text = null

        return binding.root
    }

}