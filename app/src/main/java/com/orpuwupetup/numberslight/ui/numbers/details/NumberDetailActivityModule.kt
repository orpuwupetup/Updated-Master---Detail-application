package com.orpuwupetup.numberslight.ui.numbers.details

import com.orpuwupetup.numberslight.di.annotations.ActivityScoped
import com.orpuwupetup.numberslight.ui.main.MainActivity
import com.orpuwupetup.numberslight.ui.numbers.details.fragment.NumberDetailsFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NumberDetailActivityModule {

    @Binds
    @ActivityScoped
    internal abstract fun bindNumberDetailPresenter(presenter: NumberDetailActivityPresenter): NumberDetailActivityContract.Presenter

    @Module
    companion object {

        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun providesDetailsFragment() = NumberDetailsFragment()

        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun getNumberNameFromIntent(context: NumberDetailActivity): String =
            context.intent.getStringExtra(MainActivity.NUMBER_NAME)
    }
}