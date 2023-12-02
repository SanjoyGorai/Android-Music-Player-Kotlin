package com.example.s.player.n.activity.fragments

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s.player.R
import com.example.s.player.databinding.FragmentFolderBinding
import com.example.s.player.n.activity.activities.MainActivity
import com.example.s.player.n.activity.activities.PlayerActivity.Companion.binding
import com.example.s.player.n.activity.adapters.FolderAdapter
import com.example.s.player.n.activity.models.Folder
import com.example.s.player.n.activity.models.Music
import java.io.File

class FolderFragment : Fragment() {

    private var _binding: FragmentFolderBinding? = null
    private val binding: FragmentFolderBinding get() = _binding!!
    private lateinit var folderAdapter: FolderAdapter
    private lateinit var folderList: ArrayList<Folder>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFolderBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        folderAdapter = FolderAdapter(requireContext(), MainActivity.folderList)
        binding.folderRv.adapter = folderAdapter
        binding.folderRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    @SuppressLint("Range")
    private fun getAllAudio(): ArrayList<String> {
        val tempList = ArrayList<String>()
        val selection: String = MediaStore.Audio.Media.IS_MUSIC + " !=0"
        val sortOrder: String = MediaStore.Audio.Media.DATE_ADDED + " DESC"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Audio.Media.BUCKET_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA,
        )
        val cursor: Cursor? = context?.contentResolver?.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
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
                    val _bucket_id =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.BUCKET_ID))
                    val _album =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val _artist =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val _size = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                    val _duration =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    val _path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    if (tempList.contains(_bucket_name)) {
                        tempList.add(_bucket_name)
                        folderList.add(Folder(name = _bucket_name, id = _bucket_id))
                    } else {
//                        Toast.makeText(requireContext(), "No folder found", Toast.LENGTH_SHORT)
//                            .show()
                    }
                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return tempList
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.nav_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}