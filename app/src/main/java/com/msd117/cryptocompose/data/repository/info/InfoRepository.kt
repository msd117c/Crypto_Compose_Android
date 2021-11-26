package com.msd117.cryptocompose.data.repository.info

import com.msd117.cryptocompose.data.model.info.Data
import com.msd117.cryptocompose.data.network.info.InfoNetwork
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Retrofit
import javax.inject.Inject


class InfoRepository @Inject constructor(retrofitClient: Retrofit) {

    private val infoNetwork = retrofitClient.create(InfoNetwork::class.java)

    suspend fun fetchInfo(symbol: String): Data? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Data> = moshi.adapter(Data::class.java)

        val response = infoNetwork.fetchInfo(symbol)
        (JSONTokener(response.string()).nextValue() as JSONObject).apply {
            optJSONObject("data")?.optJSONObject(symbol)?.let { coinData ->
                return jsonAdapter.fromJson(coinData.toString()) as Data
            }
        }
        return null
    }
}
