package com.benmohammad.gitarrow.data.source.local

import com.benmohammad.gitarrow.data.RepoModel
import com.benmohammad.gitarrow.data.source.RepoDataSource
import com.benmohammad.gitarrow.util.AppExecutors

class RepoLocalDataSource private constructor(
    private val appExecutors: AppExecutors,
    private val repoDao: RepoDao
): RepoDataSource{

    companion object {
        private var INSTANCE: RepoLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, repoDao: RepoDao): RepoLocalDataSource {
            if(INSTANCE == null) {
                synchronized(RepoLocalDataSource.javaClass) {
                    if(INSTANCE == null) {
                        INSTANCE = RepoLocalDataSource(appExecutors, repoDao)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun getRepos(repoCallback: RepoDataSource.RepoCallback) {
        appExecutors.diskIO.execute {
            val repoLIst = repoDao.getRepos()
            appExecutors.mainThread.execute{
                if(repoLIst.isEmpty()) {
                    repoCallback.onDataNotAvailable()
                } else {
                    repoCallback.onLoaded(repoLIst)
                }
            }
        }
    }

    override fun saveRepo(repo: RepoModel) {
        appExecutors.diskIO.execute{
            repoDao.insertRepo(repo)
        }
    }

    override fun refreshRepos() {

    }

    override fun deleteAllRepos() {
        appExecutors.diskIO.execute{
            repoDao.deleteRepos()
        }
    }
}