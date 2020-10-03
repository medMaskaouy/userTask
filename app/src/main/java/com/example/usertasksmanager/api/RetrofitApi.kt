package com.example.usertasksmanager.api
import android.content.Context
import com.example.usertasksmanager.utils.Utils
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitApi {

        private  var retrofit: Retrofit? = null

         fun getInstance(context : Context): Retrofit {
             if(retrofit == null){
                 val cacheSize = 10 * 1024 * 1024 // 10 MB

                 val cache = Cache(context.cacheDir, cacheSize.toLong())
                 val gson = GsonBuilder().create()
                 retrofit = Retrofit.Builder()
                     .baseUrl(Utils.BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .client(okHttpClient(cache,context))
                     .build()
             }

             return retrofit!!
        }

    var onlineInterceptor: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response? {
            val response: Response = chain.proceed(chain.request())
            val maxAge = 60
            return response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
    }


    fun offlineInterceptor(context: Context) = Interceptor { chain ->
        var request: Request = chain.request()
        if (!Utils.hasNetwork(context)!!) {
            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

        private fun okHttpClient(cache : Cache,context: Context): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(offlineInterceptor(context))
                .addNetworkInterceptor (onlineInterceptor)
                .cache(cache)
                .build()
        }


    fun getTasksApi(context : Context) = getInstance(context).create(TaskApi::class.java)

    fun getUsersApi(context : Context) =  getInstance(context).create(UserApi::class.java)


}