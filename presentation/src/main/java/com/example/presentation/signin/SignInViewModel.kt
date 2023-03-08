package com.example.presentation.signin

import androidx.lifecycle.viewModelScope
import com.example.data.module.UserInfoModule
import com.example.domain.usecase.LoginUserUseCase
import com.example.presentation.mapper.toUser
import com.example.ui_kit.`ui-kit`.viewmodel.noReplyFlow
import kotlinx.coroutines.flow.MutableSharedFlow

import kotlinx.coroutines.launch

class SignInViewModel(private val loginUserUseCase: LoginUserUseCase) : SignInViewModelApi() {

    override val errorLoginEvent: MutableSharedFlow<Boolean> = noReplyFlow()
    override val successLoginEvent: MutableSharedFlow<UserInfoModule> = noReplyFlow()

    override fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            val user = loginUserUseCase.execute(email = email, password = password)?.toUser()
            if (user != null) {
                successLoginEvent.emit(UserInfoModule(user.email))
            } else {
                errorLoginEvent.emit(true)
            }
        }
    }
}
