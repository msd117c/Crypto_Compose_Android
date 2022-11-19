package com.msd.latest_coins.detail.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.msd.core.ui.theme.Padding.paddingM
import com.msd.core.ui.theme.Padding.paddingS
import com.msd.core.ui.theme.Size.smallIconSize
import com.msd.core.ui.theme.ui.image.BackIconImage
import com.msd.core.ui.theme.ui.image.UrlImage
import com.msd.core.ui.theme.ui.shared.SharedElement
import com.msd.core.ui.theme.ui.shared.SharedElementInfo
import com.msd.core.ui.theme.ui.widget.TitleText
import com.msd.core.ui.theme.zero
import com.msd.latest_coins.detail.presenter.CoinDetailState
import com.msd.latest_coins.detail.presenter.CoinDetailViewModel
import com.msd.latest_coins.list.presenter.initialState

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
                                BackIconImage()
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
        UrlImage(
            url = iconProvider,
            modifier = Modifier
                .requiredSize(smallIconSize)
                .align(Alignment.CenterVertically)
        )
        TitleText(
            text = nameProvider(),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = paddingM, vertical = zero)
        )
    }
}
