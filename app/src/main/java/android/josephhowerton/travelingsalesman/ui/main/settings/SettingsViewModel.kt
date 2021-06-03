package android.josephhowerton.travelingsalesman.ui.main.settings

import android.josephhowerton.travelingsalesman.data.Repository
import androidx.lifecycle.ViewModel

class SettingsViewModel(private val repository: Repository): ViewModel() {

    fun signOut(){
        repository.logout()
    }
}