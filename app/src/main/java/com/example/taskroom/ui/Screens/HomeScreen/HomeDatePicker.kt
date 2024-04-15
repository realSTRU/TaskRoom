package com.example.taskroom.ui.Screens.HomeScreen

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDatePicker(
    context: Context,
    homeViewModel: HomeViewModel
) {

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            if (month < 10 && dayOfMonth < 10) {
                homeViewModel.onEndDatechange("$year-0$month-0$dayOfMonth")
            } else if (month < 10 && dayOfMonth > 10) {
                homeViewModel.onEndDatechange("$year-0$month-$dayOfMonth")
            } else if (month > 10 && dayOfMonth < 10) {
                homeViewModel.onEndDatechange("$year-$month-0$dayOfMonth")
            } else if (month > 10 && dayOfMonth > 10) {
                homeViewModel.onEndDatechange("$year-$month-$dayOfMonth")
            }
        }, year, month, day
    )
    TextField(
        value = homeViewModel.endDate,
        onValueChange = { homeViewModel.onEndDatechange(it) },
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(255, 180, 1),
            cursorColor = Color(255, 180, 1),
            unfocusedBorderColor = Color.White,
            backgroundColor = Color.Transparent,
            focusedLabelColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth(),
        isError = homeViewModel.endDateError,
        leadingIcon = {
            IconButton(onClick = {
                datePickerDialog.show()
            }) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "date")
            }
        },
        label = { Text("End Date") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )

}