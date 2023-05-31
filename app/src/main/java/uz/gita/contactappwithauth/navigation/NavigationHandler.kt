package uz.gita.contactappwithauth.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationHandler {
    val navBuffer:Flow<NavigationArg>
}