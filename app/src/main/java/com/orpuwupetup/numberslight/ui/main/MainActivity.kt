package com.orpuwupetup.numberslight.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.ui.AbstractFragmentContainingActivity
import com.orpuwupetup.numberslight.ui.numbers.details.NumberDetailActivity
import com.orpuwupetup.numberslight.ui.numbers.details.fragment.NumberDetailsFragment
import com.orpuwupetup.numberslight.ui.numbers.list.NumbersListFragment
import javax.inject.Inject

class MainActivity : AbstractFragmentContainingActivity(), MainActivityContract.View,
    NumbersListFragment.NumberClickedListener {

    @Inject
    override lateinit var presenter: MainActivityContract.Presenter

    @Inject
    lateinit var numbersListFragment: NumbersListFragment

    @Inject
    lateinit var numberDetailsFragment : NumberDetailsFragment

    companion object {
        const val LIST_FRAGMENT_KEY = "listFragment"
        const val DETAIL_FRAGMENT_KEY = "detailFragment"
        const val NUMBER_NAME = "number_name"
        const val NO_ITEM_DETAILS_DISPLAYED = "no_details"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            supportFragmentManager.getFragment(savedInstanceState, LIST_FRAGMENT_KEY)?.apply {
                numbersListFragment = this as NumbersListFragment
            }
            supportFragmentManager.getFragment(savedInstanceState, DETAIL_FRAGMENT_KEY)?.apply {
                numberDetailsFragment = this as NumberDetailsFragment
            }
        }

        numbersListFragment.setOnNumberClickedListener(this)

        presenter.takeView(this, readFromBundle(savedInstanceState))
    }

    private fun readFromBundle(savedInstanceState: Bundle?): MainActivityContract.State =
        MainActivityState(
            savedInstanceState?.getString(NUMBER_NAME)
        )

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (numbersListFragment.isAdded)
            supportFragmentManager.putFragment(outState, LIST_FRAGMENT_KEY, numbersListFragment)

        if (numberDetailsFragment.isAdded)
            supportFragmentManager.putFragment(outState, DETAIL_FRAGMENT_KEY, numberDetailsFragment)

        writeToBundle(outState, presenter.getState())
    }

    private fun writeToBundle(outState: Bundle, state: MainActivityContract.State) {
        with(outState) {
            putString(NUMBER_NAME, state.getDisplayedItemName() ?: NO_ITEM_DETAILS_DISPLAYED)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
        numbersListFragment.setOnNumberClickedListener(null)

    }

    override fun showNumberDetailsForPhoneAndPortraitTablet(clickedNumberName: String) {
        // if portrait or not tablet
        startActivity(Intent(this, NumberDetailActivity::class.java).apply {
            putExtra(NUMBER_NAME, clickedNumberName)
        })
    }

    override fun showMasterDetailLayoutDetails(numberName: String) {
        numberDetailsFragment.showNewNumberDetails(numberName)
    }

    override fun showMasterDetailLayout(currentNumberDetailsName: String) {

        // just making sure that detail fragment will display correct value
        numberDetailsFragment.setNameOfNumberDetailsToShow(currentNumberDetailsName)

        showTabletPortraitListFragment()
        showFragment(numberDetailsFragment, R.id.container_tablet_landscape_details)

        /*
         to use showNewNumberDetails I have to be sure that number detail fragment presenter is initialized already
         because it is lateinit property, and if if detail fragment is already added, I can be quite sure that all is set
        */
        if (numberDetailsFragment.isAdded)
            numberDetailsFragment.showNewNumberDetails(currentNumberDetailsName)
    }

    private fun showTabletPortraitListFragment() {
        removeListFragment()

        showFragment(numbersListFragment, R.id.container_tablet_landscape_list)
    }

    // that is needed to change containers in which I want to place list fragment
    private fun removeListFragment() {
        with(supportFragmentManager) {
            popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            beginTransaction().remove(numbersListFragment).commit()
            executePendingTransactions()
        }
    }

    override fun setPhoneLayout() {
        showFragment(numbersListFragment, R.id.container)
    }

    override fun setTabletPortraitLayout() {
        removeListFragment()
        showFragment(numbersListFragment, R.id.container_tablet_portrait)
    }

    override fun onNumberClicked(clickedNumberName: String) {
        presenter.listNumberClicked(clickedNumberName)
    }
}