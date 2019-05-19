package com.orpuwupetup.numberslight.ui

interface BaseStatefulPresenter<V, S: BaseState>: BasePresenter<V> {

    fun takeView(view: V, state: S?)
    fun getState(): S
}