package com.example.s.player.n.activity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s.player.R
import com.example.s.player.databinding.FolderViewBinding
import com.example.s.player.n.activity.activities.FolderActivity
import com.example.s.player.n.activity.models.Folder
import com.google.android.material.bottomsheet.BottomSheetDialog

class FolderAdapter(private val context: Context, private val folderList: ArrayList<Folder>) :
    RecyclerView.Adapter<FolderAdapter.MyViewHolder>() {
    class MyViewHolder(binding: FolderViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val folderName = binding.folderName
        val folderPath = binding.folderPath
        val more = binding.folderMore
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderAdapter.MyViewHolder {
        return MyViewHolder(FolderViewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: FolderAdapter.MyViewHolder, position: Int) {
        holder.folderName.text = folderList[position].name


        holder.root.setOnClickListener {
            val intent = Intent(context, FolderActivity::class.java)
            intent.putExtra("title", folderList[position].name)
            intent.putExtra("index", position)
            context.startActivity(intent)
        }


        holder.more.setOnClickListener {

            val bottomSheetDialog = BottomSheetDialog(context)
            bottomSheetDialog.setContentView(R.layout.folder_bottom_sheet)

            val folderName = bottomSheetDialog.findViewById<TextView>(R.id.folder_name)
            folderName!!.text = folderList[position].name
            val playLayout = bottomSheetDialog.findViewById<LinearLayout>(R.id.play_layout)!!
                .setOnClickListener {

                    bottomSheetDialog.dismiss()
                }
            val playNextLayout =
                bottomSheetDialog.findViewById<LinearLayout>(R.id.play_next_layout)!!
                    .setOnClickListener {
                        bottomSheetDialog.dismiss()
                    }
            val addToqQueue = bottomSheetDialog.findViewById<LinearLayout>(R.id.add_to_queue)!!
                .setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
            val addToPlaylist = bottomSheetDialog.findViewById<LinearLayout>(R.id.add_to_playlist)!!
                .setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
            bottomSheetDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return folderList.size
    }
}