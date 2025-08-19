package com.aristidevs.kmpfirebasetutorial

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform