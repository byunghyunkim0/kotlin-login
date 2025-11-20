package com.my.kotlinlogin.domain.token

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, Long>