package com.aditya.to_do_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.aditya.to_do_app.room.Note
import com.aditya.to_do_app.room.noteViewModel

class myAdapter(val mainActivity: MainActivity, val noteList: List<Note>) : RecyclerView.Adapter<myAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val data=itemView.findViewById<TextView>(R.id.textData)
        val title=itemView.findViewById<TextView>(R.id.textTitle)
        val date=itemView.findViewById<TextView>(R.id.myDate)
        val layout=itemView.findViewById<RelativeLayout>(R.id.layout)
        val preority=itemView.findViewById<View>(R.id.myPreority)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        )
    }

    override fun getItemCount(): Int {
     return noteList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
       holder.data.text=noteList[position].data
        holder.title.text=noteList[position].title
        holder.date.text=noteList[position].date

        when(noteList[position].priority){
            "1"->{holder.preority.setBackgroundResource(R.drawable.green_gola)}
            "2"->{holder.preority.setBackgroundResource(R.drawable.pila_gola)}
            "3"->{holder.preority.setBackgroundResource(R.drawable.shape_priority)}
        }


        holder.itemView.setOnClickListener {

            val i=Intent(mainActivity,AddTaskActivity::class.java)
           i.putExtra("title",noteList[position].title)
            i.putExtra("data",noteList[position].data)
            i.putExtra("myId",noteList[position].id.toString())
            i.putExtra("mode","update")
              mainActivity.startActivity(i)

        }




    }
}