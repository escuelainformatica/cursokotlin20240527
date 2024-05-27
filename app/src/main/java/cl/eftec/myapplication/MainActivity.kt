package cl.eftec.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.eftec.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyApplicationTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var vm= remember {
                        PostVistaModelo()
                    }
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            //.weight(weight = 1f, fill = false)
                    ) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        ElevatedButton(onClick = { vm.leerPost("1",vm)}) {
                            Text("cargar post")
                        }
                        ElevatedButton(onClick = { vm.obtenerPosts(vm) }) {
                            Text("cargar listado post")
                        }
                        ElevatedButton(onClick = { vm.obtenerTodos(vm) }) {
                            Text("cargar listado todos")
                        }
                        Text(text = vm.cargando.value)
                        Text(text = "titulo: ${vm.mutable.value.title}")
                        for(item in vm.listamutable) {
                            Text(text = "${item.id} ${item.title}")
                        }
                        for(item in vm.listadotareas) {
                            Text(text = "${item.id} ${item.title}")
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}