package com.aristidevs.kmpfirebasetutorial

interface AuthResult {

    data class Success(val user: AuthUser) : AuthResult
    data class Error(val message: String = "", val cause: Throwable? = null) : AuthResult
}