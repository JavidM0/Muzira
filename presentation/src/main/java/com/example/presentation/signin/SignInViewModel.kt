package com.example.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUserUseCase
import com.example.data.module.UserInfoModule
import kotlinx.coroutines.launch

class SignInViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    private val _errorLoginEvent: MutableLiveData<Boolean> = MutableLiveData()
    val errorLoginEvent: LiveData<Boolean> get() = _errorLoginEvent

    private val _successLoginEvent: MutableLiveData<UserInfoModule> = MutableLiveData()
    val successLoginEvent: LiveData<UserInfoModule> get() = _successLoginEvent

    fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            val user = loginUserUseCase.execute(email, password)

            if (user != null) {
                _successLoginEvent.value = UserInfoModule(user.email)
            } else {
                _errorLoginEvent.value = true
            }
        }
    }
}