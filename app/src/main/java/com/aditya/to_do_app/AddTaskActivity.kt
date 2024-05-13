package com.aditya.to_do_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.aditya.to_do_app.databinding.ActivityAddTaskBinding
import com.aditya.to_do_app.room.Note
import com.aditya.to_do_app.room.noteViewModel
import kotlinx.coroutines.processNextEventInCurrentThread
import java.util.Date

class AddTaskActivity : AppCompatActivity() {
    val viewModel: noteViewModel by viewModels()
    private var priority="1"
    lateinit var binding: ActivityAddTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        changeStatusBarColor()

        val mode = intent.getStringExtra("mode")
        val heading = intent.getStringExtra("title")
        val body = intent.getStringExtra("data")
        val id = intent.getStringExtra("myId")

        binding.delBtn.visibility = View.GONE

        if (mode == "update") {
            binding.delBtn.visibility = View.VISIBLE

        }

        binding.delBtn.setOnClickListener {
            deleteData(id)
        }




    binding.lowBtn.setOnClickListener {
        priority="1"
        binding.lowBtn.setImageResource(R.drawable.tick)
        binding.mediumBtn.setImageResource(0)
        binding.highBtn.setImageResource(0)

    }
    binding.mediumBtn.setOnClickListener {
        binding.mediumBtn.setImageResource(0)
        binding.mediumBtn.setImageResource(R.drawable.tick)
        binding.highBtn.setImageResource(0)
        priority="2"
    }
    binding.highBtn.setOnClickListener {
        binding.lowBtn.setImageResource(0)
        binding.mediumBtn.setImageResource(0)
        binding.highBtn.setImageResource(R.drawable.tick)
      priority="3"
    }

        binding.titleData.setText(heading)
        binding.mainData.setText(body)

        val d = Date()
        val date: CharSequence = android.text.format.DateFormat.format("MMM d, yyyy", d.time)
        binding.myDate.text = date.toString()

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
        binding.btn.setOnClickListener {
            createNote(it)
        }


    }

    private fun deleteData(id: String?) {

        viewModel.deleteNote(id!!.toInt())
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))

    }

    private fun createNote(view: View) {

        val id = intent.getStringExtra("myId")
        val mode = intent.getStringExtra("mode")
        if (mode == "update") {

            val title = binding.titleData.text.toString()
            val data = binding.mainData.text.toString()
            val d = Date()
            val date: CharSequence = android.text.format.DateFormat.format("MMM d, yyyy", d.time)

            val note = Note(
                id = id!!.toInt(),
                title = title,
                data = data,
                date = date.toString(),
                priority = priority
            )
            viewModel.updateNote(note)
        } else {


            val title = binding.titleData.text.toString()
            val data = binding.mainData.text.toString()
            val d = Date()

            val date: CharSequence = android.text.format.DateFormat.format("MMM d, yyyy", d.time)

            if (title.isNotBlank() || date.isNotBlank()) {
                val note =
                    Note(null, title = title, data = data, date = date.toString(), priority = priority)
                viewModel.addNote(note)
            } else {
                Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show()
            }


        }



        Toast.makeText(this, "Create Done", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))


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