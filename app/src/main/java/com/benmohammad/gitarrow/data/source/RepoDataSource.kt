package com.benmohammad.gitarrow.data.source

import com.benmohammad.gitarrow.data.RepoModel

interface RepoDataSource {

    interface RepoCallback {
        fun onLoaded(repoList: List<RepoModel>)
        fun onDataNotAvailable()
    }

    fun getRepos(repoCallback: RepoCallback)
    fun saveRepo(repo: RepoModel)
    fun refreshRepos()
    fun deleteAllRepos()
}