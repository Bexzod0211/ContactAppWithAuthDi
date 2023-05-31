package uz.gita.contactappwithauth.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.contactappwithauth.data.source.remote.api.AuthApi
import uz.gita.contactappwithauth.data.source.remote.api.ContactApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val baseUrl = "http://5a4e-185-139-137-116.ngrok-free.app"


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient):Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(client)
        .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context:Context):OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()


    @Provides
    @Singleton
    fun providesAuthApi(retrofit: Retrofit):AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun providesContactApi(retrofit: Retrofit):ContactApi = retrofit.create(ContactApi::class.java)


    @Provides
    @Singleton
    fun providesSharedPref(@ApplicationContext context: Context):SharedPreferences = context.getSharedPreferences("Contact",Context.MODE_PRIVATE)




}