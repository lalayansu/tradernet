package com.example.data.di

import android.app.Application
import com.example.data.adapter.FlowStreamAdapter
import com.example.data.adapter.StringMessageAdapterFactory
import com.example.tradernet_test_task.data.BuildConfig
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ScarletModule {
    @Provides
    @Singleton
    fun provideScarlet(
        @OkHttpClientWithoutAuth client: OkHttpClient,
        @Named("gson") gson: Gson,
        application: Application
    ): Scarlet = Scarlet.Builder()
        .webSocketFactory(factory = client.newWebSocketFactory(BuildConfig.BASE_URL_WS))
        .addMessageAdapterFactory(factory = StringMessageAdapterFactory())
        .addMessageAdapterFactory(factory = GsonMessageAdapter.Factory(gson))
        .addStreamAdapterFactory(factory = FlowStreamAdapter.Factory())
        .backoffStrategy(backoffStrategy = BACKOFF_STRATEGY)
        .lifecycle(createAppForegroundLifecycle(application = application))
        .build()
}