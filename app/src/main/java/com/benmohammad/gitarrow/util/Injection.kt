package com.benmohammad.gitarrow.util

import android.content.Context
import com.benmohammad.gitarrow.data.source.RepoRepository
import com.benmohammad.gitarrow.data.source.local.RepoDatabase
import com.benmohammad.gitarrow.data.source.local.RepoLocalDataSource
import com.benmohammad.gitarrow.data.source.remote.RepoRemoteDataSource

object Injection {

    fun provideRepoRepository(context: Context): RepoRepository {
        val database = RepoDatabase.getInstance(context)
        return RepoRepository.getInstance(RepoRemoteDataSource.getInstance(), RepoLocalDataSource.getInstance(
            AppExecutors(), database.repoDao()))
    }
}