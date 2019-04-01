package es.diegogargallotarin.cloudpowerbank.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import es.diegogargallotarin.cloudpowerbank.R
import es.diegogargallotarin.cloudpowerbank.base.BaseActivity
import kotlinx.android.synthetic.main.auth_activity.*


fun Context.authScreenIntent(): Intent {
    return Intent(this, AuthActivity::class.java)
}

class AuthActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)

        FirebaseApp.initializeApp(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return findNavController(auth_fragment).navigateUp()
    }


}
