package com.msd.latest_coins.detail.presenter

import com.msd.latest_coins.common.TestDataBuilder.buildCoinDetail
import com.msd.latest_coins.detail.presenter.CoinDetailState.*
import com.msd.latest_coins.detail.presenter.helper.FetchCoinDetailHelper
import com.msd.core.presentation.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Ignore
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.only
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest : ViewModelTest<CoinDetailState, CoinDetailViewModel>() {

    private val fetchCoinDetailHelper: FetchCoinDetailHelper = mock()
    private val symbol = "Symbol"
    private val icon = "icon"
    private val name = "Name"

    override val viewModel = CoinDetailViewModel(
        fetchCoinDetailHelper,
        symbol,
        icon,
        name
    )

    private val coinDetail = buildCoinDetail()
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
    @Ignore
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
