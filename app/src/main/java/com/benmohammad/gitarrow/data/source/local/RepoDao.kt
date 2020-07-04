package com.benmohammad.gitarrow.data.source.local

import androidx.room.*
import com.benmohammad.gitarrow.data.RepoModel

@Dao
interface RepoDao {

    @Query("SELECT * FROM Repo")
    fun getRepos(): List<RepoModel>

    @Query("SELECT * FROM Repo WHERE id = :repoId")
    fun getRepoById(repoId: String): RepoModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(repo: RepoModel)

    @Update
    fun updateRepo(repo: RepoModel): Int

    @Query("DELETE FROM Repo WHERE id = :repoId")
    fun deleteRepoById(repoId: String): Int

    @Query("DELETE FROM repo")
    fun deleteRepos()
}