package uz.gita.contactappwithauth.data.source.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MySharedPreferences @Inject constructor(
    private val preferences: SharedPreferences
) {

    private val LOGIN_STATE = "LOGIN_STATE"
    private val TOKEN = "TOKEN"


    /* init {
         preferences = context.getSharedPreferences("Contact", Context.MODE_PRIVATE)

     }

    companion object {
        private lateinit var instance: MySharedPreferences

        fun init(context: Context) {
            if (!::instance.isInitialized) {
                instance = MySharedPreferences(context)
            }
        }

        fun getInstance() = instance

    }*/


    var loginState: Boolean
        set(value) = preferences.edit().putBoolean(LOGIN_STATE, value).apply()
        get() = preferences.getBoolean(LOGIN_STATE, false)

    var token: String
        set(value) = preferences.edit().putString(TOKEN, value).apply()
        get() = preferences.getString(TOKEN, "").toString()

}