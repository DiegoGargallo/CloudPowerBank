package es.diegogargallotarin.cloudpowerbank.ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.diegogargallotarin.cloudpowerbank.R

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OnboardingFragment.newInstance())
                .commitNow()
        }
    }

}
