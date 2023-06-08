package com.example.placele

data class JobPostingResponse(
    val company_posting_array: List<List<JobPosting>>,
    val comapny_name_list: List<String>
)

data class JobPosting(
    val job_title: String,
    val job_location: String,
    val job_link: String
)
