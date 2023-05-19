package com.example.bookapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookapp.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


import com.example.bookapp.common.Validator
import com.example.bookapp.models.BookList
import com.example.bookapp.screens.EditBookUiState
import com.example.bookapp.screens.hasError
import com.example.bookapp.screens.toBook
import com.example.movieappmad23.screens.EditBookUIEvent




class EditViewModel: ViewModel() {

/*
ich hab jetzt dank der BookList Klasse einen single Sourc of Truth für meine beiden unterschiedlichen Viewmodels
 */
    var bookUiState by mutableStateOf(EditBookUiState())
        private set

    val readBooks: List<Book>
        get() = BookList.bookListState.value.filter { it.read }
    //wir greifen auf die Klasse zu mit BookList.

   /* init {
        _bookListState.value = getBooks() //ich glaube das geht nicht ...man muss mit . arbeiten
        wenn man nix hat gibts auch keine liste
    }*/
    fun getAllBooks():StateFlow<List<Book>>{
        return  BookList.bookListState
    }
    fun updateUIState(newBookUiState: EditBookUiState, event: EditBookUIEvent){
        var state = EditBookUiState()   // this is needed because copy always creates a new instance

        when (event) {
            is EditBookUIEvent.TitleChanged -> {
                val titleResult = Validator.validateBookTitle(newBookUiState.title)
                state = if(!titleResult.successful) newBookUiState.copy(titleErr = true) else newBookUiState.copy(titleErr = false)
            }
            is EditBookUIEvent.YearChanged -> {
                val yearResult = Validator.validateBookYear(newBookUiState.year)
                state = if(!yearResult.successful) newBookUiState.copy(yearErr = true) else newBookUiState.copy(yearErr = false)
            }

            is EditBookUIEvent.AuthorChanged -> {
                val authorResult = Validator.validateBookAuthor(newBookUiState.author)
                state = if(!authorResult.successful) newBookUiState.copy(authorErr = true) else newBookUiState.copy(authorErr = false)
            }

            is EditBookUIEvent.IsbnChanged -> {
                val isbnResult = Validator.validateBookIsbn(newBookUiState.isbn)
                state = if(!isbnResult.successful) newBookUiState.copy(isbnErr = true) else newBookUiState.copy(isbnErr = false)
            }


            else -> {}
        }

        bookUiState = state.copy(actionEnabled = !newBookUiState.hasError())
    }

    fun updateReadBooks(book: Book) = BookList.bookListState.value.find { it.isbn == book.isbn }?.let { book ->
        book.read = !book.read

    }
    //es wäre dann read


    fun saveBook() {
        val book = bookUiState.toBook()

        BookList.saveBook(book)
    }
}