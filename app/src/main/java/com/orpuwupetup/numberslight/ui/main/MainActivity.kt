package com.orpuwupetup.numberslight.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.ui.numbers.list.NumbersListFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainActivityContract.View {

    @Inject
    override lateinit var presenter: MainActivityContract.Presenter

    private var numbersListFragmentState: Fragment? = NumbersListFragment()

    companion object {
        const val LIST_FRAGMENT_KEY = "listFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            numbersListFragmentState = supportFragmentManager.getFragment(savedInstanceState, LIST_FRAGMENT_KEY)
        }

        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container, numbersListFragmentState ?: NumbersListFragment())
            commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        numbersListFragmentState?.let { listFragment ->
            supportFragmentManager.putFragment(outState, LIST_FRAGMENT_KEY, listFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }
}