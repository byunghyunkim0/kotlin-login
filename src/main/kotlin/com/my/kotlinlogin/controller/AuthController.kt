package com.my.kotlinlogin.controller

import com.my.kotlinlogin.service.oauth.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    data class GoogleLoginRequest(val idToken: String)
    data class LoginResponse(
        val accessToken: String,
        val refreshToken: String
    )

    @PostMapping("/google")
    fun login(@RequestBody req: GoogleLoginRequest): LoginResponse {
        val token = authService.loginWithGoogle(req.idToken)
        return LoginResponse(
            token.accessToken,
            token.refreshToken
        )
    }
}