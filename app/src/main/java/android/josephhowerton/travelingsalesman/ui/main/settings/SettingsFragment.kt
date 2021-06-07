package android.josephhowerton.travelingsalesman.ui.main.settings

import android.os.Bundle
import android.content.Context
import androidx.preference.Preference
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.data.manager.PreferenceManager
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = PreferenceManager.USER_SHARED_PREFERENCE_NAME
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
        return super.onPreferenceTreeClick(preference)
    }
}