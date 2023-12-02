package com.example.s.player.n.activity.activities

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s.player.databinding.ActivityFolderBinding
import com.example.s.player.n.activity.adapters.MusicAdapter
import com.example.s.player.n.activity.models.Music
import java.io.File

class FolderActivity : AppCompatActivity() {

    companion object {
        lateinit var currentFolderVideos: ArrayList<Music>
    }

    lateinit var binding: ActivityFolderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val title = intent.getStringExtra("title")
        supportActionBar?.title = title

        val position = intent.getIntExtra("index", 0)
        currentFolderVideos = getAllAudio(MainActivity.folderList[position].id)
        val musicAdapter = MusicAdapter(this, currentFolderVideos)
        binding.folderRv.layoutManager = LinearLayoutManager(this)
        binding.folderRv.adapter = musicAdapter


    }

    @SuppressLint("Range")
    private fun getAllAudio(folderId: String): ArrayList<Music> {
        val tempList = ArrayList<Music>()
        val tempFolderList = ArrayList<String>()
//        val selection: String = MediaStore.Audio.Media.IS_MUSIC + " !=0"
        val selection: String = MediaStore.Audio.Media.BUCKET_ID + " like? "
        val sortOrder: String = MediaStore.Audio.Media.DATE_ADDED + " DESC"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Audio.Media.BUCKET_ID,
        )
        val cursor: Cursor? = this.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            arrayOf(folderId),
            sortOrder, null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val _title =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val _bucket_name =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.BUCKET_DISPLAY_NAME))
//                    val _bucket_id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.BUCKET_ID))
                    val _album =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val _artist =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val _size = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                    val _duration =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    val _path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val music = Music(
                        id = _id,
                        title = _title,
                        album = _album,
                        artist = _artist,
                        size = _size,
                        path = _path,
                        duration = _duration, data = _path
                    )
                    try {
                        val file = File(music.path)
                        if (file.exists()) {
                            tempList.add(music)
                        } else Toast.makeText(this, "File is no exists", Toast.LENGTH_SHORT).show()
                    } catch (_: Exception) {
                    }

                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return tempList
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 0) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}