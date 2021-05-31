package android.josephhowerton.travelingsalesman.data

import android.app.Application

class Repository private constructor(application: Application){
    companion object {
        private var instance: Repository? = null
        fun getInstance(application: Application) : Repository {
            if(instance == null){
                instance = Repository(application)
            }
            return instance!!
        }
    }


}