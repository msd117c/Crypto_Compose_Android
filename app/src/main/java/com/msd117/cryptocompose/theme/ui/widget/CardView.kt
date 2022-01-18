package com.msd117.cryptocompose.theme.ui.widget

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

enum class CardViewIconSize(val size: Dp) {
    MEDIUM(40.dp)
}

@ExperimentalMaterialApi
@Composable
fun CardIconButtonView(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    iconSize: CardViewIconSize = CardViewIconSize.MEDIUM,
    onClick: () -> Unit
) {
    CardIconButtonView(
        icon = icon,
        label = LocalContext.current.getString(label),
        modifier = modifier,
        iconSize = iconSize,
        onClick = onClick
    )
}

@ExperimentalMaterialApi
@Composable
fun CardIconButtonView(
    @DrawableRes icon: Int,
    label: String,
    modifier: Modifier = Modifier,
    iconSize: CardViewIconSize = CardViewIconSize.MEDIUM,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp)
            .shadow(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            GlideImage(
                imageModel = icon,
                contentScale = ContentScale.Crop,
                modifier = Modifier.requiredSize(iconSize.size)
            )
            Text(
                text = label,
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}