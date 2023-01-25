package com.example.presentation.signin

import androidx.lifecycle.ViewModel
import com.example.data.module.UserInfoModule
import kotlinx.coroutines.flow.Flow

abstract class SignInViewModelApi : ViewModel() {

    abstract val errorLoginEvent: Flow<Boolean>
    abstract val successLoginEvent: Flow<UserInfoModule>
}
