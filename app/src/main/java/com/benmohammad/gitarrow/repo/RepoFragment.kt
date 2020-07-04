package com.benmohammad.gitarrow.repo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.benmohammad.gitarrow.R
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.fragment_repo.view.*
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

    private val repoAdapter = RepoAdapter(ArrayList(0), repoListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_repo, container, false)
        with(root) {
            recyclerView.adapter = repoAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
            swipeRefreshLayout.setOnRefreshListener { presenter.onSwipeRefresh() }
        }

        setHasOptionsMenu(true)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_repo_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_refresh -> {
                presenter.onForceRefreshClick()
                return true
            }
        }
        return onOptionsItemSelected(item)
    }

    override fun setLoadingIndicator(active: Boolean) {
        with(swipeRefreshLayout) {
            post{isRefreshing = active}
        }
    }

    override fun showRepoList(repoList: List<RepoViewModel>) {
        repoAdapter.repoList = repoList
    }

    override fun navigateToUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

}