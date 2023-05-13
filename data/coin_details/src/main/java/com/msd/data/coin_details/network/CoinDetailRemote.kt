package com.msd.data.coin_details.network

import com.msd.data.coin_details.model.CoinDetail
import com.squareup.moshi.JsonAdapter
import org.json.JSONObject
import org.json.JSONTokener
import javax.inject.Inject

private const val DATA = "data"

class CoinDetailRemote @Inject constructor(
    private val network: CoinDetailNetwork,
    private val jsonAdapter: JsonAdapter<CoinDetail>
) {

    suspend fun fetchCoinDetailRemote(symbol: String): CoinDetail? {
        val response = network.fetchCoinDetailRemote(symbol)
        (JSONTokener(response.string()).nextValue() as JSONObject).apply {
            optJSONObject(DATA)?.optJSONObject(symbol)?.let { coinData ->
                return jsonAdapter.fromJson(coinData.toString()) as CoinDetail
            }
        }
        return null
    }
}
