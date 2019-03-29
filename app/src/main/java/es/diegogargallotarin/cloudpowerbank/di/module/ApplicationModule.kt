package es.diegogargallotarin.cloudpowerbank.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import es.diegogargallotarin.cloudpowerbank.base.BaseApp
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}
