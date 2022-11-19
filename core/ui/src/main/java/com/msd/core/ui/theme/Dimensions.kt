package com.msd.core.ui.theme

import androidx.compose.ui.unit.dp
import com.msd.core.ui.theme.Multipliers.lMultiplier
import com.msd.core.ui.theme.Multipliers.sMultiplier
import com.msd.core.ui.theme.Multipliers.xlMultiplier
import com.msd.core.ui.theme.Multipliers.xsMultiplier

private val medium = 8.dp

private object Multipliers {

    const val sMultiplier = .5f

    const val xsMultiplier = .25f

    const val lMultiplier = 2

    const val xlMultiplier = 4
}

object Padding {

    val paddingM = medium

    val paddingS = paddingM.times(sMultiplier)

    val paddingXS = paddingM.times(xsMultiplier)

    val paddingL = paddingM.times(lMultiplier)

    val paddingXL = paddingM.times(xlMultiplier)
}

object Size {

    val sizeM = medium

    val sizeS = sizeM.times(sMultiplier)

    val smallIconSize = 44.dp

    val smallLoadingIconSize = 47.5.dp

    val xSmallIconSize = 34.dp

    val topBarHeight = 48.dp
}

val zero = 0.dp
