package com.msd117.cryptocompose.presentation.main.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.msd117.cryptocompose.presentation.latest.ui.LatestCoinsActivity
import com.msd117.cryptocompose.presentation.main.presenter.MainViewModel
import com.msd117.cryptocompose.theme.setUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel.getState()) {
            setUi { MainView(mainStateFlow = this, onClicked = viewModel::onMenuItemClicked) }
            onEach { (_, menuItem) ->
                when (menuItem) {
                    MenuItem.COINS -> startActivity(
                        Intent(this@MainActivity, LatestCoinsActivity::class.java)
                    )
                    MenuItem.HISTORY -> {}
                    MenuItem.MARKETS -> {}
                }
            }
        }

        viewModel.initialize()
    }
}