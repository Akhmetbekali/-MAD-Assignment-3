package com.example.assignment3.di

import com.example.assignment3.BuildConfig
import com.example.assignment3.data.PetfinderService
import com.example.assignment3.data.TokenService
import com.example.assignment3.data.interceptors.AuthInterceptor
import com.example.assignment3.data.interceptors.AuthenticationInterceptorRefreshToken
import com.example.assignment3.sharedpreferences.SharedPreferencesWrapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("withAuth")
    fun providesAuthOkHttpClient(
        authInterceptor: AuthInterceptor,
        refreshTokenInterceptor: AuthenticationInterceptorRefreshToken
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(refreshTokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("withoutAuth")
    fun providesWithOutAuthOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @Named("withAuth")
    fun providesAuthRetrofit(@Named("withAuth") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("withoutAuth")
    fun provideWithoutAuthRetrofit(@Named("withoutAuth") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        @Named("secure")
        secureSharedPrefs: SharedPreferencesWrapper
    ): AuthInterceptor {
        return AuthInterceptor(secureSharedPrefs)
    }

    @Provides
    @Singleton
    fun provideRefreshTokenInterceptor(
        tokenService: TokenService,
        @Named("secure") secureSharedPrefs: SharedPreferencesWrapper
    ): AuthenticationInterceptorRefreshToken {
        return AuthenticationInterceptorRefreshToken(
            tokenService,
            secureSharedPrefs
        )
    }

    @Provides
    @Singleton
    fun provideTokenService(@Named("withoutAuth") retrofit: Retrofit): TokenService {
        return retrofit.create(TokenService::class.java)
    }

    @Provides
    @Singleton
    fun providePetFinderService(@Named("withAuth") retrofit: Retrofit): PetfinderService {
        return retrofit.create(PetfinderService::class.java)
    }
}