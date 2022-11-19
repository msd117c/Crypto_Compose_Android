package com.msd.core.network_capabilities

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import com.msd.domain.network_capabilities.INetworkCapabilitiesProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NetworkCapabilitiesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : INetworkCapabilitiesProvider {

    private val connectedState = MutableStateFlow(false)
    override fun getConnectedState(): Flow<Boolean> = connectedState

    private val connectivityCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
            val connected = capabilities.hasCapability(NET_CAPABILITY_INTERNET)
            notifyConnectedState(connected)
        }

        override fun onLost(network: Network) {
            notifyConnectedState(false)
        }
    }

    init {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.registerDefaultNetworkCallback(connectivityCallback)
    }

    fun notifyConnectedState(isConnected: Boolean) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                connectedState.emit(isConnected)
            }
        }
    }
}
