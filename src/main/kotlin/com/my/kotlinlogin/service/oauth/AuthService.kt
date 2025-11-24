package com.my.kotlinlogin.service.oauth

import com.my.kotlinlogin.domain.token.GoogleTokenVerifier
import com.my.kotlinlogin.domain.token.JwtTokenProvider
import com.my.kotlinlogin.domain.token.Token
import com.my.kotlinlogin.domain.user.AuthProvider
import com.my.kotlinlogin.domain.user.Role
import com.my.kotlinlogin.domain.user.User
import com.my.kotlinlogin.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val googleTokenVerifier: GoogleTokenVerifier,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtTokenProvider
) {

    fun loginWithGoogle(idToken: String): Token {
        val googleUser = googleTokenVerifier.verify(idToken)

        var user = userRepository.findByProviderAndProviderId(AuthProvider.GOOGLE, googleUser.sub)

        if (user == null) {
            user = userRepository.save(
                User(
                    email = googleUser.email,
                    name = googleUser.name,
                    provider = AuthProvider.GOOGLE,
                    providerId = googleUser.sub,
                    role = Role.USER,
                    picture = googleUser.picture
                )
            )
        }

        return Token(
            jwtProvider.generateAccessToken(user.id),
            jwtProvider.generateRefreshToken(user.id)
        )
    }
}