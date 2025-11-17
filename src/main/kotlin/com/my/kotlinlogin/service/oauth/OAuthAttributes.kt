package com.my.kotlinlogin.service.oauth

import com.my.kotlinlogin.domain.user.AuthProvider

object OAuthAttributes {

    fun extract(provider: AuthProvider, attributes: Map<String, Any>): OAuthUserInfo {
        return when (provider) {
            AuthProvider.GOOGLE -> GoogleOAuthUserInfo(attributes)
            // todo Kakao Naver login
            AuthProvider.KAKAO -> GoogleOAuthUserInfo(attributes)
            AuthProvider.NAVER -> GoogleOAuthUserInfo(attributes)
        }
    }
}