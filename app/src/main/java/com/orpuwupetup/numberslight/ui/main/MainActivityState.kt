package com.orpuwupetup.numberslight.ui.main

class MainActivityState(private val listScrollPosition: Int?, private val selectedItemPosition: Int?,
                        private val displayedItemName: String?,
                        private val lastOpenedFragment: String? = null): MainActivityContract.State {

    override fun getListScrollPosition(): Int? =
        listScrollPosition

    override fun getSelectedItemPosition(): Int? =
            selectedItemPosition

    override fun getDisplayedItemName(): String? =
            displayedItemName
}