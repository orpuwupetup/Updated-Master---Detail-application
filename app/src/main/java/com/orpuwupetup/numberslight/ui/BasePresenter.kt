package com.orpuwupetup.numberslight.ui

interface BasePresenter<T> {

    var view: T?

    fun takeView(view: T)
    fun dropView()

}