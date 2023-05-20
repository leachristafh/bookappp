@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bookapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.models.Book
import com.example.bookapp.ui.theme.Shapes
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
    book: Book ) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = Shapes.large,
        elevation = 10.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
              //  contentAlignment = Alignment.Center
            )

            BookDetails(modifier = Modifier.padding(12.dp), book = book)
        }
    }
}
@Composable
fun BookDetails(modifier: Modifier = Modifier, book: Book) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            book.title,
            modifier = Modifier.weight(6f),
            style = MaterialTheme.typography.h6
        )

        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { expanded = !expanded }) {
            Icon(imageVector =
            if (expanded) Icons.Filled.KeyboardArrowDown
            else Icons.Filled.KeyboardArrowUp,
                contentDescription = "expand",
                modifier = Modifier
                    .size(25.dp),
                tint = Color.DarkGray
            )
        }

    }
    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column (modifier = modifier) {
            androidx.compose.material.Text(text = "Author: ${book.author}", style = MaterialTheme.typography.caption)
            androidx.compose.material.Text(text = "First publish year: ${book.year}", style = MaterialTheme.typography.caption)
            androidx.compose.material.Text(text = "Isbn: ${book.isbn}", style = MaterialTheme.typography.caption)
            androidx.compose.material.Text(text = "Title: ${book.title}", style = MaterialTheme.typography.caption)

            //Divider(modifier = Modifier.padding(3.dp))


        }
    }
}





