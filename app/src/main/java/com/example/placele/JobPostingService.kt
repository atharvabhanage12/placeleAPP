package com.example.placele

import retrofit2.http.GET

interface JobPostingService {
    @GET("receive-data")
    suspend fun getJobPostings(): JobPostingResponse
}

