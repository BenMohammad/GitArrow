package com.benmohammad.gitarrow.repo

import androidx.fragment.app.Fragment
import java.lang.reflect.Array.get

class RepoFragment: Fragment(), RepoContract.View {

    companion object {
        fun newInstance() = RepoFragment()
    }

    override var isActive: Boolean = false
    get() = isAdded


    override lateinit var presenter: RepoContract.Presenter


    interface RepoListener {
        fun onRepoClick(repo: RepoViewModel)
    }

    private var repoListener: RepoListener = object: RepoListener {
        override fun onRepoClick(repo: RepoViewModel) {
            presenter.onRepoClick(repo)
        }
    }

    override fun setLoadingIndicator(active: Boolean) {

    }

    override fun showRepoList(repoList: List<RepoViewModel>) {

    }

    override fun navigateToUrl(url: String) {

    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

}