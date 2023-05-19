package com.example.bookapp.models

data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String,
    var read: Boolean = false //wir gehen davon aus, dass wenns hinzugefügt wird noch nicht gelesen wurde
)