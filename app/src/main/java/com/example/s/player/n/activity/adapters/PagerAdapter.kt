package com.example.s.player.n.activity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.s.player.n.activity.fragments.AlbumFragment
import com.example.s.player.n.activity.fragments.ArtistFragment
import com.example.s.player.n.activity.fragments.FavouriteFragment
import com.example.s.player.n.activity.fragments.FolderFragment
import com.example.s.player.n.activity.fragments.PlaylistFragment
import com.example.s.player.n.activity.fragments.SongFragment

class PagerAdapter(fragment: FragmentManager) : FragmentPagerAdapter(fragment) {


    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SongFragment()
            }

            1 -> {
                PlaylistFragment()
            }

            2 -> {
                FolderFragment()
            }

            3 -> {
                AlbumFragment()
            }

            4 -> {
                ArtistFragment()
            }

            else -> FavouriteFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "SONG"
            }

            1 -> {
                return "PLAYLIST"
            }

            2 -> {
                return "FOLDER"
            }

            3 -> {
                return "ALBUM"
            }

            4 -> {
                return "ARTIST"
            }

            5 -> {
                return "FAVOURITE"
            }
        }
        return super.getPageTitle(position)
    }
}