package com.example.placele

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread

import androidx.recyclerview.widget.RecyclerView

class JobPostingAdapter(private val jobPostings: List<List<JobPosting>>) :
    RecyclerView.Adapter<JobPostingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textJobTitle: TextView = itemView.findViewById(R.id.text_job_title)
        val textJobLocation: TextView = itemView.findViewById(R.id.text_job_location)
        val registerButton: Button = itemView.findViewById(R.id.btn_job_link)
        // Other views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_job_posting, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("kichooo","sizee"+jobPostings[position].size.toString())
//        var sizee=jobPostings[position].size
//        if(jobPostings[position].size!=0){
//            for(i in 0..(sizee-1)){
                val jobPosting = jobPostings[position]
//                Log.i("kichooo",jobPosting.toString())
                holder.textJobTitle.text = jobPosting[position].job_title
                holder.textJobLocation.text = jobPosting[position].job_location
//                Log.i("kichooo",jobPosting[position].job_link)
                holder.registerButton.setOnClickListener {
//                    Log.i("kichooo",position.toString())
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(jobPosting[position].job_link)
                    holder.itemView.context.startActivity(intent)

//            }

//        }

//            startActivity(intent)
        }


        // Bind other views here
    }

    override fun getItemCount(): Int {
        Log.i("kichooo",jobPostings.size.toString())

        return jobPostings.size
    }
}
