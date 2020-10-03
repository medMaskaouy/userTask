package com.example.usertasksmanager.api
import android.content.Context
import android.util.Log
import com.example.usertasksmanager.utils.Utils
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object RetrofitApi {

        private  var retrofit: Retrofit? = null

         fun getInstance(context : Context): Retrofit {
             if(retrofit == null){
                 val httpCacheDirectory = File(context.cacheDir, "cache_file")

                 val myCache = Cache(httpCacheDirectory, Utils.CACHE_SIZE)
                 val gson = GsonBuilder().create()

                 retrofit = Retrofit.Builder()
                     .baseUrl(Utils.BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .client(okHttpClient(myCache ,context))
                     .build()
             }

             Log.e("RETROFIT", retrofit.toString())
             return retrofit!!
        }

        private fun okHttpClient(cache : Cache,context: Context): OkHttpClient {
            return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor())
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (Utils.hasNetwork(context)!!)
                        request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + 5)
                            .build()
                           // .cacheControl(CacheControl.FORCE_NETWORK).build()
                    else
                        request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                           // .cacheControl(CacheControl.FORCE_CACHE)
                            .build()
                    chain.proceed(request)
                }
                .build()
        }


    fun getTasksApi(context : Context) = getInstance(context).create(TaskApi::class.java)

    fun getUsersApi(context : Context) =  getInstance(context).create(UserApi::class.java)

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor {
                message -> Log.i("LoggingInterceptor\n",message) }
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return httpLoggingInterceptor
    }

}