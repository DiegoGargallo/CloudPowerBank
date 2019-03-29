package es.diegogargallotarin.cloudpowerbank.di.module

import dagger.Module
import dagger.Provides
import es.diegogargallotarin.cloudpowerbank.api.ApiServiceInterface

@Module
class FragmentModule {
    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}