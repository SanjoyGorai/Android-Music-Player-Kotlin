package com.example.s.player.n.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s.player.R
import com.example.s.player.databinding.FragmentRecentPlayedBinding

class RecentPlayedFragment : Fragment() {

    lateinit var binding: FragmentRecentPlayedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentPlayedBinding.inflate(inflater, container, false)


        return binding.root
    }

}