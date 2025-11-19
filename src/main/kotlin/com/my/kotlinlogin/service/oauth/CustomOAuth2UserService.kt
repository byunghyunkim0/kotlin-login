package com.my.kotlinlogin.service.oauth

import com.my.kotlinlogin.domain.token.JwtTokenProvider
import com.my.kotlinlogin.domain.token.RefreshToken
import com.my.kotlinlogin.domain.token.RefreshTokenRepository
import com.my.kotlinlogin.domain.user.AuthProvider
import com.my.kotlinlogin.domain.user.Role
import com.my.kotlinlogin.domain.user.User
import com.my.kotlinlogin.domain.user.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User? {
        val oAuth2User = super.loadUser(userRequest)

        val provider = AuthProvider.valueOf(
            userRequest.clientRegistration.registrationId.uppercase()
        )

        val info = OAuthAttributes.extract(provider, oAuth2User.attributes)

        val user = userRepository.findByProviderAndProviderId(provider, info.getProviderId())
            ?: userRepository.save(
                User(
                    provider = provider,
                    providerId = info.getProviderId(),
                    email = info.getEmail(),
                    name = info.getName(),
                    role = Role.USER
                )
            )

        val refreshToken = tokenProvider.generateRefreshToken(user.id)
        refreshTokenRepository.save(RefreshToken(user.id, refreshToken))

        return DefaultOAuth2User(
            emptyList(),
            mapOf(
                "userId" to user.id,
                "accessToken" to tokenProvider.generateAccessToken(user.id),
                "refreshToken" to refreshToken
            ),
            "userId"
        )
    }
}