package com.example.movieappmad23.screens

sealed class EditBookUIEvent{
    object TitleChanged : EditBookUIEvent()
    object YearChanged: EditBookUIEvent()
    object AuthorChanged: EditBookUIEvent()
    object IsbnChanged: EditBookUIEvent()
    object submit: EditBookUIEvent()
}
