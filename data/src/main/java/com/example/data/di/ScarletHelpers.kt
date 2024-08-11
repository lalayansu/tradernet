package com.example.data.di

import android.app.Application
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.retry.BackoffStrategy
import com.tinder.scarlet.retry.ExponentialBackoffStrategy

fun createAppForegroundLifecycle(application: Application) =
    AndroidLifecycle.ofApplicationForeground(application)

val BACKOFF_STRATEGY: BackoffStrategy = ExponentialBackoffStrategy(
    initialDurationMillis = 1000,
    maxDurationMillis = 30000
)