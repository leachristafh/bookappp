//@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bookapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
//import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookapp.R
//import androidx.compose.ui.text.input.KeyboardType

import com.example.bookapp.viewmodels.EditViewModel
import com.example.bookapp.widget.SimpleTopAppBar
import com.example.movieappmad23.screens.EditBookUIEvent
//wieso funktioniert es nicht mt bookapp.screens.EditBookUIEvent beim movieappmad23.
//warum ist das so???
import java.util.*
//da stand früher addbook i state
@Composable
fun EditScreen(
    navController: NavController,
    booksViewModel: EditViewModel,
    bookId: kotlin.String? //es muss null sein dürfen
    //Wieso war es bei leon books?, bookId: kotlin.String?){}
){
    val scaffoldState = rememberScaffoldState()

  Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = "add_book" )
            }
        },
    ) { padding ->
        MainContent(
            Modifier.padding(padding),
            booksViewModel = booksViewModel,
            navController = navController
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    booksViewModel: EditViewModel, //bei leon books
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        BookBody(
            bookUiState = booksViewModel.bookUiState,
            onBookValueChange = { newUiState, event -> booksViewModel.updateUIState(newUiState, event)},
            onSaveClick = {
                booksViewModel.saveBook()
                navController.navigate(Screen.HomeScreen.route) //statt main
            }
        )
    }
}

@Composable
fun BookBody(
    bookUiState: EditBookUiState,
    onBookValueChange: (EditBookUiState, EditBookUIEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
       BookInputForm (bookUiState = bookUiState, onBookValueChange = onBookValueChange)

        Button(
            enabled = bookUiState.actionEnabled,
            onClick = onSaveClick) {
            Text(text = "Add")
            //das String Resource ist sinnvoll, wenn wir den gleichen String an mehreren Teilen im App verwenden
            //oder: App mit mehreren Sprachen -> deutsch englisch und spanisch
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookInputForm(
    bookUiState: EditBookUiState,
    onBookValueChange: (EditBookUiState, EditBookUIEvent) -> Unit,
){

    OutlinedTextField(
        value = bookUiState.title,
        label = {Text("Enter a title!")},
        isError = bookUiState.titleErr,
        onValueChange = {
            onBookValueChange(bookUiState.copy(title =it), EditBookUIEvent.TitleChanged)
        }
    )


    OutlinedTextField(
        value = bookUiState.author,
        label = {Text("Enter a author!")},
        isError = bookUiState.authorErr,
        onValueChange = {
            onBookValueChange(bookUiState.copy(author =it), EditBookUIEvent.AuthorChanged)
        }
    )

    OutlinedTextField(
        value = bookUiState.isbn,
        label = {Text("Enter a isbn!")},
        isError = bookUiState.isbnErr,
        onValueChange = {
            onBookValueChange(bookUiState.copy(isbn =it), EditBookUIEvent.IsbnChanged)
        }
    )

    OutlinedTextField(
        value = bookUiState.year.toString(), //value muss ein string sein - kotlin sehr streng
        label = {Text("Enter a year!")},
        isError = bookUiState.yearErr,
        onValueChange = {
            onBookValueChange(bookUiState.copy(isbn =it), EditBookUIEvent.YearChanged)
        }
    )








//isbn
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navController: NavController,
    bookViewModel: String?,
    bookId:String?)
                {

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titel") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor*in") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Jahr der Erstveröffentlichung") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(
            onClick = {
                if (validateBookData(title, author, year, isbn)) {
                    val newBook = Book(title, author, year.toInt(), isbn)
                   // onBookCreated(newBook)
                } else {
                    // Show error message or handle invalid data
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Buch anlegen")
        }
    }
}

fun validateBookData(title: String, author: String, year: String, isbn: String): Boolean {
    if (title.isEmpty() || author.isEmpty()) {
        return false
    }

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    if (year.toIntOrNull() == null || year.toInt() > currentYear) {
        return false
    }


}

@Composable
fun MyApp() {
    var currentScreen by remember { mutableStateOf(Screen.NewEditBook) }
    var books by remember { mutableStateOf(emptyList<Book>()) }
wie tu ich mir die merken?*/