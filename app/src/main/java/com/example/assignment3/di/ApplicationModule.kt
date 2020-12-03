package com.example.assignment3.di

import android.content.Context
import com.example.assignment3.Assignment3Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplication() = Assignment3Application.APPLICATION

    @Provides
    fun provideContext(): Context = Assignment3Application.APPLICATION

}