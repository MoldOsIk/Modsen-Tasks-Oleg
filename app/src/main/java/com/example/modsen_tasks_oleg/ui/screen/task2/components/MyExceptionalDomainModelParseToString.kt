package com.example.modsen_tasks_oleg.ui.screen.task2.components

import android.content.Context
import com.example.modsen_tasks_oleg.R
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel

fun MyExceptionDomainModel.parseToString(context: Context): String {
    return when (this) {
        is MyExceptionDomainModel.NoInternet -> context.getString(R.string.no_internet_failure)
        is MyExceptionDomainModel.Other -> context.getString(R.string.unknown_error)
    }
}