package com.example.taskroom.ui.Screens.ProjectScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskroom.currentUser
import com.example.taskroom.data.remote.dto.ParticipantDto
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.TaskDto
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.data.repository.AuthRepository
import com.example.taskroom.data.repository.ParticipantRepository
import com.example.taskroom.data.repository.ParticipantRepository_Factory
import com.example.taskroom.data.repository.ProjectRepository
import com.example.taskroom.data.repository.UserRepository
import com.example.taskroom.ui.Screens.HomeScreen.modalAddParticipantOpen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TaskUserDto(
    var task: TaskDto = TaskDto(),
    var user: UserDto = UserDto()
)
data class RoleUserDto(
    var role: Int = 0,
    var user: UserDto = UserDto()
)
@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository,
    private val participantRepository: ParticipantRepository
) : ViewModel() {
    var message by mutableStateOf("")
    var username by mutableStateOf("")
    var userid by mutableStateOf("0")

    var usernameError by mutableStateOf(false)
    var userIdError by mutableStateOf(false)
    fun onUsernameChange(value : String){
        username=value
        usernameError = username.isBlank()
    }
    fun onUserIdChange(value : String){
        try {
            userid= value
            userIdError = userid.toInt() > 0
        }
        catch (e:Exception){
            userIdError = true
        }

    }

    fun validate() : Boolean{
        return userIdError && usernameError
    }

    //other variables
    var currentProject = ProjectDto()
    var  taskUserList : MutableList<TaskUserDto> = mutableListOf()
    var  roleUserList : MutableList<RoleUserDto> = mutableListOf()

    fun getUserInfo(){
        load()
        taskUserList = mutableListOf()
        viewModelScope.launch {
            for( task in currentUser.tasks) {
                taskUserList.add(TaskUserDto(task= task,user = userRepository.getUserById(task.userId)?: UserDto()))
            }

        }
    }
    fun getUserRole(){
        load()
        roleUserList = mutableListOf()
        viewModelScope.launch {
            for( participant in currentProject.participants) {
                roleUserList.add(RoleUserDto(role = participant.roleId,user = userRepository.getUserById(participant.userId)?: UserDto()))
            }

        }
    }
    fun load(){
        viewModelScope.launch {
            projectRepository.getProjectById(currentProject.id)?.let {
                currentProject = it
            }
        }
    }
    fun addParticipant(){
        load()
        if (!validate()){
            viewModelScope.launch {
                val user = userRepository.getUserById(userid.toInt())
                user?.let {
                    if (it.username == username){
                        userRepository.addProjetToAUser(userId = currentUser.id, projectId = currentProject.id)
                        participantRepository.postParticipant(ParticipantDto(projectId = currentProject.id, userId = currentUser.id, roleId = 2))
                        projectRepository.addParticipant(projectId = currentProject.id, userId = userid.toInt(), roleId = 2)
                        modalAddParticipantOpen = !modalAddParticipantOpen
                        load()
                    }
                    else{
                        message = "username or tag incorrect"
                        userIdError = true
                        usernameError = true
                    }

                }

            }
        }
    }

    fun clean(){
        onUserIdChange("")
        onUsernameChange("")
        usernameError = false
        userIdError=false
    }
    fun deleteTask(task: TaskDto){

    }

    fun removeParticipant(){

    }

    fun loadProject(id:Int){
        viewModelScope.launch {
            var projectFinded = projectRepository.getProjectById(id)
            projectFinded?.let {
                currentProject = it
            }
        }
    }

}