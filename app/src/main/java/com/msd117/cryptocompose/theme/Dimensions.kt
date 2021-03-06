package com.msd117.cryptocompose.theme

import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.Multipliers.lMultiplier
import com.msd117.cryptocompose.theme.Multipliers.sMultiplier
import com.msd117.cryptocompose.theme.Multipliers.xlMultiplier
import com.msd117.cryptocompose.theme.Multipliers.xsMultiplier

private object Multipliers {

    const val sMultiplier = .5f

    const val xsMultiplier = .25f

    const val lMultiplier = 2

    const val xlMultiplier = 4
}

object Padding {

    val paddingM = 8.dp

    val paddingS = paddingM.times(sMultiplier)

    val paddingXS = paddingM.times(xsMultiplier)

    val paddingL = paddingM.times(lMultiplier)

    val paddingXL = paddingM.times(xlMultiplier)
}

object Size {

    val sizeM = 8.dp

    val sizeS = sizeM.times(sMultiplier)

    val smallIconSize = 44.dp

    val smallLoadingIconSize = 47.5.dp

    val xSmallIconSize = 34.dp
}

val zero = 0.dp
