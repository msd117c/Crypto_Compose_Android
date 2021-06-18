package com.msd117.cryptocompose.presentation.latest.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.msd117.cryptocompose.presentation.latest.presenter.LatestCoinsViewModel
import com.msd117.cryptocompose.theme.setUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestCoinsActivity : AppCompatActivity() {

    private val viewModel: LatestCoinsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUi {
            LatestCoinsView(stateFlow = viewModel.getState())
        }

        viewModel.fetchLatestCoins()
    }
}