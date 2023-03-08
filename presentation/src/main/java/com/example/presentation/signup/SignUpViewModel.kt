package com.example.presentation.signup

import androidx.lifecycle.viewModelScope
import com.example.data.module.UserInfoModule
import com.example.domain.usecase.RegisterUserUseCase
import com.example.presentation.mapper.toUserData
import com.example.presentation.model.User
import com.example.presentation.signup.validation.Validation
import com.example.ui_kit.`ui-kit`.viewmodel.noReplyFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val registerUserUseCase: RegisterUserUseCase) : SignUpViewModelApi() {

    override val invalidEmailEvent: MutableSharedFlow<Boolean> = noReplyFlow()
    override val invalidPasswordEvent: MutableSharedFlow<Boolean> = noReplyFlow()
    override val invalidConfirmPasswordEvent: MutableSharedFlow<Boolean> = noReplyFlow()
    override val successValidationEvent: MutableSharedFlow<UserInfoModule> = noReplyFlow()

    override fun validateInputs(
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            when {
                Validation.ValidateEmail.isValidateEmail(
                    email = email
                ) -> invalidEmailEvent.emit(true)
                Validation.ValidatePassword.isValidatePassword(
                    password = password
                ) -> invalidPasswordEvent.emit(true)
                Validation.ValidateConfirmPassword.isValidateConfirmPassword(
                    password = password,
                    confirmPassword = confirmPassword
                ) -> invalidConfirmPasswordEvent.emit(true)
                else -> successValidationEvent.emit(UserInfoModule(email))
            }
        }
    }

    override fun registerUser(user: User) {
        viewModelScope.launch {
            registerUserUseCase.execute(user.toUserData())
        }
    }

    companion object {
        const val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@\$%^&()\\[\\]{:;<>,?/~_+\\-=|]).{8,64}$"
    }
}
