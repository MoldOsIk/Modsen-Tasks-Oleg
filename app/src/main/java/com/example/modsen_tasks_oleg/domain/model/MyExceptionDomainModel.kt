package com.example.modsen_tasks_oleg.domain.model

sealed class MyExceptionDomainModel(exception: Throwable) : Throwable(exception) {
    override val cause: Throwable = exception

    class Other(exception: Throwable) : MyExceptionDomainModel(exception)
    class NoInternet(exception: Throwable) : MyExceptionDomainModel(exception)
    class NoAuth(exception: Throwable) : MyExceptionDomainModel(exception)
}