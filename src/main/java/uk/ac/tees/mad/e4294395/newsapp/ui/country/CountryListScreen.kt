package uk.ac.tees.mad.e4294395.newsapp.ui.country

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.ac.tees.mad.e4294395.R
import uk.ac.tees.mad.e4294395.newsapp.data.model.LocaleInfo
import uk.ac.tees.mad.e4294395.newsapp.ui.base.UiState
import uk.ac.tees.mad.e4294395.newsapp.ui.common.CountryUI
import uk.ac.tees.mad.e4294395.newsapp.ui.common.ShowError
import uk.ac.tees.mad.e4294395.newsapp.ui.common.ShowLoading
import uk.ac.tees.mad.e4294395.newsapp.ui.common.TopAppBarWithOutIconUI

@Composable
fun CountryListRoute(
    onCountryClick: (countryId: String) -> Unit,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBarWithOutIconUI(title = stringResource(id = R.string.countries))
        }, content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                CountryListScreen(uiState, onCountryClick)
            }
        }
    )
}

@Composable
fun CountryListScreen(
    uiState: UiState<List<LocaleInfo>>,
    onCountryClick: (countryId: String) -> Unit
) {

    when (uiState) {
        is UiState.Success -> {
            Countries(uiState.data, onCountryClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message.toString())
        }

    }
}

@Composable
fun Countries(countries: List<LocaleInfo>, onCountryClick: (countryId: String) -> Unit) {
    LazyColumn {
        items(countries, key = { country -> country.code }) { country ->
            CountryUI(country, onCountryClick)
        }
    }
}


