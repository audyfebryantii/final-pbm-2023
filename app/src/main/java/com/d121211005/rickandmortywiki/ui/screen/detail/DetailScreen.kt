package com.d121211005.rickandmortywiki.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211005.rickandmortywiki.DetailScreenState
import com.d121211005.rickandmortywiki.RickAndMortyWikiUiState
import com.d121211005.rickandmortywiki.R
import com.d121211005.rickandmortywiki.data.model.Result

@Composable
fun DetailScreen(
    uistate: RickAndMortyWikiUiState,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when(uistate.detailScreenState){
            is DetailScreenState.Success ->
                Column {
                    CharacterProfile(uistate.detailScreenState.characterDetail)
                    Spacer(modifier = Modifier
                        .height(8.dp))
                    CharacterAbout(uistate.detailScreenState.characterDetail)
                }
            is DetailScreenState.Empty ->
                Text (
                    text = "Informasi tidak ditemukan"
                )
            is DetailScreenState.Loading -> CircularProgressIndicator()
            is DetailScreenState.Failure ->
                Text(
                    text = "Gagal memuat informasi"
                )

            else -> {}
        }
    }
}

@Composable
fun CharacterProfile(
    characterDetail: Result,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = modifier
                .size(dimensionResource(R.dimen.image_size_detail))
                .border(
                    BorderStroke(3.dp, color = MaterialTheme.colorScheme.tertiaryContainer),
                    CircleShape
                )
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(characterDetail.image)
                .crossfade(enable = true)
                .build(),
            contentDescription = characterDetail.name
        )
    }
}

@Composable
fun CharacterAbout(
    characterDetail: Result,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Information",
            style = MaterialTheme.typography.displayMedium
        )
        Card(modifier = modifier
            .border(
                BorderStroke(1.dp, color = MaterialTheme.colorScheme.tertiaryContainer),
                RoundedCornerShape(8.dp)
            ),
        ) {
            Column(modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = "Gender:" + characterDetail.gender,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                Text(
                    text = "Species:" + characterDetail.species,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                Text(
                    text = "Status:" + characterDetail.status,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                Text(
                    text = "Origin:" + characterDetail.origin?.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                Text(
                    text = "Location:" + characterDetail.location?.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
            }
        }
    }
}

