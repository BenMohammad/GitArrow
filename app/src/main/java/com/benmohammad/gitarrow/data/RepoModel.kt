package com.benmohammad.gitarrow.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Repo")
data class RepoModel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "fork") val fork: Boolean,
    @ColumnInfo(name = "created_at") val created_at: String,
    @ColumnInfo(name = "stargazers_count") val stargazers_count: Int,
    @ColumnInfo(name = "html_url") val html_url: String
)