package com.aristidevs.kmpfirebasetutorial

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthDataResult
import cocoapods.FirebaseAuth.FIRAuthStateDidChangeListenerHandle
import cocoapods.FirebaseAuth.FIRUser
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Foundation.NSError
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class, ExperimentalCoroutinesApi::class)
class AuthRepositoryIos : AuthRepository {

    private val auth = FIRAuth.auth()
    private val _state = MutableStateFlow<AuthUser?>(null)
    private var handle: FIRAuthStateDidChangeListenerHandle? = null

    init {
        handle = auth.addAuthStateDidChangeListener { _, user: FIRUser? ->
            _state.value = user?.let { AuthUser(it.uid(), it.email()) }
        }
    }

    override val authState: Flow<AuthUser?> = _state.asStateFlow()

    override suspend fun signInWithEmail(
        email: String, password: String
    ): AuthResult {
       return withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<AuthResult> { cont: CancellableContinuation<AuthResult> ->
                auth.signInWithEmail(
                    email = email, password = password
                ) { result: FIRAuthDataResult?, error: NSError? ->
                    if (!cont.isActive) return@signInWithEmail
                    when {
                        error != null -> {
                            cont.resume(AuthResult.Error(error.localizedDescription))
                        }

                        result?.user() == null -> {
                            cont.resume(AuthResult.Error("ERROR"))
                        }

                        else -> {
                            val user = result.user()
                            cont.resume(AuthResult.Success(AuthUser(user.uid(), user.email())))
                        }
                    }
                }
            }
        }
    }
}
