package com.orpuwupetup.numberslight.ui.main

import com.orpuwupetup.numberslight.di.annotations.ActivityScoped
import com.orpuwupetup.numberslight.ui.numbers.details.fragment.NumberDetailsFragment
import com.orpuwupetup.numberslight.ui.numbers.list.NumbersListFragment
import com.orpuwupetup.numberslight.utils.devicestate.DeviceStateChecker
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class MainActivityModule {

    @Binds
    @ActivityScoped
    internal abstract fun bindMainPresenter(presenter: MainActivityPresenter): MainActivityContract.Presenter

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ActivityScoped
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun providesListFragment() = NumbersListFragment()

        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun providesDetailsFragment() = NumberDetailsFragment()

        @Provides
        @JvmStatic
        internal fun provideDeviceStateChecker(context: MainActivity) = DeviceStateChecker(context)
    }
}