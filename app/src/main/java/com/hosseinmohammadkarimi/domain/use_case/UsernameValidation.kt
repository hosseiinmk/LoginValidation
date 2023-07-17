package com.hosseinmohammadkarimi.domain.use_case

class UsernameValidation {

    var errorMessage: String = ""
        private set

    fun isValid(username: String): Boolean {
        if (username.isBlank()) {
            errorMessage = "نام کاربری وارد نشده"
            return false
        }
        if (username[0].isDigit()) {
            errorMessage = "نام کاربری نمیتواند با عدد شروع شود"
            return false
        }
        if (!username.matches(Regex("[a-zA-Z0-9]*"))) {
            errorMessage = "نام کاربری باید انگلیسی باشد"
            return false
        }
        if (username.length < 3) {
            errorMessage = "نام کاربری باید حداقل شامل 3 کاراکتر باشد"
            return false
        }
        if (!Regex("[A-Z]").containsMatchIn(username)) {
            errorMessage = "نام کاربری باید شامل حداقل یک حرف بزرگ باشد"
            return false
        }
        if (!Regex("[0-9]").containsMatchIn(username)) {
            errorMessage = "نام کاربری باید شامل حداقل یک عدد باشد"
            return false
        }
        errorMessage = ""
        return true
    }
}