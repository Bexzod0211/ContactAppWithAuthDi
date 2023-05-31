package uz.gita.contactappwithauth.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.contactappwithauth.utils.myLog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor():  AppNavigator,NavigationHandler {
    override val navBuffer: MutableSharedFlow<NavigationArg> = MutableSharedFlow()

    private suspend fun navigate(arg:NavigationArg){
        myLog("navigate and emit")
        navBuffer.emit(arg)
    }


    override suspend fun navigateTo(screen: AppScreen) = navigate {
        myLog("navigateTo")
        navigate(screen)
    }


    override suspend fun back() = navigate{
        popBackStack()
    }
}