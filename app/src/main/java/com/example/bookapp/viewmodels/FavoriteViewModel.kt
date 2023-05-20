package com.example.bookapp.viewmodels

import com.example.bookapp.models.Book
import com.example.bookapp.models.BookList
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel {
    val readBooks: List<Book>
        get() = BookList.bookListState.value.filter { it.read }
    //wir greifen auf die Klasse zu mit BookList.

    fun getAllBooks(): StateFlow<List<Book>> {
        return  BookList.bookListState
    }

    fun updateReadBooks(book: Book) = BookList.bookListState.value.find { it.isbn == book.isbn }?.let { book ->
        book.read = !book.read

    }

}