package com.example.placele

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placele.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var jobPostingService: JobPostingService
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.i("kichooo","waiting retrofit")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://skitter-adaptable-shallot.glitch.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Log.i("kichooo","got retrofit")
        jobPostingService = retrofit.create(JobPostingService::class.java)
        Log.i("kichooo","Setting in recycler view")

        // RecyclerView setup
        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)

        Log.i("kichooo","set in recycler view")

        // Retrieve and display job postings
        retrieveJobPostings()
    }

    private fun retrieveJobPostings() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val jobPostingResponse: JobPostingResponse = jobPostingService.getJobPostings()
                val jobPostings: List<List<JobPosting>> = jobPostingResponse.company_posting_array
                val adapter = JobPostingAdapter(jobPostings)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.i("kichooo",e.toString())
            }
        }
    }


}