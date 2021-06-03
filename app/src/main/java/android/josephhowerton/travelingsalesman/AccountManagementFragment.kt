package android.josephhowerton.travelingsalesman

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class AccountManagementFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.account_management, rootKey)
    }
}