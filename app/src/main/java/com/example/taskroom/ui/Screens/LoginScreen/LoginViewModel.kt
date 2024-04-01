package com.example.taskroom.ui.Screens.LoginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskroom.currentUser
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.data.remote.dto.CredentialDto
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.data.repository.AuthRepository
import com.example.taskroom.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
    ) : ViewModel(){
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    var usernameError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)

    var message by mutableStateOf("")

    fun onUsernameChange(valor:String){
        username= valor
        usernameError= valor.isBlank()
    }

    fun onPasswordChange(valor:String){
        password= valor
        passwordError= valor.isBlank()
    }

    fun Validate():Boolean{
        onPasswordChange(password)
        onUsernameChange(username)
        return !usernameError && !passwordError
    }

    fun Clear(){
        password=""
        username=""
    }

    fun Login(storage: SessionStorage){
        if (Validate()){
            viewModelScope.launch{
                try {
                    var user = authRepository.login(CredentialDto( username=username,password=password))
                    if (user != null) {
                        currentUser = user
                        storage.saveID(currentUser.id)
                    }
                    else{
                        message = "LOGIN ERROR"
                        Clear()

                    }
                }
                catch (e : Exception){
                    throw e
                }
            }
        }


    }

    fun Login(id:Int) {
            viewModelScope.launch{
                try {
                    var user =  userRepository.getUserById(id)
                    user?.let {
                        currentUser =  it
                    }
                }
                catch (e : Exception){
                    throw e
                }
            }
    }

}