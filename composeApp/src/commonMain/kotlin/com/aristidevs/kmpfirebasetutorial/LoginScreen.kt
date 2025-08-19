package com.aristidevs.kmpfirebasetutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aristidevs.kmpfirebasetutorial.domain.SignInWithEmail
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun LoginScreen() {
    val singIn = koinInject<SignInWithEmail>()
    var email by remember { mutableStateOf("aris@aris.com") }
    var password by remember { mutableStateOf("123456") }
    var loginResult by remember { mutableStateOf("ESPERANDO") }
    val scope = rememberCoroutineScope()

    Column(
        Modifier.fillMaxSize().safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = email, onValueChange = { email = it})
        TextField(value = password, onValueChange = { password = it})
        Button(onClick = {
            scope.launch {
                val result = singIn(email, password)
                when(result){

                    is AuthResult.Success -> {
                        loginResult = result.user.email.orEmpty()
                    }
                    is AuthResult.Error -> {
                        loginResult = result.message
                    }
                }
            }
        }){
            Text("Login")
        }
        Spacer(Modifier.height(24.dp))
        Text(loginResult)
    }

}