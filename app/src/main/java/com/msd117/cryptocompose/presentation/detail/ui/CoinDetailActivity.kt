package com.msd117.cryptocompose.presentation.detail.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import com.msd117.cryptocompose.presentation.detail.presenter.CoinDetailViewModel
import com.msd117.cryptocompose.presentation.detail.ui.view.CoinDetailView
import com.msd117.cryptocompose.theme.setUi
import dagger.hilt.android.AndroidEntryPoint

const val COIN_SYMBOL_PARAMETER = "COIN_SYMBOL"

@AndroidEntryPoint
class CoinDetailActivity : AppCompatActivity() {

    private val viewModel: CoinDetailViewModel by viewModels()

    private val symbol: String
        get() = intent.getStringExtra(COIN_SYMBOL_PARAMETER) ?: ""

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
