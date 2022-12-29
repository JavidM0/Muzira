package com.example.presentation.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.User
import com.example.domain.usecase.RegisterUserUseCase
import com.example.data.module.UserInfoModule
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    private val _invalidEmailEvent: MutableLiveData<Boolean> = MutableLiveData()
    val invalidEmailEvent: LiveData<Boolean> get() = _invalidEmailEvent

    private val _invalidPasswordEvent: MutableLiveData<Boolean> = MutableLiveData()
    val invalidPasswordEvent: LiveData<Boolean> get() = _invalidPasswordEvent

    private val _invalidConfirmPasswordEvent: MutableLiveData<Boolean> = MutableLiveData()
    val invalidConfirmPasswordEvent: LiveData<Boolean> get() = _invalidConfirmPasswordEvent

    private val _successValidationEvent: MutableLiveData<UserInfoModule> = MutableLiveData()
    val successValidationEvent: LiveData<UserInfoModule> get() = _successValidationEvent

    fun validateInputs(
        email: String,
        password: String,
        confirmPassword: String,
    ) = when {
        !isValidateEmail(email) -> _invalidEmailEvent.value = true
        !isValidatePassword(password) -> _invalidPasswordEvent.value = true
        !isValidateConfirmPassword(
            password,
            confirmPassword
        ) -> _invalidConfirmPasswordEvent.value = true
        else -> _successValidationEvent.value = UserInfoModule(email)
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
        const val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@\$%^&()\\[\\]{:;<>,?/~_+\\-=|]).{8,64}$"
    }
}