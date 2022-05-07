package com.example.eatroom

import com.example.eatroom.model.repository.OrderRepository
import com.example.eatroom.model.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRestaurantRepository(): RestaurantRepository {
        return RestaurantRepository()
    }

    @Provides
    @Singleton
    fun provideOrderRepository(): OrderRepository {
        return OrderRepository()
    }
}