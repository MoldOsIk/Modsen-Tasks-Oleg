package com.example.modsen_tasks_oleg.domain.usecase

import com.example.modsen_tasks_oleg.domain.repository.IAuthRepository

class LoginUseCase(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(login: String, password: String): Result<Unit> {
        return if (authRepository.isValidCredentials(login, password)) {
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("Invalid login or password"))
        }

    }
}