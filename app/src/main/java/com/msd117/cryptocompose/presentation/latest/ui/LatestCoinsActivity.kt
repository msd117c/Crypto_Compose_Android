package com.msd117.cryptocompose.presentation.latest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.msd117.cryptocompose.presentation.detail.ui.COIN_SYMBOL_PARAMETER
import com.msd117.cryptocompose.presentation.detail.ui.CoinDetailActivity
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestCoinsActivity : AppCompatActivity() {

    private val viewModel: LatestCoinsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun openCoinDetail(symbol: String) {
        val intent = Intent(this, CoinDetailActivity::class.java).apply {
            putExtra(COIN_SYMBOL_PARAMETER, symbol)
        }

        startActivity(intent)
    }
}
