package es.diegogargallotarin.cloudpowerbank.ui.onboarding

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import es.diegogargallotarin.cloudpowerbank.R
import es.diegogargallotarin.cloudpowerbank.ui.auth.authScreenIntent

class OnboardingFragment : Fragment() {

    companion object {
        fun newInstance() = OnboardingFragment()
    }

    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.onboarding_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OnboardingViewModel::class.java)

        Handler().postDelayed({
            startActivity(activity?.authScreenIntent())
        }, 2000)
    }

}
