package com.benmohammad.gitarrow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benmohammad.gitarrow.repo.RepoFragment
import com.benmohammad.gitarrow.repo.RepoPresenter
import com.benmohammad.gitarrow.util.Injection
import com.benmohammad.gitarrow.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var repoPresenter: RepoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val repoFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as RepoFragment? ?: RepoFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        repoPresenter = RepoPresenter(Injection.provideRepoRepository(applicationContext), repoFragment)
    }
}
