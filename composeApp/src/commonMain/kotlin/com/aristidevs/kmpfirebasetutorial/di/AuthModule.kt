package com.aristidevs.kmpfirebasetutorial.di

import com.aristidevs.kmpfirebasetutorial.domain.ObserveAuthState
import com.aristidevs.kmpfirebasetutorial.domain.SignInWithEmail
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authModule = module {
    factory { ObserveAuthState(get()) }
    factoryOf(::SignInWithEmail)
}
