package uz.gita.contactappwithauth.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import uz.gita.contactappwithauth.data.source.local.MySharedPreferences
import uz.gita.contactappwithauth.domain.AppRepository

@HiltAndroidApp
class App : Application() {
    /*companion object {
        lateinit var instance: App
            private set
    }*/

    override fun onCreate() {
        super.onCreate()
        /*instance = this
        MySharedPreferences.init(this)
        AppRepository.init()*/
    }


}