package com.aristidevs.kmpfirebasetutorial

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<AuthUser?>

    suspend fun signInWithEmail(email:String, password:String): AuthResult
}