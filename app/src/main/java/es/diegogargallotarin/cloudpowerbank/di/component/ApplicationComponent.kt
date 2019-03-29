package es.diegogargallotarin.cloudpowerbank.di.component

import dagger.Component
import es.diegogargallotarin.cloudpowerbank.base.BaseApp
import es.diegogargallotarin.cloudpowerbank.di.module.ApplicationModule

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}
