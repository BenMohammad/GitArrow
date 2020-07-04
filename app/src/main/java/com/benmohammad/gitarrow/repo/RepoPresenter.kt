package com.benmohammad.gitarrow.repo

import com.benmohammad.gitarrow.data.RepoModel
import com.benmohammad.gitarrow.data.source.RepoDataSource
import com.benmohammad.gitarrow.data.source.RepoRepository
import com.benmohammad.gitarrow.util.mapRepoModelToRepoViewModel

class RepoPresenter(val repoRepository: RepoRepository, val repoView: RepoContract.View): RepoContract.Presenter {

    private var firstLoad = true

    init {
        repoView.presenter = this
    }

    override fun onSwipeRefresh() = loadRepos(false)

    override fun onRefreshClick() = loadRepos(false)

    override fun onForceRefreshClick() = loadRepos(false)

    override fun onRepoClick(repo: RepoViewModel) {
        repoView.navigateToUrl(repo.htmlUrl)
    }

    override fun start() = loadRepos(false)

    private fun loadRepos(forceUpdate: Boolean) {
        loadRepos(forceUpdate || firstLoad, true)
        firstLoad = false

    }
    private fun loadRepos(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if(showLoadingUI)
            repoView.setLoadingIndicator(true)

        if(forceUpdate)
            repoRepository.refreshRepos()

        repoRepository.getRepos(object: RepoDataSource.RepoCallback{
            override fun onLoaded(repoList: List<RepoModel>) {
                if(!repoView.isActive)
                    return

                if(showLoadingUI)
                    repoView.setLoadingIndicator(false)

                val repoViewModelList = mapRepoModelToRepoViewModel(repoList)
                processRepos(repoViewModelList)
            }

            override fun onDataNotAvailable() {

            }
        })
    }

    private fun processRepos(repoList: List<RepoViewModel>) {
        repoView.showRepoList(repoList)
    }
}