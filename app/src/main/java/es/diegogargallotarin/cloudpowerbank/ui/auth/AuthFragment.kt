package es.diegogargallotarin.cloudpowerbank.ui.auth

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import es.diegogargallotarin.cloudpowerbank.R
import kotlinx.android.synthetic.main.auth_main_fragment.*

class AuthFragment : Fragment() {

    companion object {
        fun newInstance() = AuthFragment()
    }

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.auth_main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        btnAuthMainToLogin.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_authFragment_to_loginFragment)
        )
        btnAuthMainToRegister.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_authFragment_to_registerFragment)
        )


    }

}
