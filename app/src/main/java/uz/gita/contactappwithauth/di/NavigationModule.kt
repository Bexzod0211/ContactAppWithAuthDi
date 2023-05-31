package uz.gita.contactappwithauth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.navigation.NavigationDispatcher
import uz.gita.contactappwithauth.navigation.NavigationHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {


    @[Binds Singleton]
    fun bindAppNavigator(impl:NavigationDispatcher):AppNavigator

    @[Binds Singleton]
    fun bindNavigationHandler(impl:NavigationDispatcher):NavigationHandler
}