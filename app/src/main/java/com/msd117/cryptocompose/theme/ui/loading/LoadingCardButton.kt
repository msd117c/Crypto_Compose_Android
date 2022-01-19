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
import com.msd117.cryptocompose.theme.paddingM
import com.msd117.cryptocompose.theme.paddingS
import com.msd117.cryptocompose.theme.sizeS
import com.msd117.cryptocompose.theme.zero

@ExperimentalMaterialApi
@Composable
fun LoadingCardIconButton() {
    Card(
        onClick = { },
        modifier = Modifier
            .padding(all = paddingS)
            .shadow(elevation = sizeS)
    ) {
        Row(modifier = Modifier.padding(all = paddingM)) {
            LoadingCircle(size = 40.dp)
            LoadingText(
                width = 100.dp,
                height = Height.Medium,
                modifier = Modifier
                    .padding(horizontal = paddingM, vertical = zero)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}