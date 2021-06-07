package android.josephhowerton.travelingsalesman.data.manager

class PreferenceManager {

    fun enablePushNotifications(){

    }

    fun disablePushNotifications(){

    }

    fun enableLocationServices(){

    }

    fun disableLocationServices(){

    }

    companion object {
        const val USER_SHARED_PREFERENCE_NAME = "DEFAULT_SHARED_PREFERENCES"
        const val SIGN_OUT_PREFERENCE_NAME = "sign_out"
        const val DELETE_PREFERENCE_NAME = "delete_account"
        const val LOCATION_SERVICES_PREFERENCE_NAME = "location_updates"
        const val LOCATION_MANUAL_PREFERENCE_NAME = "location_manual"
        const val PUSH_NOTIFICATIONS_PREFERENCE_NAME = "push_notifications"
    }
}