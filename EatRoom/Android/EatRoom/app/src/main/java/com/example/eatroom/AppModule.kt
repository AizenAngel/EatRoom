package com.example.eatroom

import com.example.eatroom.model.remote.RestaurantApi
import com.example.eatroom.model.repository.OrderRepository
import com.example.eatroom.model.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRestaurantRepository(): RestaurantRepository {
        return RestaurantRepository(provideRestaurantApi())
    }

    @Provides
    @Singleton
    fun provideOrderRepository(): OrderRepository {
        return OrderRepository()
    }

    @Provides
    @Singleton
    fun provideRestaurantApi(): RestaurantApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8002")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(RestaurantApi::class.java)
    }
}