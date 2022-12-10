package com.msd.latest_coins.detail.presenter

import com.msd.latest_coins.detail.presenter.CoinDetailState.*
import com.msd.latest_coins.detail.presenter.helper.FetchCoinDetailHelper
import com.msd.latest_coins.detail.presenter.model.CoinDetail
import com.msd.unit_test.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest : ViewModelTest<CoinDetailState, CoinDetailViewModel>() {

    private val fetchCoinDetailHelper: FetchCoinDetailHelper = mock()
    private val symbol = "BTC"
    private val icon = "icon"
    private val name = "Bitcoin"

    override val viewModel = CoinDetailViewModel(
        fetchCoinDetailHelper,
        symbol,
        icon,
        name
    )

    private val coinDetail = CoinDetail(
        name = "Bitcoin",
        symbol = "BTC",
        description = "description",
        dateAdded = "",
        tagNames = listOf("tagNames"),
        technicalButtons = emptyList(),
        urlButtons = emptyList()
    )
    private val coinData = CoinData(symbol, icon, name)

    @Test
    fun `when initializing successfully should return the expected states`() = test(
        conditions = {
            whenever(fetchCoinDetailHelper(symbol)).thenReturn(coinDetail)
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(
                results == listOf(
                    Uninitialized(coinData),
                    Loading(coinData),
                    Loaded(coinData, coinDetail)
                )
            )
            verify(fetchCoinDetailHelper, only()).invoke(symbol)
        }
    )

    @Test
    fun `when initializing with errors should return the expected states`() = test(
        conditions = {
            whenever(fetchCoinDetailHelper(symbol)).thenAnswer { throw Exception() }
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(
                results == listOf(
                    Uninitialized(coinData),
                    Loading(coinData),
                    Error(coinData)
                )
            )
            verify(fetchCoinDetailHelper, only()).invoke(symbol)
        }
    )

    @Test
    fun `when already initialized should not initialize again`() = test(
        conditions = {
            whenever(fetchCoinDetailHelper(symbol)).thenAnswer { throw Exception() }
            viewModel.initialize()
        },
        execution = viewModel::initialize,
        assertions = { results ->
            assert(results == listOf(Error(coinData)))
            verify(fetchCoinDetailHelper, only()).invoke(symbol)
        }
    )
}
