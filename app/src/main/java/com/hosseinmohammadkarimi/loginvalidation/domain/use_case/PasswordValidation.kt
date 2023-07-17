package com.hosseinmohammadkarimi.loginvalidation.domain.use_case

class PasswordValidation {

    var errorMessage = ""
        private set

    fun isValid(password: String): Boolean {
        if (password.isBlank()) {
            errorMessage = "رمز عبور وارد نشده"
            return false
        }
        if (!password.matches(Regex("[a-zA-Z0-9!^$%@]*"))) {
            errorMessage = "رمز عبور باید انگلیسی باشد"
            return false
        }
        if (password.length < 6) {
            errorMessage = "رمز عبور باید حداقل شامل 6 کاراکتر باشد"
            return false
        }
        if (!Regex("[A-Z]").containsMatchIn(password)) {
            errorMessage = "رمز عبور باید شامل حداقل یک حرف بزرگ باشد"
            return false
        }
        if (!Regex("[!^$%@]").containsMatchIn(password)) {
            errorMessage = "رمز عبور باید شامل حداقل یکی از (@ % $ ^ !) باشد"
            return false
        }
        if (!Regex("[0-9]").containsMatchIn(password)) {
            errorMessage = "رمز عبور باید شامل حداقل یک عدد باشد"
            return false
        }
        errorMessage = ""
        return true
    }
}