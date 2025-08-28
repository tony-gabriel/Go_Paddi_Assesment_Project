package com.deaelum.android.gopaddi.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deaelum.android.gopaddi.ui.data.Country

@Composable
fun CountryItem(country: Country, onSelectCountry: (String) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth().clickable { onSelectCountry(country.name) },
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "Location Icon",
            tint = Color.Gray,
            modifier = Modifier.height(80.dp).padding(start = 16.dp)
        )

        Text(
            text = country.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp).weight(1f)
        )

        Column(modifier = Modifier.padding(end = 16.dp)) {
            Text(country.flag)
            Text(
                text = country.locale,
                style = MaterialTheme.typography.bodyMedium,
            )

        }
    }
}