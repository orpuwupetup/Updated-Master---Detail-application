package com.orpuwupetup.numberslight.data.di

import com.orpuwupetup.numberslight.api.NumbersLightService
import com.orpuwupetup.numberslight.data.source.NumbersDataSource
import com.orpuwupetup.numberslight.data.source.remote.NumbersRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NumbersRepositoryModule {

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(numbersService: NumbersLightService): NumbersDataSource =
            NumbersRemoteDataSource(numbersService)
}