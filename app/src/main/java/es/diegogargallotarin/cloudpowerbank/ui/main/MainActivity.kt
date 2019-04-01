package es.diegogargallotarin.cloudpowerbank.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseUser
import es.diegogargallotarin.cloudpowerbank.R
import es.diegogargallotarin.cloudpowerbank.base.BaseActivity
import es.diegogargallotarin.cloudpowerbank.models.User


fun Context.mainScreenIntent(user: User): Intent {
    val intent = Intent(this, MainActivity::class.java)
    intent.putExtra("USER", user)
    return intent
}

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
