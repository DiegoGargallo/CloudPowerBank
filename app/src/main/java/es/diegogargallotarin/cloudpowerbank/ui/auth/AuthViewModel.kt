package es.diegogargallotarin.cloudpowerbank.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.diegogargallotarin.cloudpowerbank.models.User

class AuthViewModel : ViewModel() {
    val user = MutableLiveData<User>()

    init {
        // Here you could use the ID to get the user info from the DB or remote server
        user.value = User()
    }
}
