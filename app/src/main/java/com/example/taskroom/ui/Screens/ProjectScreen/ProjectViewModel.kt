package com.example.taskroom.ui.Screens.ProjectScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskroom.currentUser
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.TaskDto
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.data.repository.ParticipantRepository
import com.example.taskroom.data.repository.ProjectRepository
import com.example.taskroom.data.repository.TaskRepository
import com.example.taskroom.data.repository.UserRepository
import com.example.taskroom.ui.Screens.HomeScreen.modalAddParticipantOpen
import com.example.taskroom.ui.Screens.HomeScreen.modalAddTaskOpen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
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
    private val participantRepository: ParticipantRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {
    var message by mutableStateOf("")
    var username by mutableStateOf("")
    var userid by mutableStateOf("")

    var usernameError by mutableStateOf(false)
    var userIdError by mutableStateOf(false)

    var taskUserId by mutableStateOf("")
    var taskTitle by mutableStateOf("")
    var taskDescripcion by mutableStateOf("")
    var taskEndDate by mutableStateOf("")
    var taskNote by mutableStateOf("")

    var taskUserIdError  by mutableStateOf(false)
    var taskTitleError  by mutableStateOf(false)
    var taskDescripcionError  by mutableStateOf(false)
    var taskEndDateError  by mutableStateOf(false)
    var taskNoteError  by mutableStateOf(false)

    fun onUsernameChange(value : String){
        username=value
        usernameError = username.isBlank()
    }
    fun onUserIdChange(value : String){
        try {
            userid= value
            userIdError = userid.toInt() <= 0
        }
        catch (e:Exception){
            userIdError = true
        }

    }
    fun onTaskUserIdChange(value : String){
        try {
            taskUserId= value
            taskUserIdError = taskUserId.toInt() <= 0
        }
        catch (e:Exception){
            taskUserIdError = true
        }

    }
    fun onTaskTitleChange(value : String){
        taskTitle=value
        taskTitleError = taskTitle.isBlank()
    }
    fun onTaskDescriptionChange(value : String){
        taskDescripcion=value
        taskDescripcionError = taskDescripcion.isBlank()
    }
    fun onTaskNoteChange(value : String){
        taskNote=value
        taskNoteError = taskNote.isBlank()
    }

    fun onTaskEndDateChange(value : String){
        taskEndDate=value
        taskEndDateError = taskEndDate.isBlank()
    }

    private fun validateParticipant() : Boolean{
        return userIdError && usernameError
    }

    private fun validateTask() : Boolean{
        return taskNoteError  && taskDescripcionError  && taskEndDateError && taskUserIdError && taskTitleError
    }

    //other variables
    var currentProject = ProjectDto()
    var  taskUserList : MutableList<TaskUserDto> = mutableListOf()
    var  roleUserList : MutableList<RoleUserDto> = mutableListOf()

    fun getUserInfo(){
        taskUserList = mutableListOf()
        viewModelScope.launch {
            var tasks =currentProject.tasks
            tasks?.let {
                for (task in tasks) {
                    taskUserList.add(
                        TaskUserDto(
                            task = task,
                            user = userRepository.getUserById(task.userId) ?: UserDto()
                        )
                    )
                }
            }

        }
    }
    fun getUserRole(){
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
        if (!validateParticipant()){
            viewModelScope.launch {
                val user = userRepository.getUserById(userid.toInt())
                user?.let {
                    if (it.username == username){
                        projectRepository.addParticipant(projectId = currentProject.id, userId = userid.toInt(), roleId = 2)
                        modalAddParticipantOpen = !modalAddParticipantOpen
                        load()
                        clean()
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

    fun addTask(){
        load()
        if (!validateTask()) {
            var date = LocalDateTime.now()
            var startDate = ""
            var dayOfMonth = date.dayOfMonth
            var month = date.monthValue
            var year = date.year
            if (month < 10 && dayOfMonth < 10) {
                startDate = "$year-0$month-0$dayOfMonth"
            } else if (month < 10 && dayOfMonth > 10) {
                startDate = "$year-0$month-$dayOfMonth"
            } else if (month > 10 && dayOfMonth < 10) {
                startDate = "$year-$month-0$dayOfMonth"
            } else if (month > 10 && dayOfMonth > 10) {
                startDate = "$year-$month-$dayOfMonth"
            }
            try {
                viewModelScope.launch {
                    var taskToAdd = TaskDto(
                        projectId = currentProject.id,
                        userId = taskUserId.toInt(),
                        status = 0,
                        title = taskTitle,
                        description = taskDescripcion,
                        note = taskNote,
                        endDate = taskEndDate,
                        startDate = startDate
                    )
                    var task = taskRepository.postTask(
                        taskToAdd
                    )
                    task?.let {
                        modalAddTaskOpen = !modalAddTaskOpen
                        clean()
                        load()
                    }

                }
            }
            catch (e:Exception){}

        }

    }

    fun clean(){
        onUserIdChange("")
        onUsernameChange("")
        onTaskDescriptionChange("")
        onTaskNoteChange("")
        onTaskEndDateChange("")
        onTaskTitleChange("")
        onTaskUserIdChange("")
    }
    fun deleteTask(id:Int){
        if (isAdmin()) {
           viewModelScope.launch {
               var removedTask = taskRepository.deleteTask(id = id)

               removedTask?.let {
                   load()
               }
           }
        }
    }

    fun isAdmin(): Boolean{
        var valid = false
        try {
            var valid =(roleUserList.filter { o -> o.user.id == currentUser.id }[0]).role == 1
            return valid
        }
        catch (e:Exception){
            return false
        }
    }


    fun removeParticipant(id:Int){
        if (isAdmin())
        {
            viewModelScope.launch {
                var removedParticipant = projectRepository.removeParticipant(projectId=currentProject.id, userId  = id)
                removedParticipant?.let {
                    load()
                }
            }
        }

    }

    fun loadProject(id:Int){
        viewModelScope.launch {
            var projectFinded = projectRepository.getProjectById(id)
            projectFinded?.let {
                currentProject = it
            }
        }
        getUserInfo()
        getUserRole()
    }

}