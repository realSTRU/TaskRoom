package com.example.taskroom.ui.Screens.HomeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskroom.currentUser
import com.example.taskroom.data.remote.dto.ParticipantDto

import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.TaskDto
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.data.repository.ParticipantRepository
import com.example.taskroom.data.repository.ProjectRepository
import com.example.taskroom.data.repository.TaskRepository
import com.example.taskroom.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
    private val participantRepository: ParticipantRepository
) : ViewModel(){

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var projectNote by mutableStateOf("")
    var endDate by mutableStateOf("")

    var titleError by mutableStateOf(false)
    var descriptionError by mutableStateOf(false)
    var projectNoteError by mutableStateOf(false)
    var endDateError by mutableStateOf(false)

    var projectList : MutableList<ProjectDto> = mutableListOf()
    var taskList : MutableList<TaskDto> = mutableListOf()
    fun onTitlechange(value : String){
        title=value
        titleError = title.isBlank()
    }
    fun onDescripcionchange(value : String){
        description=value
        descriptionError = description.isBlank()
    }
    fun onProjectNotehange(value : String){
        projectNote=value
        projectNoteError = projectNote.isBlank()
    }

    fun onEndDatechange(value : String){
        endDate=value
        endDateError = endDate.isBlank()
    }


    fun Validate(): Boolean{
        onEndDatechange(endDate)
        onDescripcionchange(description)
        onTitlechange(title)
        onProjectNotehange(projectNote)

        return !endDateError && !descriptionError && !titleError && !projectNoteError
    }

    fun changeState(task: TaskDto, state: Int){
        task.status = state
        task.startDate = task.startDate.split("T")[0]
        task.endDate = task.endDate.split("T")[0]
        currentUser.tasks.filter { o-> o.id == task.id }[0].status =  state
        viewModelScope.launch {
                taskRepository.updateTask(task)
                load()
        }
    }

    fun load(){
        viewModelScope.launch {
            userRepository.getUserById(currentUser.id)?.let {
                currentUser = it
            }
            if (currentUser.id!=0){
                userRepository.getProjectByUser(currentUser.id)?.let {
                    projectList = it
                }
                userRepository.getTaskByUser(currentUser.id)?.let{
                    taskList = it
                }
            }
        }
    }

    fun deleteProject(project: ProjectDto){
        viewModelScope.launch {
            projectRepository.removeParticipant(projectId = project.id, userId = currentUser.id)
            load()
        }
    }

    fun addProject() {
        if (Validate()){
            viewModelScope.launch {
                var date = LocalDateTime.now()
                try {
                    var startDate = ""
                    var dayOfMonth = date.dayOfMonth
                    var month = date.monthValue
                    var year= date.year
                    if(month<10 && dayOfMonth<10){
                        startDate="$year-0$month-0$dayOfMonth"
                    }
                    else if(month<10 && dayOfMonth>10){
                        startDate="$year-0$month-$dayOfMonth"
                    }
                    else if(month>10 && dayOfMonth<10){
                        startDate="$year-$month-0$dayOfMonth"
                    }
                    else if (month>10 && dayOfMonth>10){
                        startDate="$year-$month-$dayOfMonth"
                    }

                    var projectToSend =ProjectDto(title=title, description = description, startDate= startDate , endDate = endDate, note = projectNote)
                    var project = projectRepository.addProject(projectToSend)
                    project?.let {
                        projectRepository.addParticipant(projectId = project.id, userId = currentUser.id, roleId = 1)
                        currentUser = userRepository.getUserById(currentUser.id)?: UserDto()
                        modalNewProjectOpen = false
                        load()
                    }
                }
                catch (e: Exception){
                    throw e
                }
            }

        }

    }

}


