@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bookapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.bookapp.viewmodels.EditViewModel
import com.example.bookapp.widget.HomeTopAppBar
import com.example.bookapp.widget.SimpleTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: EditViewModel) {

    //später soll der obere Teil Scaffold noch in den EditScreen
    Scaffold(topBar = {
       HomeTopAppBar(
            title = "Favorite Screen",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.HomeScreen.route) }) {
                    Row {
                        Text(text = "Favorites/Home Screen", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))

                    }
                }
                DropdownMenuItem(onClick = { navController.navigate(Screen.EditScreen.route) }) {
                    Row {
                        Text(text = "Edit Screen", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))

                    }
                }
            }
        )
    }
    )
    { padding ->
        MainContent(modifier = Modifier.padding(padding), navController = navController,  booksViewModel = viewModel)
    }
}
/*
@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: EditViewModel
) {
    val bookListState by viewModel.getAllBooks().collectAsState()
    if (bookListState.isEmpty()) {
        Text(text = "Es gibt derzeit keine Favoriten")
    } else
        Scaffold(topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = "My Favorite Movies")
            }
        }){ padding ->
            val bookList: List<Book> = getBooks(){
        Column(modifier = Modifier.padding(5.dp)) {
            LazyColumn {
                items(bookList){ books ->
                    BookRow(
                        book = book,


                }
            }
        }
    }*/

