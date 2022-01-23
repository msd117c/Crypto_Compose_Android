package com.msd117.cryptocompose.presentation.detail.ui

import android.app.Activity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.msd117.cryptocompose.R
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailState
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.presentation.latest.presenter.initialState
import com.msd117.cryptocompose.presentation.main.ui.MainActivity
import com.msd117.cryptocompose.theme.paddingM
import com.msd117.cryptocompose.theme.paddingS
import com.msd117.cryptocompose.theme.smallIconSize
import com.msd117.cryptocompose.theme.ui.shared.SharedElement
import com.msd117.cryptocompose.theme.ui.shared.SharedElementInfo
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
fun CoinDetailView(viewModel: CoinDetailViewModel, navController: NavController) {
    val currentState by viewModel.getState().collectAsState(initial = initialState)

    Column(modifier = Modifier.fillMaxWidth()) {
        SharedElement(
            tag = viewModel.symbol,
            type = SharedElementInfo.SharedElementType.To,
            modifier = Modifier.fillMaxWidth()
        ) {
            TopAppBar(
                title = {
                    Row {
                        GlideImage(
                            imageModel = viewModel.icon,
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
                            )
                        )
                        Text(
                            text = viewModel.name,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = paddingM, vertical = zero)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
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
        Crossfade(targetState = currentState, animationSpec = tween(1000)) { state ->
            when (state) {
                is CoinDetailState.Loading -> CoinDetailLoadingView()
                is CoinDetailState.Error -> CoinDetailErrorView()
                is CoinDetailState.Loaded -> CoinDetailLoadedView(state.coinDetail)
            }
        }
    }
}
