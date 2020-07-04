package com.benmohammad.gitarrow.repo

import androidx.lifecycle.ViewModel

data class RepoViewModel(
    val id: String,
    val name: String,
    val fork: String,
    val stargazersCount: String,
    val timeSinceCreated: String,
    val htmlUrl: String
): ViewModel()