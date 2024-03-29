package com.msd.core.ui.theme.ui.widget

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.msd.core.ui.theme.Padding.paddingM
import com.msd.core.ui.theme.Padding.paddingS
import com.msd.core.ui.theme.Size.sizeS
import com.msd.core.ui.theme.Size.smallIconSize
import com.msd.core.ui.theme.zero

@ExperimentalMaterialApi
@Composable
fun CardIconButtonView(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    iconSize: Dp = smallIconSize,
    onClick: () -> Unit
) {
    CardIconButtonView(
        icon = icon,
        label = stringResource(label),
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
    iconSize: Dp = smallIconSize,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(all = paddingS)
            .shadow(elevation = sizeS)
    ) {
        Row(modifier = Modifier.padding(all = paddingM)) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.requiredSize(iconSize)
            )
            BodyText(
                text = label,
                modifier = Modifier
                    .padding(horizontal = paddingM, vertical = zero)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
