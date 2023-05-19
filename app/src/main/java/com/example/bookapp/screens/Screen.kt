package com.example.bookapp.screens

const val DETAIL_ARGUMENT_KEY = "isbn"
sealed class Screen (val route: String) {
    object HomeScreen : Screen("main")
    object EditScreen : Screen("edit/{$DETAIL_ARGUMENT_KEY}") {
        fun withId(id: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id)
        }
    }
  //  object FavoriteScreen : Screen("favorite")
/*
wenn edit screen aufgerufgen wird... dann ist die route "edit/isbn" --> ich übergebe eine id!
--> was an der Stelle im ursprünglichen drinnnen steht ist die ISBN-meine definierte id
wenns verwendet wir zum edit string definieren --> isbn wird ersetzt mit zb 12345678945(isbn nummer).
navigation schaut -> welcher Wert ist drinnen?
 */
}