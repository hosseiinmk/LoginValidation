package com.hosseinmohammadkarimi.loginvalidation.ui.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseinmohammadkarimi.domain.use_case.PasswordValidation
import com.hosseinmohammadkarimi.domain.use_case.UsernameValidation
import com.hosseinmohammadkarimi.loginvalidation.utils.UIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usernameValidation: UsernameValidation = UsernameValidation(),
    private val passwordValidation: PasswordValidation = PasswordValidation()
) : ViewModel() {

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

    fun login() {
        val usernameResult = usernameValidation.isValid(username)
        val passwordResult = passwordValidation.isValid(password)
        val hasError = !usernameResult || !passwordResult
        if (hasError) {
            usernameError = usernameValidation.errorMessage
            passwordError = passwordValidation.errorMessage
            return
        }
        usernameError = ""
        passwordError = ""
        viewModelScope.launch {
            _uiEvent.emit(
                UIEvent.SnackbarShow(
                    message = "با موفقیت وارد شدید"
                )
            )
        }
    }
}