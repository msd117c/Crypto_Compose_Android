package com.msd117.cryptocompose.detail.ui

import android.app.Activity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.activity.MainActivity
import com.msd117.cryptocompose.detail.presenter.CoinDetailState
import com.msd117.cryptocompose.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.latest.presenter.initialState
import com.msd117.cryptocompose.theme.Padding.paddingM
import com.msd117.cryptocompose.theme.Padding.paddingS
import com.msd117.cryptocompose.theme.Size.smallIconSize
import com.msd117.cryptocompose.theme.ui.shared.SharedElement
import com.msd117.cryptocompose.theme.ui.shared.SharedElementInfo
import com.msd117.cryptocompose.theme.ui.widget.TitleText
import com.msd117.cryptocompose.theme.zero
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.EntryPointAccessors

@ExperimentalTransitionApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun coinDetailViewModel(symbol: String, icon: String, name: String): CoinDetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).coinDetailViewModelFactory()

    return viewModel(factory = CoinDetailViewModel.provideFactory(factory, symbol, icon, name))
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun CoinDetailView(viewModel: CoinDetailViewModel) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)

    Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
        if (state is CoinDetailState) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SharedElement(
                    tagProvider = state.coinData::symbol,
                    type = SharedElementInfo.SharedElementType.To,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TopAppBar(
                        title = {
                            CoinDetailTopBarTitleView(
                                iconProvider = state.coinData::icon,
                                nameProvider = state.coinData::name
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = viewModel::popBackStack,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .padding(all = paddingS)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_arrow_back),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.Black)
                                )
                            }
                        }
                    )
                }
                when (state) {
                    is CoinDetailState.Loading -> CoinDetailLoadingView()
                    is CoinDetailState.Error -> CoinDetailErrorView()
                    is CoinDetailState.Loaded -> CoinDetailLoadedView(state::coinDetail)
                    is CoinDetailState.Uninitialized -> Unit
                }
            }
        }
    }
}

@Composable
private fun CoinDetailTopBarTitleView(iconProvider: () -> String, nameProvider: () -> String) {
    Row {
        GlideImage(
            imageModel = iconProvider(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .requiredSize(smallIconSize)
                .align(Alignment.CenterVertically),
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.LightGray,
                durationMillis = 600,
                dropOff = 0.65f,
                tilt = 20f
            ),
            error = ImageVector.vectorResource(id = R.drawable.ic_placeholder)
        )
        TitleText(
            text = nameProvider(),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = paddingM, vertical = zero)
        )
    }
}
