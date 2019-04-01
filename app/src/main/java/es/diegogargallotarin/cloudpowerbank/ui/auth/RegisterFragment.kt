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
import es.diegogargallotarin.cloudpowerbank.models.User
import es.diegogargallotarin.cloudpowerbank.ui.main.mainScreenIntent
import kotlinx.android.synthetic.main.auth_register_fragment.*

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()

        private const val TAG = "AuthRegisterFragment"
    }

    private lateinit var viewModel: AuthViewModel

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.auth_register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        btnAuthRegister.setOnClickListener {
            this.createAccount(etAuthRegisterEmail.text.toString(), etAuthRegisterPass.text.toString())
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }

        (activity as AuthActivity).showProgressDialog()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener((activity as AuthActivity)) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    user?.let {
                        viewModel.user.value = User(it.uid, it.displayName!!, user.displayName!!, user.email!!)
                        goToMainScreen(viewModel.user.value!!)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText((activity as AuthActivity), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
                (activity as AuthActivity).hideProgressDialog()
            }
    }



    private fun validateForm(): Boolean {
        var valid = true

        val name = etAuthRegisterName.text.toString()
        if (TextUtils.isEmpty(name)) {
            etAuthRegisterName.error = "Required."
            valid = false
        } else {
            etAuthRegisterName.error = null
        }

        val email = etAuthRegisterEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            etAuthRegisterEmail.error = "Required."
            valid = false
        } else {
            etAuthRegisterEmail.error = null
        }

        val password = etAuthRegisterPass.text.toString()
        if (TextUtils.isEmpty(password)) {
            etAuthRegisterPass.error = "Required."
            valid = false
        } else {
            etAuthRegisterPass.error = null
        }

        return valid
    }

    private fun goToMainScreen(user: User) {
        startActivity(activity?.mainScreenIntent(user))
    }

}
