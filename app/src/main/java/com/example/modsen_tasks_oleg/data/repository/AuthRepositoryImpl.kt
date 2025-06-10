package com.example.modsen_tasks_oleg.data.repository

import com.example.modsen_tasks_oleg.domain.repository.IAuthRepository

import kotlinx.coroutines.delay

class AuthRepositoryImpl : IAuthRepository {

    private val validCredentials = listOf(
        Pair("admin", "123"),
        Pair("user", "456")
    )

    override suspend fun isValidCredentials(login: String, password: String): Boolean {
        delay(3000)
        return validCredentials.contains(Pair(login, password))
    }
}