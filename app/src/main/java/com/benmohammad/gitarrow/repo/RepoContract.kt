package com.benmohammad.gitarrow.repo

import com.benmohammad.gitarrow.BasePresenter
import com.benmohammad.gitarrow.BaseView

interface RepoContract {

    interface View: BaseView<Presenter> {

        var isActive: Boolean
        fun setLoadingIndicator(active: Boolean)
        fun showRepoList(repoList: List<RepoViewModel>)
        fun navigateToUrl(url: String)
    }

    interface Presenter: BasePresenter {
        fun onSwipeRefresh()
        fun onRefreshClick()
        fun onForceRefreshClick()
        fun onRepoClick(repo: RepoViewModel)
    }
}