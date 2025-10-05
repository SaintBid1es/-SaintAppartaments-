package org.example.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.state.LoginIntent
import org.example.project.presentation.state.LoginState

import org.example.project.ui.theme.Purple
import org.example.project.viewmodel.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


object MainScreen: Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MainViewModel = remember { MainViewModel() }
        MainScreen(viewModel,navigator)
    }


}



@Composable
fun MainScreen(viewModel: MainViewModel,navigator: Navigator){
        val state = viewModel.state.collectAsState().value
        when(state){
            is LoginState.Error ->ErrorView(state.message)
            LoginState.Idle ->{
                MainScreenSucces(navigator)
            }
            LoginState.Loading ->LoadingView()
            is LoginState.Success -> MainScreenSucces(navigator)
        }

}
@Composable
@Preview
fun MainScreenSucces(navigator: Navigator){
    val viewmodel = remember { MainViewModel() }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(10.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        Text(text = "Войти",
            modifier = Modifier.padding(top = 40.dp), fontSize = 20.sp)
        TextField(
            value = viewmodel.email,
            onValueChange ={viewmodel.proccesIntent(LoginIntent.InputEmail(it),navigator)},
            label = {Text("Напишите свой email")}
        )
        TextField(
            value = viewmodel.password,
            onValueChange ={viewmodel.proccesIntent(LoginIntent.InputPassword(it),navigator)},
            label = {Text("Напишите свой пароль")}
        )
        Button(onClick = {
            viewmodel.proccesIntent(LoginIntent.ClickButtonLogin,navigator)
        }, modifier = Modifier.fillMaxWidth(0.75f)){
            Text("Подтвердить")
        }
        Text("Зарегистрироваться?", fontWeight = FontWeight.Bold, color = Purple, modifier = Modifier.clickable{
            viewmodel.proccesIntent(LoginIntent.ClickButtonRegister,navigator)
        })
        Text("Забыли пароль?",fontWeight = FontWeight.Bold, color = Purple, modifier = Modifier.clickable{
            viewmodel.proccesIntent(LoginIntent.ClickButtonForgotPassword,navigator)
        })

    }



}


@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.Companion.fillMaxSize(),
        contentAlignment = Alignment.Companion.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String) {
    Box(
        modifier = Modifier.Companion.fillMaxSize(),
        contentAlignment = Alignment.Companion.Center
    ) {
        Text(
            text = "Error: $message",
            color = Color.Companion.Red,
            fontSize = 18.sp
        )
    }
}