package com.my.kotlinlogin.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "USERS")
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val provider: AuthProvider,

    @Column(nullable = false)
    val providerId: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role,

    @Column(nullable = true)
    val picture: String?,
) {
    protected constructor() : this(
        id = 0L,
        email = "",
        name = "",
        provider = AuthProvider.GOOGLE,
        providerId = "",
        role = Role.USER,
        picture = null,
    )
}