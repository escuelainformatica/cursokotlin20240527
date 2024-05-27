package cl.eftec.myapplication

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

// https://json2kt.com/
data class Post (
    @SerializedName("userId" ) var userId : Int    = 0,
    @SerializedName("id"     ) var id     : Int    = 0,
    @SerializedName("title"  ) var title  : String = "",
    @SerializedName("body"   ) var body   : String = ""
)
data class Todos (
    @SerializedName("userId"    ) var userId    : Int?     = null,
    @SerializedName("id"        ) var id        : Int?     = null,
    @SerializedName("title"     ) var title     : String?  = null,
    @SerializedName("completed" ) var completed : Boolean? = null

)


public interface PostApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("posts/{id}")
    abstract fun getPostById(@Path("id") id: String): Call<Post?>?
    @GET("posts")
    abstract fun getPosts(): Call<List<Post>?>?
    @GET("todos")
    abstract fun getTodos(): Call<List<Todos>?>?
    @POST("posts")
    abstract fun addPost(post:Post)
}
