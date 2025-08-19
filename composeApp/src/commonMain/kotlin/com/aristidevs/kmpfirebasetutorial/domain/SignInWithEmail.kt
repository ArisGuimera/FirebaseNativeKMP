package com.aristidevs.kmpfirebasetutorial.domain

import com.aristidevs.kmpfirebasetutorial.AuthRepository

class SignInWithEmail(private val repository: AuthRepository) {

    suspend operator fun invoke(user: String, password: String) = repository.signInWithEmail(user, password)

}