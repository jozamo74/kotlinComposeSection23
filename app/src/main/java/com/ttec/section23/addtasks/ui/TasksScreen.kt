package com.ttec.section23.addtasks.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ttec.section23.addtasks.ui.model.TaskModel


//@Preview
@Composable
fun TasksScreen( viewModel: TasksViewModel ) {

    val showDialog: Boolean by viewModel.showDialog.observeAsState(false)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AddTasksDialog(
            show = showDialog,
            onDismiss = { viewModel.onDialogClose() },
            onTaskAdded = { viewModel.onTasksCreated(it) })
        FabDialog(Modifier.align(Alignment.BottomEnd), viewModel)
        TaskList(viewModel)
    }
}


@Composable
fun ItemTask(taskModel: TaskModel, viewModel: TasksViewModel) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit){
                detectTapGestures(onLongPress = {
                    viewModel.onItemRemove(taskModel)
                })
            }

    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)

            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { viewModel.onCheckBoxSelected(taskModel) })
        }

    }

}

@Composable
fun TaskList(viewModel: TasksViewModel) {
    val myTasks: List<TaskModel> = viewModel.tasks

    LazyColumn {
        items(myTasks, key = { it.id }) {task ->
            ItemTask(taskModel = task, viewModel = viewModel )
            
        }
    }
}

@Composable
private fun FabDialog(modifier: Modifier, viewModel: TasksViewModel) {
    FloatingActionButton(
        onClick = {
            viewModel.onShowDialog()
        },
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask by remember {
        mutableStateOf("")
    }
    if (show) {
        BasicAlertDialog(
            modifier = Modifier.clip(shape = RoundedCornerShape(16.dp)),
            onDismissRequest = { onDismiss() },

        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)

            ) {
                Text(
                    text = "Añade tu tarea",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold

                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = {
                        onTaskAdded(myTask)
                        myTask = ""
                              },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}