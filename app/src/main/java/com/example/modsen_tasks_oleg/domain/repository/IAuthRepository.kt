package com.example.modsen_tasks_oleg.domain.repository

interface IAuthRepository {
    suspend fun isValidCredentials(login: String, password: String): Boolean
}