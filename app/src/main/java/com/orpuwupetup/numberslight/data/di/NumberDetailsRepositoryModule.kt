package com.orpuwupetup.numberslight.data.di

import com.orpuwupetup.numberslight.api.NumbersLightService
import com.orpuwupetup.numberslight.data.source.NumberDetailsDataSource
import com.orpuwupetup.numberslight.data.source.remote.NumberDetailsRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NumberDetailsRepositoryModule {

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(numbersService: NumbersLightService): NumberDetailsDataSource =
        NumberDetailsRemoteDataSource(numbersService)
}