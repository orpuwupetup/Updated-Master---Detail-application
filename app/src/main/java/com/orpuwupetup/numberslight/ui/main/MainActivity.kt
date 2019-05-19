package com.orpuwupetup.numberslight.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.orpuwupetup.numberslight.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject
    override lateinit var presenter: MainActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}