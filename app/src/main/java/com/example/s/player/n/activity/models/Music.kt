package com.example.s.player.n.activity.models

import com.example.s.player.n.activity.activities.PlayerActivity
import com.example.s.player.n.activity.fragments.FavouriteFragment
import java.time.Duration
import java.util.concurrent.TimeUnit

open class Music(
    val id: String,
    val title: String,
    val album: String,
    val artist: String,
    val size: String,
    open val data: String,
    val duration: Long = 0,
    val path: String
) {

}

class Playlist {
    lateinit var name: String
    lateinit var playlist: ArrayList<Music>
    lateinit var createdBy: String
    lateinit var createdOn: String
}

class MusicPlaylist {
    var ref: ArrayList<Playlist> = ArrayList()
}

fun musicFormatDuration(duration: Long): String {
    val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MINUTES)
    val seconds = (TimeUnit.SECONDS.convert(
        duration,
        TimeUnit.SECONDS
    ) - minutes + TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES))
    return String.format("%82d:%2d", minutes, seconds)
}

fun favouriteChecker(id: String): Int {
    PlayerActivity.isFavourite = false
    FavouriteFragment.fabSongs.forEachIndexed { index, music ->
        if (id == music.id) {
            PlayerActivity.isFavourite = true
            return index
        }
    }
    return -1

}
fun checkPlaylist() {

}