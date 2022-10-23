package com.hana897trx.testheroes.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.RequestManager
import com.hana897trx.testheroes.data.remote.heroes.service.HeroesService
import com.hana897trx.testheroes.utils.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDI {
    @Provides
    @Singleton
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Network.URL + Network.API_KEY + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext ctx: Context) : RequestManager =
        Glide.with(ctx)

    @Provides
    fun getHeroService(retrofit: Retrofit) : HeroesService =
        retrofit.create(HeroesService::class.java)
}