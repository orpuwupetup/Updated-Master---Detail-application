package com.orpuwupetup.numberslight.ui.main

class MainActivityState(
                        private val displayedItemName: String?): MainActivityContract.State {

    override fun getDisplayedItemName(): String? =
            displayedItemName
}