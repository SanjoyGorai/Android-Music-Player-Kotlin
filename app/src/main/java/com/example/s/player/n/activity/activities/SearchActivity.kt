package com.example.s.player.n.activity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s.player.R
import com.example.s.player.databinding.ActivitySearchBinding
import com.example.s.player.n.activity.adapters.SearchAdapter

class SearchActivity : AppCompatActivity() {

    lateinit var searchAdapter: SearchAdapter
    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var list = ArrayList<String>()
        list.add("Song1")
        list.add("Song2")
        list.add("Searh3")
        list.add("Song4")
        list.add("Song5")
        list.add("Song6")
        list.add("Song7")
        list.add("Song7")
        list.add("Song7")
        list.add("Song7")
        list.add("Song7")
        list.add("Song7")
        list.add("Song8")
        list.add("Song9")
        list.add("Song910")
        searchAdapter = SearchAdapter(this, list)
        binding.recyclerview.adapter = searchAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView: SearchView =
            menu!!.findItem(R.id.menu_search).actionView as SearchView
        searchView.queryHint = "Search Songs"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item: MenuItem? = menu?.findItem(R.id.action_search)
//        item!!.isVisible = false
        return true
    }

}