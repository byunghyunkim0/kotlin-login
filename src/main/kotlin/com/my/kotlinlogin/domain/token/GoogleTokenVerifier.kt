package com.my.kotlinlogin.domain.token

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GoogleTokenVerifier(
    @Value("\${spring.security.oauth2.client.registration.google.client-id}")
    private val clientId: String
) {

    private val verifier = GoogleIdTokenVerifier.Builder(
        NetHttpTransport(),
        JacksonFactory.getDefaultInstance()
    )
        .setAudience(listOf(clientId))
        .build()

    fun verify(idToken: String): GooglePayload {
        val token = verifier.verify(idToken)
            ?: throw IllegalArgumentException("Invalid Google ID Token")

        val payload = token.payload

        return GooglePayload(
            sub = payload.subject,
            email = payload.email,
            name = payload["name"]?.toString() ?: "",
            picture = payload["picture"]?.toString()
        )
    }
}

data class GooglePayload(
    val sub: String,
    val email: String,
    val name: String,
    val picture: String?
)