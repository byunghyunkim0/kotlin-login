package com.my.kotlinlogin.domain.token

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
class RefreshToken(
    @Id
    val userId: Long,
    val refreshToken: String,
)