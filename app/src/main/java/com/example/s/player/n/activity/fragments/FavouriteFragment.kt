package com.example.s.player.n.activity.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s.player.R
import com.example.s.player.databinding.FragmentArtistBinding
import com.example.s.player.databinding.FragmentFavourateBinding
import com.example.s.player.n.activity.adapters.FavouriteAdapter
import com.example.s.player.n.activity.models.Music

class FavouriteFragment : Fragment() {
    companion object {
        var fabSongs: ArrayList<Music> = ArrayList()
    }

    private var _binding: FragmentFavourateBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavourateBinding.inflate(inflater, container, false)

        val adapter = FavouriteAdapter(requireContext(), fabSongs)
        if (fabSongs.size > 0) {
            binding.favouriteRV.adapter = adapter
            binding.favouriteRV.layoutManager = LinearLayoutManager(requireContext())
            binding.favouriteRV.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        } else {
            if (binding.radioLayout.visibility == View.GONE) {
                binding.radioLayout.visibility = View.VISIBLE
            }
        }



        return binding.root
    }

}