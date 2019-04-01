package es.diegogargallotarin.cloudpowerbank.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: String = "0",
                val name: String = "default name",
                val username: String = "default username",
                val mail: String = "default mail") : Parcelable