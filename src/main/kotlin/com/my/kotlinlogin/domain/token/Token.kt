package com.my.kotlinlogin.domain.token

data class Token(
    val accessToken: String,
    val refreshToken: String,
)