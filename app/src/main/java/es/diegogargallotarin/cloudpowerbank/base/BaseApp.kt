package es.diegogargallotarin.cloudpowerbank.base


import android.app.Application
import es.diegogargallotarin.cloudpowerbank.BuildConfig
import es.diegogargallotarin.cloudpowerbank.di.component.ApplicationComponent
import es.diegogargallotarin.cloudpowerbank.di.component.DaggerApplicationComponent
import es.diegogargallotarin.cloudpowerbank.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}