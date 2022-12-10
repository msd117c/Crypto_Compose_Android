package com.msd.core.ui.theme.ui.image

import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import com.msd.core.ui.R
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UrlImage(urlProvider: () -> String, modifier: Modifier = Modifier) {
    GlideImage(
        imageModel = urlProvider(),
        modifier = modifier,
        contentScale = ContentScale.Crop,
        shimmerParams = ShimmerParams(
            baseColor = MaterialTheme.colors.background,
            highlightColor = Color.LightGray,
            durationMillis = 600,
            dropOff = 0.65f,
            tilt = 20f
        ),
        error = ImageVector.vectorResource(id = R.drawable.ic_placeholder)
    )
}

@Composable
fun IconImage(
    idProvider: () -> Int,
    contentDescription: String?,
    colorFilter: ColorFilter? = null
) {
    Image(
        imageVector = ImageVector.vectorResource(id = idProvider()),
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}

@Composable
fun BackIconImage() {
    IconImage(
        idProvider = { R.drawable.ic_arrow_back },
        contentDescription = null,
        colorFilter = ColorFilter.tint(Color.Black)
    )
}
