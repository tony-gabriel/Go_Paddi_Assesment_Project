package com.deaelum.android.gopaddi.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.ui.data.Country
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDatesBottomSheet(
    onDismiss: () -> Unit,
    onSelectCountry: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var searchText by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        dragHandle = null,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                    )
                }

                Text(
                    text = "Where",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Divider()

            Text(
                text = stringResource(id = R.string.select_a_city),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = {Text("Search cities")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2196F3),
                ),
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(16.dp))

            CountryList(searchText, onSelectCountry)
        }


    }
}

@Composable
fun CountryList(searchText: String, onSelectCountry: (String) -> Unit) {
    val countries = getListOfCountries()
    var filteredCountries: MutableList<Country>

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        filteredCountries = if (searchText.isEmpty()) {
            countries
        } else {
            val resultList = mutableListOf<Country>()
            for (country in countries) {
                if (country.name.contains(searchText, ignoreCase = true)) {
                    resultList.add(country)
                }
            }
            resultList
        }

        items(filteredCountries) { filteredCountry ->
            CountryItem(filteredCountry, onSelectCountry)
        }
    }
}

@Composable
fun getListOfCountries(): MutableList<Country> {
    val isoCountryCodes = Locale.getISOCountries()
    val countriesWithIcons = mutableListOf<Country>()

    for (countryCode in isoCountryCodes) {
        val locale = Locale("", countryCode)
        val countryName = locale.displayCountry
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
        val flag = String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))

        val country = Country(name = countryName, locale = "${locale.country}", flag = flag)
        countriesWithIcons.add(country)
    }

    return countriesWithIcons
}