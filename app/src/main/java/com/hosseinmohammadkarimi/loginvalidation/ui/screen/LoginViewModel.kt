package com.hosseinmohammadkarimi.loginvalidation.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseinmohammadkarimi.loginvalidation.utils.UIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var usernameError by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        username = input
    }

    fun updatePassword(input: String) {
        password = input
    }



    private fun isUsernameValid(): Boolean {
        if (username.isBlank()) {
            usernameError = "نام کاربری وارد نشده"
            return false
        }
        if (username[0].isDigit()) {
            usernameError = "نام کاربری نمیتواند با عدد شروع شود"
            return false
        }
        if (username.length < 3) {
            usernameError = "نام کاربری باید حداقل شامل 3 کاراکتر باشد"
            return false
        }
        if (!username.matches(Regex("[a-zA-Z0-9]*"))) {
            usernameError = "نام کاربری باید انگلیسی باشد"
            return false
        }
        if (!Regex("[A-Z]").containsMatchIn(username)) {
            usernameError = "نام کاربری باید شامل حداقل یک حرف بزرگ باشد"
            return false
        }
        if (!Regex("[0-9]").containsMatchIn(username)) {
            usernameError = "نام کاربری باید شامل حداقل یک عدد باشد"
            return false
        }
        usernameError = ""
        return true
    }

    private fun isPasswordValid(): Boolean {
        if (password.isBlank()) {
            passwordError = "رمز عبور وارد نشده"
            return false
        }
        if (username.length < 6) {
            passwordError = "رمز عبور باید حداقل شامل 6 کاراکتر باشد"
            return false
        }
        if (!password.matches(Regex("[a-zA-Z0-9!^$%@]*"))) {
            passwordError = "رمز عبور باید انگلیسی باشد"
            return false
        }
        if (!Regex("[A-Z]").containsMatchIn(password)) {
            passwordError = "رمز عبور باید شامل حداقل یک حرف بزرگ باشد"
            return false
        }
        if (!Regex("[!^$%@]").containsMatchIn(password)) {
            passwordError = "رمز عبور باید شامل حداقل یکی از (@ % $ ^ !) باشد"
            return false
        }
        if (!Regex("[0-9]").containsMatchIn(password)) {
            passwordError = "رمز عبور باید شامل حداقل یک عدد باشد"
            return false
        }
        passwordError = ""
        return true
    }

    fun login() {
        if (isUsernameValid()) {
            if (isPasswordValid()) {
                viewModelScope.launch {
                    _uiEvent.emit(
                        UIEvent.SnackbarShow(
                            message = "با موفقیت وارد شدید"
                        )
                    )
                }
            }
        }
    }
}