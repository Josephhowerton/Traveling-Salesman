package android.josephhowerton.travelingsalesman.ui.success

import android.app.Application
import android.josephhowerton.travelingsalesman.data.Repository
import android.josephhowerton.travelingsalesman.ui.auth.auth.AuthViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SuccessViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessViewModel::class.java)) {
            return SuccessViewModel(
                repository = Repository.getInstance(application)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}