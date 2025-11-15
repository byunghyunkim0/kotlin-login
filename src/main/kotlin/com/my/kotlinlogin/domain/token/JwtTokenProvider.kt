package com.my.kotlinlogin.domain.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenProvider {

    private val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun generateAccessToken(userId: Long): String {
        val now = Date()
        val expireDate = Date(now.time + 1000 * 60 * 30)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(secretKey)
            .compact()
    }

    fun generateRefreshToken(userId: Long): String {
        val now = Date()
        val expireDate = Date(now.time + 1000 * 60 * 60 * 24 * 14)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(secretKey)
            .compact()
    }
}