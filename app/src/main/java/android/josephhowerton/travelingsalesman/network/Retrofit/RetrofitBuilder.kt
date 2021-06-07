package android.josephhowerton.travelingsalesman.network.Retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {

    private var gson: Gson? = null
    private var gsonConverterFactory: GsonConverterFactory? = null
    private var okHttpClient: OkHttpClient? = null

    private var retrofitFoursquare: Retrofit? = null
    private val retrofitBuilderFoursquare = Retrofit.Builder()


    init {
        initializeGson()
        initializeOkHttp()
        initializeAdapterFactory()
    }

    private fun initializeGson(){
        if(gson == null){
            val gsonBuilder = GsonBuilder()
            gson = gsonBuilder.create()
            gsonConverterFactory = GsonConverterFactory.create(gson!!)
        }
    }

    private fun initializeOkHttp() {
        val requestTimeout = 15L
        val builder = OkHttpClient().newBuilder()
                .connectTimeout(requestTimeout, TimeUnit.SECONDS)
                .readTimeout(requestTimeout, TimeUnit.SECONDS)
                .writeTimeout(requestTimeout, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.addInterceptor(interceptor)
        builder.addInterceptor { chain ->
            val original = chain.request();
            val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Request-Type", "Android")
                    .addHeader("Content-Type", "application/json");

            val request = requestBuilder.build()

            chain.proceed(request)
        }

        okHttpClient = builder.build();
    }

    private fun initializeAdapterFactory(){
        if(gson == null){
            val gsonBuilder = GsonBuilder()
            gson = gsonBuilder.create()
        }
    }

    @NotNull
    fun <service> getFoursquareService(baseUrl: String, serviceClass: Class<service>): service {
        if (retrofitFoursquare == null) {
            retrofitFoursquare = retrofitBuilderFoursquare.baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory!!)
                    .client(okHttpClient!!)
                    .build()
        }
        return retrofitFoursquare!!.create(serviceClass)
    }


}