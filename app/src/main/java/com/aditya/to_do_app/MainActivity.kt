package com.aditya.to_do_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aditya.to_do_app.databinding.ActivityMainBinding
import com.aditya.to_do_app.room.noteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val viewModel: noteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        changeStatusBarColor()


        binding.allBtn.setOnClickListener {

            viewModel.getNotes().observe(this) { noteList ->

                binding.myRec.layoutManager =     StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.myRec.adapter = myAdapter(this, noteList)

                if (noteList.isEmpty()) {
                    binding.emptyImg.visibility = View.VISIBLE
                } else {
                    binding.emptyImg.visibility = View.GONE
                }

                binding.pendingTaskText.text = "Here is ${noteList.size} Tasks"

            }

        }
        binding.lowBtn.setOnClickListener {
            viewModel.getLowNotes().observe(this) { noteList ->

                binding.myRec.layoutManager =     StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.myRec.adapter = myAdapter(this, noteList)

                if (noteList.isEmpty()) {
                    binding.emptyImg.visibility = View.VISIBLE
                } else {
                    binding.emptyImg.visibility = View.GONE
                }

                binding.pendingTaskText.text = "Here is ${noteList.size} Tasks"

            }
        }
        binding.mediumBtn.setOnClickListener {
            viewModel.getMediumNote().observe(this) { noteList ->

                binding.myRec.layoutManager =     StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.myRec.adapter = myAdapter(this, noteList)

                if (noteList.isEmpty()) {
                    binding.emptyImg.visibility = View.VISIBLE
                } else {
                    binding.emptyImg.visibility = View.GONE
                }

                binding.pendingTaskText.text = "Here is ${noteList.size} Tasks"

            }
        }
        binding.highBtn.setOnClickListener {
            viewModel.getHighNote().observe(this) { noteList ->

                binding.myRec.layoutManager =     StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.myRec.adapter = myAdapter(this, noteList)

                if (noteList.isEmpty()) {
                    binding.emptyImg.visibility = View.VISIBLE
                } else {
                    binding.emptyImg.visibility = View.GONE
                }

                binding.pendingTaskText.text = "Here is ${noteList.size} Tasks"

            }
        }



 viewModel.getNotes().observe(this) { noteList ->

     binding.myRec.layoutManager =     StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
     binding.myRec.adapter = myAdapter(this, noteList)

     if (noteList.isEmpty()) {
         binding.emptyImg.visibility = View.VISIBLE
     } else {
         binding.emptyImg.visibility = View.GONE
     }

     binding.pendingTaskText.text = "Here is ${noteList.size} Tasks"

 }

        binding.addBnt.setOnClickListener {
            startActivity(Intent(this,AddTaskActivity::class.java))
        }


    }

    private fun changeStatusBarColor() {
        val window = this.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        window?.decorView?.let {
            WindowCompat.getInsetsController(window, it).apply {
                isAppearanceLightStatusBars = true
            }
        }
    }


}