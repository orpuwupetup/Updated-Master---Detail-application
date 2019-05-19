package com.orpuwupetup.numberslight.ui.numbers.details

class NumberDetailsFragmentPresenter: NumberDetailsFragmentContract.Presenter {

    override var view: NumberDetailsFragmentContract.View? = null

    override fun takeView(view: NumberDetailsFragmentContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }
}