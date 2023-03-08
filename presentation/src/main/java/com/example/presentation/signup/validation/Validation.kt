package com.example.presentation.signup.validation

import android.util.Patterns
import com.example.presentation.signup.SignUpViewModel
import java.util.regex.Pattern

sealed class Validation {

    object ValidateEmail : Validation() {
        fun isValidateEmail(email: String) = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    object ValidatePassword : Validation() {
        fun isValidatePassword(password: String) =
            !Pattern.compile(SignUpViewModel.PASSWORD_PATTERN).matcher(password).matches()
    }

    object ValidateConfirmPassword : Validation() {
        fun isValidateConfirmPassword(password: String, confirmPassword: String) =
            password != confirmPassword
    }
}
