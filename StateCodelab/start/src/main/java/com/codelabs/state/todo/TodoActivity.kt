package com.codelabs.state.todo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.codelabs.state.ui.StateCodelabTheme


class TodoActivity : AppCompatActivity() {

    private val todoViewModel by viewModels<TodoViewModel>() // up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateCodelabTheme {
                Surface {
                    TodoActivityScreen(todoViewModel)
                }
            }
        }
    }
}

@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel) { // down
    // observeAsState observes a LiveData and returns a State object that is updated whenever the LiveData changes.
    val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
    TodoScreen(
        // state
        items = items,
        // event(the method reference syntax)
        onAddItem = todoViewModel::addItem,
        onRemoveItem = todoViewModel::removeItem
    )
}

