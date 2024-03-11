package com.example.feature_player.di

import android.app.Activity
import android.content.Context
import com.example.feature_player.domain.ActivityProvider
import com.example.feature_player.domain.PlayerController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlayerModule(
    private val applicationContext: Context,
    private val activity: Class<out Activity>,
) {

    @Singleton
    @Provides
    fun provideMainActivity(): ActivityProvider = ActivityProvider(activity)

    @Singleton
    @Provides
    fun providePlayerController(): PlayerController =
        PlayerController(applicationContext, activity)

}