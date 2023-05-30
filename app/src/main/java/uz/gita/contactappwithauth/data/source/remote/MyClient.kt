package uz.gita.contactappwithauth.data.source.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.contactappwithauth.app.App

object MyClient {
    /*val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(App.instance).build())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://9dce-37-110-214-112.ngrok-free.app")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()*/
}