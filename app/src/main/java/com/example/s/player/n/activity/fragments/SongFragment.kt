package com.example.s.player.n.activity.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.s.player.R
import com.example.s.player.databinding.FragmentSongBinding
import com.example.s.player.n.activity.activities.MainActivity
import com.example.s.player.n.activity.activities.PlayerActivity.Companion.binding
import com.example.s.player.n.activity.adapters.MusicAdapter
import com.example.s.player.n.activity.models.Music
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.trendyol.bubblescrollbarlib.BubbleScrollBar
import me.zhanghai.android.fastscroll.FastScroller
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.File
import java.util.Collections

class SongFragment : Fragment() {

    private var _binding: FragmentSongBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("StaticFieldLeak")
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var MusicListMA: ArrayList<Music>

    companion object {
        var isList = false
    }

    @SuppressLint("UseRequireInsteadOfGet", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        val swipeRefreshLayout = SwipeRefreshLayout(requireContext())
        swipeRefreshLayout.setOnRefreshListener {
            musicAdapter = MusicAdapter(context, MainActivity.MusicListMA)
            Toast.makeText(view!!.context, "Swipe Songs", Toast.LENGTH_SHORT).show()
        }
        swipeRefreshLayout.isRefreshing = false

        musicAdapter = MusicAdapter(context, MainActivity.MusicListMA)
        if (MainActivity.MusicListMA.size > 0) {
            binding.emptyEmoji.visibility = View.GONE
            binding.emptyText.visibility = View.GONE
        } else {
            binding.musicRV.visibility = View.GONE
        }
        binding.musicRV.adapter = musicAdapter
        binding.musicRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.musicRV.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        binding.fastScroller.setRecyclerView(binding.musicRV)

        binding.totalSong.text = MainActivity.MusicListMA.size.toString() + " Songs "
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchHelper)
        itemTouchHelper.attachToRecyclerView(binding.musicRV)
//        FastScrollerBuilder(binding.musicRV).build()
        val scrollBar = BubbleScrollBar(requireContext())
        scrollBar.attachToRecyclerView(binding.musicRV)

        binding.listGrid.setOnClickListener {
               startActivity( Intent(activity, AlbumDetailsFragment::class.java))
            if (isList) {
                binding.listGrid.setImageResource(R.drawable.list)
                binding.musicRV.layoutManager = GridLayoutManager(requireContext(), 2)
                isList = false
            } else {
                binding.listGrid.setImageResource(R.drawable.grid)
                binding.musicRV.layoutManager = LinearLayoutManager(requireContext())
                isList = true
            }

        }


        return binding.root
    }

    @SuppressLint("Range")
    private fun getAllAudio(): ArrayList<Music> {
        val tempList = ArrayList<Music>()
        val selection: String = MediaStore.Audio.Media.IS_MUSIC + " !=0"
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
        )
        val cursor: Cursor? = context?.contentResolver?.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder,
            null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val _title =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
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
                    val file = File(music.path)
                    if (file.exists()) {
                        tempList.add(music)
                    } else Toast.makeText(context, "File is no exists", Toast.LENGTH_SHORT).show()
                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return tempList
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        menu.clear()
        inflater.inflate(R.menu.song_fragment_menu, menu)
//        val searchView =
//            menu.findItem(R.id.frag_action_search)?.actionView as androidx.appcompat.widget.SearchView
//        searchView.setOnQueryTextListener(object :
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("Not yet implemented")
//                return true
//            }

//            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText != null) {
//                    for (music in MainActivity.MusicListMA) {
//                        if (music.title.lowercase().contains(newText.lowercase()))
//                            MainActivity.searchList.add(music)
//                    }
//                    MainActivity.search = true
//                    musicAdapter.updateList(MainActivity.searchList)
//
//                }
//                return true
//            }
//        })
//        super.onCreateOptionsMenu(menu, inflater)
    }

    private val simpleItemTouchHelper: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                Collections.swap(MainActivity.MusicListMA, fromPosition, toPosition)
                musicAdapter.notifyItemMoved(fromPosition, toPosition)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }

        }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onOptionsItemSelected(item)",
            "androidx.fragment.app.Fragment"
        )
    )
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by -> {
                val dialog = BottomSheetDialog(requireContext())
                dialog.setContentView(R.layout.sortby_bottom_view)
                dialog.create()
                dialog.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}