package com.orpuwupetup.numberslight.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.ui.AbstractFragmentContainingActivity
import com.orpuwupetup.numberslight.ui.numbers.details.NumberDetailActivity
import com.orpuwupetup.numberslight.ui.numbers.details.fragment.NumberDetailsFragment
import com.orpuwupetup.numberslight.ui.numbers.list.NumbersListFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : AbstractFragmentContainingActivity(), MainActivityContract.View, NumbersListFragment.NumberClickedListener {

    @Inject
    override lateinit var presenter: MainActivityContract.Presenter

    @Inject
    lateinit var numbersListFragmentState: NumbersListFragment

    private var numberDetailsFragmentState: Fragment? = null

    companion object {
        const val LIST_FRAGMENT_KEY = "listFragment"
        const val DETAIL_FRAGMENT_KEY = "detailFragment"

        const val NUMBER_NAME = "number_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState != null) {
//            numbersListFragmentState = supportFragmentManager.getFragment(savedInstanceState, LIST_FRAGMENT_KEY) as NumbersListFragment
//        }

        //showFragment(numbersListFragmentState, R.id.container)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        numbersListFragmentState.let { listFragment ->
//            supportFragmentManager.putFragment(outState, LIST_FRAGMENT_KEY, listFragment)
//        }
    }

    override fun onResume() {
        super.onResume()
        numbersListFragmentState.setOnNumberClickedListener(this)
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        numbersListFragmentState.setOnNumberClickedListener(null)
        presenter.dropView()
    }

    override fun showNumberDetails(clickedNumberName: String) {
        // if portrait or not tablet
        startActivity(Intent(this, NumberDetailActivity::class.java).apply {
            putExtra(NUMBER_NAME, clickedNumberName)
        })
    }

    override fun onNumberClicked(clickedNumberName: String) {
        presenter.listNumberClicked(clickedNumberName)
    }
}