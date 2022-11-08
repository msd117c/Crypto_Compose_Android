package com.msd117.cryptocompose.detail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.theme.ui.widget.TitleText

@Composable
fun CoinDetailErrorView() {
    Box(modifier = Modifier.fillMaxSize()) {
        TitleText(
            text = stringResource(id = R.string.details_error_message),
            color = MaterialTheme.colors.error,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
