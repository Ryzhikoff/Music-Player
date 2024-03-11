package com.example.feature_player.domain

import com.example.core.ui.models.TrackUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow

object MediaPlayerState {

    private val _stateListener: MutableStateFlow<State> = MutableStateFlow(Init)
    val stateListener: SharedFlow<State> = _stateListener.asStateFlow()

    internal fun preparingPlay(trackUi: TrackUi) {
        _stateListener.tryEmit(Preparing(trackUi))
    }

    internal fun pause(trackUi: TrackUi) {
        _stateListener.tryEmit(Paused(trackUi))
    }

    internal fun playing(trackUi: TrackUi) {
        _stateListener.tryEmit(Playing(trackUi))
    }

    internal fun stop(trackUi: TrackUi) {
        _stateListener.tryEmit(Stopped(trackUi))
    }

    sealed class State {
        data object Init : State()
        class Preparing(val trackUi: TrackUi): State()
        class Playing(val trackUi: TrackUi) : State()
        class Paused(val trackUi: TrackUi) : State()
        class Stopped(val trackUi: TrackUi) : State()
    }
}

typealias Playing = MediaPlayerState.State.Playing
typealias Paused = MediaPlayerState.State.Paused
typealias Stopped = MediaPlayerState.State.Stopped
typealias Preparing = MediaPlayerState.State.Preparing
typealias Init = MediaPlayerState.State.Init