package com.example.taskroom.ui.Screens.LoginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskroom.currentUser
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.data.repository.AuthRepository
import com.example.taskroom.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val authrepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel(){
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var password by mutableStateOf("")
    var reppasword by mutableStateOf("")

    var usernameError by mutableStateOf(false)
    var emailError by mutableStateOf(false)
    var nameError by mutableStateOf(false)
    var surnameError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)
    var reppasswordError by mutableStateOf(false)


    var message by mutableStateOf("")

    fun onUsernameChange(valor:String){
        username= valor
        usernameError= valor.isBlank()
    }
    fun onNameChange(valor:String){
        name= valor
        nameError= valor.isBlank()
    }

    fun onSurnameChange(valor:String){
        surname= valor
        surnameError= valor.isBlank()
    }

    fun onEmailChange(valor:String){
        email= valor
        emailError= valor.isBlank()
    }

    fun onPasswordChange(valor:String){
        password= valor
        passwordError= valor.isBlank()
    }

    fun onReppasswordChange(valor:String){
        reppasword= valor
        reppasswordError= password != reppasword
    }

    fun Validate():Boolean{
        onPasswordChange(password)
        onUsernameChange(username)
        onEmailChange(email)
        onSurnameChange(surname)
        onNameChange(name)
        onReppasswordChange(reppasword)

        return !usernameError && !passwordError && !emailError && !reppasswordError && !nameError && !surnameError
    }

    fun Clear(){
        password=""
        username=""
        email=""
        reppasword=""
        name=""
        surname=""
    }

    fun Register(storage: SessionStorage){
        if (Validate()){
            viewModelScope.launch {
                try {
                    var user =  authrepository.registrar(UserDto(username=username, password = password, email = email, name = name, surname = surname))
                    user?.let {
                        storage.saveID(it.id)
                        currentUser=it
                        Clear()
                        modalSigninOpen = !modalSigninOpen
                    }
                }
                catch (e: Exception)
                {
                    throw e
                }

            }
        }
    }

}