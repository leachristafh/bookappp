package com.example.bookapp.models

import com.example.bookapp.screens.toBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object BookList { //automatisch singleton class
    private val _bookListState = MutableStateFlow(listOf<Book>())
    val bookListState: StateFlow<List<Book>> = _bookListState.asStateFlow()

    fun updateReadBooks(book: Book) =
        _bookListState.value.find { it.isbn == book.isbn }?.let { book ->
            book.read = !book.read
//read icon ...mit boolean : remember im composable wo ich es nutze
        }
    //es wäre dann read


    fun saveBook(book: Book) {

        _bookListState.update { //weil wir es verändern wollen brauchen wir' _'!!!
            val list: MutableList<Book> = bookListState.value.toMutableList()
            list.add(book)
            list
        }
    }
}
