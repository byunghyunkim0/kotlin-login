package com.my.kotlinlogin.service.oauth

interface OAuthUserInfo {
    fun getProviderId(): String
    fun getEmail(): String
    fun getName(): String
    fun getImageUrl(): String
}