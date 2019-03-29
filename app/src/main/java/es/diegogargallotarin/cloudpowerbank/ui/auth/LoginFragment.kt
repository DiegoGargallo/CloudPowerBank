package es.diegogargallotarin.cloudpowerbank.ui.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import es.diegogargallotarin.cloudpowerbank.R
import es.diegogargallotarin.cloudpowerbank.ui.main.MainScreenIntent
import kotlinx.android.synthetic.main.auth_login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()

        private const val TAG = "AuthLoginFragment"
    }

    private lateinit var viewModel: AuthViewModel

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.auth_login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)


        btnAuthLogin.setOnClickListener {
            this.signIn(etAuthLoginEmail.text.toString(), etAuthLoginPass.text.toString())
        }
    }


    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }

        (activity as AuthActivity).showProgressDialog()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener((activity as AuthActivity)) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    user?.let { goToMainScreen(it) }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        (activity as AuthActivity), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    tvAuthLoginStatus.setText(R.string.auth_login_failed)
                }
                (activity as AuthActivity).hideProgressDialog()
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = etAuthLoginEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            etAuthLoginEmail.error = "Required."
            valid = false
        } else {
            etAuthLoginEmail.error = null
        }

        val password = etAuthLoginPass.text.toString()
        if (TextUtils.isEmpty(password)) {
            etAuthLoginPass.error = "Required."
            valid = false
        } else {
            etAuthLoginPass.error = null
        }

        return valid
    }

    private fun goToMainScreen(user: FirebaseUser) {
        startActivity(activity?.MainScreenIntent(user))
    }
}
