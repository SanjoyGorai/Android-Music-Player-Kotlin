package com.example.s.player.n.activity.activities

import  android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.example.s.player.R
import com.example.s.player.SleepTimerFragment
import com.example.s.player.databinding.ActivityMainBinding
import com.example.s.player.databinding.DialogPlaylistBinding
import com.example.s.player.databinding.HeaderLayoutBinding
import com.example.s.player.databinding.SleepTimerDialogBinding
import com.example.s.player.n.activity.adapters.TabsAdapters
import com.example.s.player.n.activity.adapters.TabsAdapters.Companion.tabLists
import com.example.s.player.n.activity.fragments.AlbumFragment
import com.example.s.player.n.activity.fragments.ArtistFragment
import com.example.s.player.n.activity.fragments.FavouriteFragment
import com.example.s.player.n.activity.fragments.FolderFragment
import com.example.s.player.n.activity.fragments.PlaylistFragment
import com.example.s.player.n.activity.fragments.SongFragment
import com.example.s.player.n.activity.models.Album
import com.example.s.player.n.activity.models.Folder
import com.example.s.player.n.activity.models.Music
import com.example.s.player.n.activity.models.MusicPlaylist
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val PERMISSION_REQUEST_CODE = 200

    companion object {
        lateinit var MusicListMA: ArrayList<Music>
        lateinit var folderList: ArrayList<Folder>
        lateinit var albumList: ArrayList<Album>
        lateinit var albumLists: ArrayList<Folder>
        lateinit var searchList: ArrayList<Music>
        var search: Boolean = false
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)
        permissions()
        val toolbar = binding.toolbar
        val navigationView: NavigationView = binding.navigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        setSupportActionBar(toolbar)
        init()
        FavouriteFragment.fabSongs = ArrayList()
        val editor = getSharedPreferences("pref", MODE_PRIVATE)
        val jsonString = editor.getString("jsonString", null)
        val token = object : TypeToken<ArrayList<Music>>() {}.type
        if (jsonString != null) {
            val data: ArrayList<Music> = GsonBuilder().create().fromJson(jsonString, token)
            FavouriteFragment.fabSongs.addAll(data)
        }

        PlaylistFragment.musicPlaylist = MusicPlaylist()
        val jsonStringPlaylist = editor.getString("jsonStringPlaylist", null)
        if (jsonStringPlaylist != null) {
            val dataPlaylist: MusicPlaylist =
                GsonBuilder().create().fromJson(jsonStringPlaylist, MusicPlaylist::class.java)
            PlaylistFragment.musicPlaylist = dataPlaylist


            val headerLayoutBinding = HeaderLayoutBinding.inflate(LayoutInflater.from(this))
            headerLayoutBinding.songName.text = " I am song"


            val toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            folderList = ArrayList()
            albumList = ArrayList()
            MusicListMA = getAllAudio()
            searchList = ArrayList()

            tabsLayoutInit();


        }

        val headerLayoutBinding = HeaderLayoutBinding.inflate(LayoutInflater.from(this))
        headerLayoutBinding.songName.text = " I am song"


        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        folderList = ArrayList()
        albumList = ArrayList()
        MusicListMA = getAllAudio()
        searchList = ArrayList()

        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.go_pro -> Toast.makeText(this, item.itemId.toString(), Toast.LENGTH_SHORT)
                    .show()

                R.id.nav_drive_mode -> Toast.makeText(
                    this,
                    item.itemId.toString(),
                    Toast.LENGTH_SHORT
                ).show()

                R.id.equalizer -> Toast.makeText(this, item.itemId.toString(), Toast.LENGTH_SHORT)
                    .show()

                R.id.sleep_timer -> {
                    customAlertDialog()
//                    Toast.makeText(this, item.itemId.toString(), Toast.LENGTH_SHORT)
//                        .show()
                }

                R.id.nav_theme -> startActivity(Intent(this, ThemeActivity::class.java))

                R.id.nav_settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }

                R.id.nav_privacy_policy -> Toast.makeText(
                    this,
                    item.itemId.toString(),
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_rate -> Toast.makeText(this, item.itemId, Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true

        }

    }

    private fun tabsLayoutInit() {
        tabLists.add(SongFragment())
        tabLists.add(PlaylistFragment())
        tabLists.add(FolderFragment())
        tabLists.add(AlbumFragment())
        tabLists.add(ArtistFragment())
        tabLists.add(FavouriteFragment())
        binding.viewPager.adapter = TabsAdapters(supportFragmentManager, lifecycle)
        binding.viewPager.offscreenPageLimit = 2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            binding.viewPager.setCurrentItem(tab.position, true)
            when (pos) {
                0 -> {
                    tab.text = "SONG"
                }

                1 -> {
                    tab.text = "PLAYLIST"
                }

                2 -> {
                    tab.text = "FOLDER"
                }

                3 -> {
                    tab.text = "ALBUM"
                }

                4 -> {
                    tab.text = "ARTIST"
                }

                5 -> {
                    tab.text = "FAVOURITE"
                }
            }
        }.attach()
    }

    private fun permissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.option_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.go_pro) {
//            Toast.makeText(this, item.toString() + "is clicked", Toast.LENGTH_SHORT).show()
//        }
        when (item.itemId) {

            R.id.action_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }

            R.id.go_pro -> {
                Toast.makeText(this, item.toString() + "is clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_drive_mode -> {
                startActivity(Intent(this, DriveModeActivity::class.java))
            }

            R.id.equalizer -> {
                Toast.makeText(this, item.toString() + "is clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.sleep_timer -> {
                Toast.makeText(this, item.toString() + "is clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_theme -> {
                startActivity(Intent(this, ThemeActivity::class.java))
            }

            R.id.nav_privacy_policy -> {
                Toast.makeText(this, item.toString() + "is clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_rate -> {
                Toast.makeText(this, item.toString() + "is clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {

    }

    @SuppressLint("Range")
    private fun getAllAudio(): ArrayList<Music> {
        val tempList = ArrayList<Music>()
        val tempFolderList = ArrayList<String>()
        val tempAlbumList = ArrayList<String>()
        val selection: String = Media.IS_MUSIC + " !=0"
        val sortOrder: String = Media.DATE_ADDED + " DESC"
        val projection = arrayOf(
            Media._ID,
            Media.TITLE,
            Media.ALBUM,
            Media.ARTIST,
            Media.SIZE,
            Media.DURATION,
            Media.DATE_ADDED,
            Media.DATA,
            Media.BUCKET_DISPLAY_NAME,
            Media.BUCKET_ID,
            Media.ALBUM_ID,
        )
        val cursor: Cursor? = this.contentResolver.query(
            Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder, null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val _title =
                        cursor.getString(cursor.getColumnIndex(Media.TITLE))
                    val _id = cursor.getString(cursor.getColumnIndex(Media._ID))
                    val _bucket_name =
                        cursor.getString(cursor.getColumnIndex(Media.BUCKET_DISPLAY_NAME))
                    val _bucket_id = cursor.getString(cursor.getColumnIndex(Media.BUCKET_ID))
                    val _album =
                        cursor.getString(cursor.getColumnIndex(Media.ALBUM))
                    val _albumId =
                        cursor.getString(cursor.getColumnIndex(Media.ALBUM_ID))
                    val _artist =
                        cursor.getString(cursor.getColumnIndex(Media.ARTIST))
                    val _size = cursor.getString(cursor.getColumnIndex(Media.SIZE))
                    val _duration =
                        cursor.getLong(cursor.getColumnIndex(Media.DURATION))
                    val _path = cursor.getString(cursor.getColumnIndex(Media.DATA))
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
                        if (!tempFolderList.contains(_bucket_name)) {
                            tempFolderList.add(_bucket_name)
                            folderList.add(Folder(id = _bucket_id, name = _bucket_name))
                        }
                        if (!tempAlbumList.contains(_album)) {
                            tempFolderList.add(_album)
                            albumList.add(Album(title = _album, id = _albumId))
                        }
                    } catch (_: Exception) {
                    }

                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return tempList
    }

    override fun onResume() {
        super.onResume()
        val editor = getSharedPreferences("pref", MODE_PRIVATE).edit()
        val jsonString = GsonBuilder().create().toJson(FavouriteFragment.fabSongs)
        editor.putString("jsonString", jsonString)
        val jsonStringPlaylist = GsonBuilder().create().toJson(PlaylistFragment.musicPlaylist)
        editor.putString("jsonStringPlaylist", jsonStringPlaylist)
        editor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    override fun onDestroy() {
        super.onDestroy()


    }

    private fun customAlertDialog() {
        val timerTime: TextView = findViewById(R.id.timerStartTime)
        val sharedPreferences = getSharedPreferences("pref", 0).edit()

        val customDialog = LayoutInflater.from(this)
            .inflate(R.layout.sleep_timer_dialog, binding.root, false)
        val binder = SleepTimerDialogBinding.bind(customDialog)
        binder.timerOff.setOnClickListener {
            binder.timerOff.setTextColor(Color.GREEN)
            if (timerTime.isVisible) {
                timerTime.visibility = View.INVISIBLE
            }

        }
        binder.timer10minutes.setOnClickListener {
            binder.timer10minutes.setTextColor(Color.GREEN)
            timerTime.visibility = View.VISIBLE
            timerTime.text = binder.timer10minutes.toString()

            Toast.makeText(this, "timer15minutes", Toast.LENGTH_SHORT).show()
        }
        binder.timer15minutes.setOnClickListener {
            binder.timer15minutes.setTextColor(Color.GREEN)
            timerTime.visibility = View.VISIBLE
            timerTime.text = binder.timer15minutes.toString()

            Toast.makeText(this, "timer15minutes", Toast.LENGTH_SHORT).show()
        }
        binder.timer30minutes.setOnClickListener {
            binder.timer30minutes.setTextColor(Color.GREEN)
            timerTime.visibility = View.VISIBLE

            Toast.makeText(this, "timer15minutes", Toast.LENGTH_SHORT).show()
        }
        binder.timer60minutes.setOnClickListener {
            binder.timer60minutes.setTextColor(Color.GREEN)
            timerTime.visibility = View.VISIBLE
            timerTime.text = binder.timer60minutes.toString()

            Toast.makeText(this, "timer60minutes", Toast.LENGTH_SHORT).show()
        }
        binder.timerCustom.setOnClickListener {
            Toast.makeText(this, "timerCustom", Toast.LENGTH_SHORT).show()
        }


        val builder = MaterialAlertDialogBuilder(this)
//        val builder = AlertDialog.Builder(this)
        val dialog = builder.setView(customDialog)
            .setTitle("Sleep Timer").setNegativeButton("Cancel") { di, _ ->
                di.dismiss()
            }
            .setPositiveButton("Set") { dialog, _ ->
                dialog.dismiss()
            }.create()

        dialog.show()

    }

}
