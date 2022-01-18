package com.msd117.cryptocompose.theme.ui.loading

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun LoadingCardIconButton() {
    Card(
        onClick = { },
        modifier = Modifier
            .padding(4.dp)
            .shadow(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            LoadingCircle(size = 40.dp)
            LoadingText(
                width = 100.dp,
                height = Height.Medium,
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}