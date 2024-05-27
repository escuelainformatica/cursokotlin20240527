package cl.eftec.myapplication

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostVistaModelo {
    var cargando=mutableStateOf<String>("")
    var mutable= mutableStateOf<Post>(Post())

    var listamutable = mutableStateListOf<Post>()
    var listadotareas = mutableStateListOf<Todos>()


    fun leerPost(
        id: String,
        vm: PostVistaModelo
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(PostApi::class.java)

        val call: Call<Post?>? = api.getPostById(id);

        call!!.enqueue(object: Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if(response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    vm.mutable.value = response.body()!!
                }
            }
            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
            }
        })
    }
    fun obtenerPosts(vm: PostVistaModelo) {
        vm.cargando.value="cargando datos...."
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(PostApi::class.java)

        val call: Call<List<Post>?>? = api.getPosts();

        call!!.enqueue(object: Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                if(response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    vm.listamutable.clear()
                    var listado=response.body()
                    if(listado!=null) {
                        for (item in listado) {
                            vm.listamutable.add(item)
                        }
                    }
                    vm.cargando.value=""
                    //vm.listamutable= response.body()!!
                }
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
                vm.cargando.value="Error"
            }
        })
    }
    fun obtenerTodos(vm: PostVistaModelo) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(PostApi::class.java)

        val call: Call<List<Todos>?>? = api.getTodos();

        call!!.enqueue(object: Callback<List<Todos>?> {
            override fun onResponse(call: Call<List<Todos>?>, response: Response<List<Todos>?>) {
                if(response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    vm.listadotareas.clear()
                    var listado=response.body()
                    if(listado!=null) {
                        for (item in listado) {
                            vm.listadotareas.add(item)
                        }
                    }
                    vm.cargando.value=""
                    //vm.listamutable= response.body()!!
                }
            }

            override fun onFailure(call: Call<List<Todos>?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
            }
        })
    }

}