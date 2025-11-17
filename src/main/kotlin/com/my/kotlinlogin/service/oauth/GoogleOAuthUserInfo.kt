package com.my.kotlinlogin.service.oauth

class GoogleOAuthUserInfo(
    private val attributes: Map<String, Any>
) : OAuthUserInfo {
    override fun getProviderId(): String {
        return attributes["sub"].toString()
    }

    override fun getEmail(): String {
        return attributes["email"].toString()
    }

    override fun getName(): String {
        return attributes["name"].toString()
    }

    override fun getImageUrl(): String {
        return attributes["picture"].toString()
    }
}