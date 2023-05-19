package com.example.bookapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: EditViewModel) {

    //später soll der obere Teil Scaffold noch in den EditScreen
    Scaffold(/*topBar = {
       HomeTopAppBar(
            title = "Favorite Screen",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.EditScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.padding(4.dp))
                        Text(text = "Favorites", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                       mehrere drop down menu items nötig  Text(text = "EditScreen", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
            }
        )
    }*/
    )
    { padding ->
        MainContent(modifier = Modifier.padding(padding), navController = navController, viewModel = viewModel)
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: EditViewModel
) {
    val bookListState by viewModel.getAllBooks().collectAsState()
    if (bookListState.isEmpty()) {
        Text(text = "Es gibt derzeit keine Favoriten")
    } else {
        Column(modifier = Modifier.padding(5.dp)) {
            LazyColumn {



                }
            }
        }
    }


/*
@Composable
fun FavoriteScreen(navController: NavController){
    if(BookList == empty){
        Text(text="Es gibt derzeit keine FAvoriten")
    }
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }){ padding ->
        val bookList: List<Book> = getBooks()

        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(bookList){ books ->
                    BookRow(
                        book = book,

                    )
                }
            }
        }
    }

*/
