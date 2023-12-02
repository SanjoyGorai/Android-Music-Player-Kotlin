package com.example.s.player.n.activity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.R
import com.example.s.player.databinding.ActivityQueueBinding
import com.example.s.player.n.activity.adapters.MusicAdapter
import com.example.s.player.n.activity.adapters.QueueMusicAdapter
import java.util.Collections

class QueueActivity : AppCompatActivity() {
    lateinit var binding: ActivityQueueBinding
    lateinit var musicAdapter: QueueMusicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQueueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Queue Playing"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        musicAdapter = QueueMusicAdapter(this, MainActivity.MusicListMA)
        binding.queueRv.adapter = musicAdapter
        binding.queueRv.layoutManager = LinearLayoutManager(this)
        binding.queueRv.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchHelper)
        itemTouchHelper.attachToRecyclerView(binding.queueRv)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 0) {
            finish()
        }
        return super.onOptionsItemSelected(item)
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
}