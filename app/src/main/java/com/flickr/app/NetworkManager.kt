package com.flickr.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject constructor(
    @ApplicationContext context: Context,
    private val connectivityManager: ConnectivityManager
) {

    private val _connectivityFlow = MutableSharedFlow<NetworkState>()
    val connectivityFlow: Flow<NetworkState> = _connectivityFlow.distinctUntilChanged()

    init {
        connectivityManager.registerDefaultNetworkCallback(
            ConnectivityCallback(_connectivityFlow)
        )
    }

    /**
     * Checks that the current active network has network connectivity.
     * @return true if we have a network connection, false if not.
     */
    fun hasConnection(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

    private class ConnectivityCallback(
        private val flow: MutableSharedFlow<NetworkState>
    ) : ConnectivityManager.NetworkCallback() {

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val connected =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            runBlocking {
                val state = if (connected) NetworkState.Connected else NetworkState.Disconnected
                flow.emit(state)
            }
        }

        override fun onLost(network: Network) {
            runBlocking {
                flow.emit(NetworkState.Disconnected)
            }
        }
    }

    sealed class NetworkState {
        object Connected : NetworkState()
        object Disconnected : NetworkState()
    }
}