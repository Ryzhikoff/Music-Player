package com.example.feature_player.domain

import android.app.Activity
import javax.inject.Inject

data class ActivityProvider @Inject constructor(
    val activityClass: Class<out Activity>,
)