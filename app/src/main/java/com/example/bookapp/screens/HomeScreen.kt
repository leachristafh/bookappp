@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bookapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.*

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.models.Book
import com.example.bookapp.viewmodels.FavoriteViewModel
import com.example.bookapp.widget.HomeTopAppBar
import com.example.bookapp.widget.SimpleTopAppBar


@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {

    val viewModel = FavoriteViewModel()

    //später soll der obere Teil Scaffold noch in den EditScreen
   Scaffold(topBar = {
        HomeTopAppBar(
            title = "Favorite Screen",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.HomeScreen.route) }) {
                    Row {
                        Text(
                            text = "Home Screen", modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp)
                        )

                    }
                }

                DropdownMenuItem(onClick = { navController.navigate(Screen.EditScreen.route) }) {
                    Row {
                        Text(
                            text = "Edit Screen", modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp)
                        )

                    }
                }


            }
        )
    }
    )
    { padding ->
        MainContent(
            modifier = Modifier.padding(padding),
            navController = navController,
            viewModel = viewModel
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: FavoriteViewModel
) {
    val bookListState by viewModel.getAllBooks().collectAsState()
    val items = bookListState // Your list of items
    // zum debuggen : Text(text = items.size.toString())

    if (items.isEmpty()) {
        Text(text = "currently there are no favorites")

    } else

        LazyColumn {
            items(items) {
                BookRow(book = it)

            }
        }
}

@Composable

fun BookRow(
    book: Book) {}

/*
{
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = Shapes.large,
        elevation = 10.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            )

           // BookDetails(modifier = Modifier.padding(12.dp), book = book)
        }
    }
}
 */


