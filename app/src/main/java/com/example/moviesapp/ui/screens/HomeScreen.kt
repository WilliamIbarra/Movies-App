package com.example.moviesapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.R
import com.example.moviesapp.data.Movie
import com.example.moviesapp.utilities.NavigationItem

@Composable
fun HomeScreen(navController: NavController) { // Added navController
    MoviesColumn( movies = listOf(
        Movie(drawable = R.drawable.deadpool3, name = "Deadpool 3"),
        Movie(drawable = R.drawable.spiderman, name = "Spider-Man"),
        Movie(drawable = R.drawable.thor_love_thunder, name = "Thor - Love & Thunder")
        ),
        navController = navController
    )
}

@Composable
fun MoviesColumn(
    modifier: Modifier = Modifier,
    movies: List<Movie> = emptyList(),
    navController: NavController
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding =  PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        modifier = modifier
    ) {
        items(movies) { movie ->
            FavoriteCollectionCard(
                movie.drawable,
                movie.name,
                onClick = {
                    navController
                        .navigate(
                            NavigationItem.Detail.route + "/${movie.name}/${movie.drawable}" // Route defined in the navHost that can receives name and drawable
                        )
                })
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { } // Empty clickListener by default
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .clickable(enabled = true, onClick = onClick)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        drawable = R.drawable.deadpool3,
        name = "Deadpool 3")
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController()) // Set navController by parameter
}