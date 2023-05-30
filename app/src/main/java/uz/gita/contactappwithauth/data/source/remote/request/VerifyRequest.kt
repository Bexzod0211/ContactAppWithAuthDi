package uz.gita.contactappwithauth.data.source.remote.request

data class VerifyRequest(
    val phone:String,
    val code:String
)