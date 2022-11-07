package com.msd117.cryptocompose.detail.data.network

import com.msd117.cryptocompose.detail.data.model.Data
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Retrofit
import javax.inject.Inject

private const val DATA = "data"

class InfoRemote @Inject constructor(retrofitClient: Retrofit) {

    private val infoNetwork = retrofitClient.create(InfoNetwork::class.java)

    suspend fun fetchRemoteInfo(symbol: String): Data? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Data> = moshi.adapter(Data::class.java)

        val response = infoNetwork.fetchInfo(symbol)
        (JSONTokener(response.string()).nextValue() as JSONObject).apply {
            optJSONObject(DATA)?.optJSONObject(symbol)?.let { coinData ->
                return jsonAdapter.fromJson(coinData.toString()) as Data
            }
        }
        return null
    }
}
