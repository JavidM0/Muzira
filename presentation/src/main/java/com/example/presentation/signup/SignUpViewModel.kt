package com.example.presentation.signup

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.data.module.UserInfoModule
import com.example.domain.entity.User
import com.example.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel(private val registerUserUseCase: RegisterUserUseCase) : SignUpViewModelApi() {

    override val invalidEmailEvent: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val invalidPasswordEvent: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val invalidConfirmPasswordEvent: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val successValidationEvent: MutableStateFlow<UserInfoModule> =
        MutableStateFlow(UserInfoModule(EMPTY_EMAIL))

    fun validateInputs(
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        viewModelScope.launch {
            when {
                !isValidateEmail(email) -> invalidEmailEvent.emit(true)
                !isValidatePassword(password) -> invalidPasswordEvent.emit(true)
                !isValidateConfirmPassword(
                    password,
                    confirmPassword
                ) -> invalidConfirmPasswordEvent.emit(true)
                else -> successValidationEvent.emit(UserInfoModule(email))
            }
        }
    }

    private fun isValidateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidatePassword(password: String) =
        Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()

    private fun isValidateConfirmPassword(password: String, confirmPassword: String) =
        password == confirmPassword

    fun registerUser(user: User) {
        viewModelScope.launch {
            registerUserUseCase.execute(user)
        }
    }

    companion object {
        const val EMPTY_EMAIL = ""
        const val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@\$%^&()\\[\\]{:;<>,?/~_+\\-=|]).{8,64}$"
    }
}
