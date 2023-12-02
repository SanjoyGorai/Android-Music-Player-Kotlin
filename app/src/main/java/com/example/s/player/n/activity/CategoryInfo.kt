package com.example.s.player.n.activity

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.s.player.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class CategoryInfo(
    val category: Category,
    var visible: Boolean
) : Parcelable {

    enum class Category(
        val id: Int,
        @StringRes val stringRes: Int,
        @DrawableRes val icon: Int
    ) {
        Home(R.id.action_home, R.string.for_you, R.drawable.asld_face),
        Songs(R.id.action_song, R.string.songs, R.drawable.music_note),
        Albums(R.id.action_album, R.string.albums, R.drawable.album),
        Artists(R.id.action_artist, R.string.artists, R.drawable.artist_bg),
        Playlists(R.id.action_playlist, R.string.playlists, R.drawable.playlist_add),
        Genres(R.id.action_genre, R.string.genres, R.drawable.volume_up),
        Folder(R.id.action_folder, R.string.folders, R.drawable.folder),
        Search(R.id.action_search, R.string.action_search, R.drawable.search);
    }

}

