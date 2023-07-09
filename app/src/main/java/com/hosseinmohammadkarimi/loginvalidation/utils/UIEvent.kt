package com.hosseinmohammadkarimi.loginvalidation.utils

sealed class UIEvent {
    data class SnackbarShow(
        val message: String,
        val actionLabel: String? = null
    ): UIEvent()
}