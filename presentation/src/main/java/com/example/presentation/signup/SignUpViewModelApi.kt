package com.example.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.data.module.UserInfoModule
import kotlinx.coroutines.flow.Flow

abstract class SignUpViewModelApi: ViewModel() {

    abstract val invalidEmailEvent: Flow<Boolean>
    abstract val invalidPasswordEvent: Flow<Boolean>
    abstract val invalidConfirmPasswordEvent: Flow<Boolean>
    abstract val successValidationEvent: Flow<UserInfoModule>
}
