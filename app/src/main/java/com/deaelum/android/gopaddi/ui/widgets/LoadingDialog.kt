package com.deaelum.android.gopaddi.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingDialog(modifier: Modifier = Modifier) {

    Box(modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}