package com.aristidevs.kmpfirebasetutorial.domain

import com.aristidevs.kmpfirebasetutorial.AuthRepository

class ObserveAuthState (private val repository: AuthRepository) {
    operator fun invoke() = repository.authState
}