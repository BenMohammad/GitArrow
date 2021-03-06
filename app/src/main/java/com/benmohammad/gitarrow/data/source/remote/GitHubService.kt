package com.benmohammad.gitarrow.data.source.remote

import com.benmohammad.gitarrow.data.RepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GitHubService {

    @Headers("Accept: application/vnd.github.v3.full+json")
    @GET("orgs/{name}/repos")
    fun getRepos(@Path("name") org: String): Call<List<RepoModel>>
}