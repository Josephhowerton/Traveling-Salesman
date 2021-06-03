package android.josephhowerton.travelingsalesman.ui.main.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.app.Config
import android.josephhowerton.travelingsalesman.ui.auth.AuthActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = Config.USER_SHARED_PREFERENCE_NAME
        preferenceManager.sharedPreferencesMode = Context.MODE_PRIVATE
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        preferenceManager.onPreferenceTreeClickListener = this
        settingsViewModel = ViewModelProvider(
            this,
            SettingsViewModelFactory(requireActivity().application)
        ).get(SettingsViewModel::class.java)
        setPreferencesFromResource(R.xml.user_preferences, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        when(preference!!.key){
            "sign_out" -> signOut()
        }
        return super.onPreferenceTreeClick(preference)
    }

    private fun signOut(){
        settingsViewModel.signOut()
        signOutGoogle()
        navigateToAuth()
    }

    private fun signOutGoogle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_client_id))
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity().application, gso)

        mGoogleSignInClient.signOut().addOnCompleteListener{ task ->

        }
    }

    private fun navigateToAuth(){
        val intent = Intent(requireActivity(), AuthActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}