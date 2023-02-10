package com.xfiles.example01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xfiles.example01.data.model.Movie
import com.xfiles.example01.ui.theme.Example01Theme
import com.xfiles.example01.ui.views.MovieViewModel
import com.xfiles.example01.ui.views.viewModelMainScreenProviderFactoryOf


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Example01Theme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val viewModel: MovieViewModel =
                        viewModel(factory = viewModelMainScreenProviderFactoryOf())
                    val viewState by viewModel.state.collectAsStateWithLifecycle()
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Block( items = viewState.popular.results)
                    }
                }
            }
        }
    }
}
@Composable
fun Block(
    items: List<Movie>
) {
    MovieCardListOne(movies = items)
    Spacer(Modifier.height(16.dp))
}

@Composable
fun MovieCardListOne(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
) {
    LazyColumn(
        modifier = modifier
            .height(240.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(movies) { item ->
            MovieCardOne(
                movie = item,
                OnMovieClick = {}
                )
        }

    }
}

@Composable
fun MovieCardOne(
    movie: Movie,
    OnMovieClick: (id: Int) -> Unit
) {
    Column(modifier = Modifier.width(150.dp)) {
        Column() {
            Text(
                text = movie.title,
                modifier = Modifier
                    .wrapContentHeight(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface
                ),
            )
        }
    }
}
