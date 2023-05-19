package com.example.bookapp.screens

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.bookapp.common.Validator
//import com.example.bookapp.models.ListItemSelectable
import com.example.bookapp.models.Book

data class EditBookUiState(
    val title: String = "",
    val year: Int = 0, // oder val year: Int ;
    val author: String = "",
    val isbn: String = "",

    var titleErr: Boolean = false,//warum nicht val
    val yearErr: Boolean = false,
    val authorErr: Boolean = false,
    val isbnErr: Boolean = false,
    val actionEnabled: Boolean = false,

    )

fun EditBookUiState.toBook(): Book = Book(
    title = title,
    year = year, //wahrscheinlich ist es ein problem dass es ein int ist
    author = author,
    isbn = isbn,

    // rating = rating.toDoubleOrNull() ?: 0.0
)

fun EditBookUiState.toBookUiState(actionEnabled: Boolean): EditBookUiState = EditBookUiState(
    title = title,
    year = year,
    author = author,
    isbn = isbn,

    )

fun EditBookUiState.hasError(): Boolean {
    val titleResult = Validator.validateBookTitle(title)
    val yearResult = Validator.validateBookYear(year)
    val authorResult = Validator.validateBookAuthor(author)
    val isbnResult = Validator.validateBookIsbn(isbn)


    return listOf(
        titleResult,
        yearResult,
        authorResult,
        isbnResult,
    ).any {
        !it.successful
    }
}

