package com.my.kotlinlogin.service.oauth

import com.my.kotlinlogin.domain.user.AuthProvider

object OAuthAttributes {

    fun extract(provider: AuthProvider, attributes: Map<String, Any>): OAuthUserInfo {
        return when (provider) {
            AuthProvider.GOOGLE -> GoogleOAuthUserInfo(attributes)
            AuthProvider.KAKAO -> TODO("KAKAO 로그인")
            AuthProvider.NAVER -> TODO("NAVER 로그인")
        }
    }
}