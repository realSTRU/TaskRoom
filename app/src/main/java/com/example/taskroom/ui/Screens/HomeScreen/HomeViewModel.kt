package com.example.taskroom.ui.Screens.HomeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskroom.currentUser
import com.example.taskroom.data.remote.dto.ParticipantDto
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.data.repository.ProjectRepository
import com.example.taskroom.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository
) : ViewModel(){

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var projectNote by mutableStateOf("")
    var endDate by mutableStateOf("")

    var titleError by mutableStateOf(false)
    var descriptionError by mutableStateOf(false)
    var projectNoteError by mutableStateOf(false)
    var endDateError by mutableStateOf(false)

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


    fun addProject():Boolean {
        var incomplete by mutableStateOf(true)
        if (Validate()){
            viewModelScope.launch {
                var date = LocalDateTime.now()
                try {
                    var project = projectRepository.addProject(ProjectDto(title=title, description = description, startDate = "${date.year}-${date.monthValue}-${date.dayOfMonth}", endDate = endDate, note = projectNote))
                    project?.let {
                        userRepository.addProjetToAUser(userId = currentUser.id, projectId = project.id)
                        projectRepository.addParticipant(projectId = project.id, userId = currentUser.id, roleId = 0)
                        currentUser = userRepository.getUserById(currentUser.id)?: UserDto()
                        incomplete=false
                    }
                }
                catch (e: Exception){
                    throw e
                }
            }
        }
        return incomplete

    }

}