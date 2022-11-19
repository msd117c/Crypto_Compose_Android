package com.msd.coin_details.network

import com.msd.coin_details.model.CoinDetail
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Retrofit
import javax.inject.Inject

private const val DATA = "data"

class CoinDetailRemote @Inject constructor(retrofitClient: Retrofit) {

    private val infoNetwork = retrofitClient.create(CoinDetailNetwork::class.java)

    suspend fun fetchCoinDetailRemote(symbol: String): CoinDetail? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<CoinDetail> = moshi.adapter(CoinDetail::class.java)

        val response = infoNetwork.fetchCoinDetailRemote(symbol)
        (JSONTokener(response.string()).nextValue() as JSONObject).apply {
            optJSONObject(DATA)?.optJSONObject(symbol)?.let { coinData ->
                return jsonAdapter.fromJson(coinData.toString()) as CoinDetail
            }
        }
        return null
    }
}
