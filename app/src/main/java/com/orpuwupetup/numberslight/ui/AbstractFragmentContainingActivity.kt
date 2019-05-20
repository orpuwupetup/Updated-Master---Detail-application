package com.orpuwupetup.numberslight.ui

import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

abstract class AbstractFragmentContainingActivity : DaggerAppCompatActivity() {

    fun showFragment(fragment: Fragment, containerId: Int) {
        with(supportFragmentManager.beginTransaction()) {
            replace(containerId, fragment)
            commit()
        }
    }
}