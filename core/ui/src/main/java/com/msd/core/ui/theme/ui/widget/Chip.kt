package com.msd.core.ui.theme.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.msd.core.ui.theme.Padding.paddingM
import com.msd.core.ui.theme.Padding.paddingS
import com.msd.core.ui.theme.Size.sizeM

@Composable
fun Chip(
    startIcon: () -> ImageVector? = { null },
    isStartIconEnabled: Boolean = false,
    startIconTint: Color = Color.Unspecified,
    onStartIconClicked: () -> Unit = { },
    endIcon: () -> ImageVector? = { null },
    isEndIconEnabled: Boolean = false,
    endIconTint: Color = Color.Unspecified,
    onEndIconClicked: () -> Unit = { },
    color: Color = MaterialTheme.colors.surface,
    contentDescription: String,
    label: String,
    isClickable: Boolean = false,
    onClick: () -> Unit = { }
) {
    Surface(
        modifier = Modifier
            .clickable(
                enabled = isClickable,
                onClick = { onClick() }
            )
            .padding(all = paddingS),
        elevation = sizeM,
        shape = MaterialTheme.shapes.small,
        color = color
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val leader = startIcon()
            val trailer = endIcon()

            if (leader != null) {
                Icon(
                    leader,
                    contentDescription = contentDescription,
                    tint = startIconTint,
                    modifier = Modifier
                        .clickable(enabled = isStartIconEnabled, onClick = onStartIconClicked)
                        .padding(start = paddingS)
                )
            }

            BodyText(text = label, modifier = Modifier.padding(all = paddingM))

            if (trailer != null) {
                Icon(
                    trailer,
                    contentDescription = contentDescription,
                    tint = endIconTint,
                    modifier = Modifier
                        .clickable(enabled = isEndIconEnabled, onClick = onEndIconClicked)
                        .padding(end = paddingS)
                )
            }

        }
    }
}

@Composable
fun SelectableChip(
    label: String,
    contentDescription: String,
    selected: Boolean,
    onClick: (nowSelected: Boolean) -> Unit
) {
    Chip(
        startIcon = { if (selected) Icons.Default.Check else null },
        startIconTint = Color.Black.copy(alpha = 0.5f),
        contentDescription = contentDescription,
        label = label,
        isClickable = true,
        onClick = { onClick(!selected) }
    )
}
