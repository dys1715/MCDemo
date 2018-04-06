package dys.mcdemo.frame.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by dys on 2018/4/5 0005.
 */
object A {
    private val BASE_URL = "http://baobab.wandoujia.com/api/"
    private var clients = HashMap<Class<*>, Any>().toMutableMap()
    private val retrofit: Retrofit
    private val DEFAULT_TIMEOUT : Long = 10
    private val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun <T> get(clazz: Class<T>): T {
        if (!clients.containsKey(clazz)){
            clients.put(clazz, retrofit.create(clazz)!!)
        }
        return clients[clazz] as T
    }
}