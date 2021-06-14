package com.msd117.cryptocompose.presentation.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.msd117.cryptocompose.presentation.main.presenter.MainViewModel
import com.msd117.cryptocompose.theme.setUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUi {
            MainView(mainStateFlow = viewModel.getState()) { menuItem ->
                when (menuItem) {
                    MenuItem.COINS -> {
                    }
                    MenuItem.HISTORY -> {
                    }
                    MenuItem.MARKETS -> {
                    }
                }
            }
        }

        viewModel.initialize()
    }
}