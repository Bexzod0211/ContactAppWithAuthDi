package uz.gita.contactappwithauth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappwithauth.presentation.directions.LoginDirection
import uz.gita.contactappwithauth.presentation.directions.MainDirection
import uz.gita.contactappwithauth.presentation.directions.RegisterDirection
import uz.gita.contactappwithauth.presentation.directions.SplashDirection
import uz.gita.contactappwithauth.presentation.directions.VerifyDirection
import uz.gita.contactappwithauth.presentation.directions.impl.LoginDirectionImpl
import uz.gita.contactappwithauth.presentation.directions.impl.MainDirectionImpl
import uz.gita.contactappwithauth.presentation.directions.impl.RegisterDirectionImpl
import uz.gita.contactappwithauth.presentation.directions.impl.SplashDirectionImpl
import uz.gita.contactappwithauth.presentation.directions.impl.VerifyDirectionImpl

@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {

    @Binds
    fun bindLoginDirection(impl:LoginDirectionImpl):LoginDirection

    @Binds
    fun bindSplashDirection(impl:SplashDirectionImpl):SplashDirection

    @Binds
    fun bindRegisterDirection(impl:RegisterDirectionImpl):RegisterDirection

    @Binds
    fun bindVerifyDirection(impl:VerifyDirectionImpl):VerifyDirection

    @Binds
    fun bindMainDirection(impl:MainDirectionImpl):MainDirection
}