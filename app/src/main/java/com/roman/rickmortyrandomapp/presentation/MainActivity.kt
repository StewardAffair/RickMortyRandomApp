package com.roman.rickmortyrandomapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.roman.rickmortyrandomapp.CharacterUI
import com.roman.rickmortyrandomapp.Status
import com.roman.rickmortyrandomapp.presentation.theme.RickMortyRandomAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickMortyRandomAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = Color.Black,
                            ),
                            title = {
                                Text("Random Character")
                            }
                        )
                    }
                ) { innerPadding ->

                    val viewModel by viewModel<MainViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    Screen(innerPadding, state, viewModel::onButtonClicked)
                }
            }
        }
    }
}

@Composable
fun Screen(innerPadding: PaddingValues, state: UIState, onClick: () -> Unit) {
    Column(
        modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.characterUI != null) {
            CharacterCard(state.characterUI)
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(4.dp),
            colors = buttonColors(
                containerColor = Color("#BB352E".toColorInt()),
                contentColor = Color.White
            ),
            onClick = { onClick() }) {
            Text("Generate New Character")
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(testCharacter: CharacterUI) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            model = testCharacter.url,
            contentDescription = ""
        )

        Text(
            text = testCharacter.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Box(
            modifier = Modifier
                .padding(6.dp)
                .clip(RoundedCornerShape(40))
                .align(Alignment.CenterHorizontally)
                .background(color = Color("#BB352E".toColorInt()))) {

            Text(
                text = "Status: ${ if (testCharacter.status == Status.ALIVE) "Alive" else "Dead" }",
                modifier = Modifier.padding(4.dp),
                color = Color.White,
                style = MaterialTheme.typography.labelSmall
            )

        }

        Text(
            text = "Episodes:",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(12.dp),
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp),
        ) {
            items(testCharacter.episodes.size) {
                Text(
                    text = testCharacter.episodes[it].name,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickMortyRandomAppTheme {
        Greeting("Android")
    }
}