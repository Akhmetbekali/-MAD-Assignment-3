package com.example.assignment3.di


import com.example.assignment3.di.SharedPrefsModule
import com.example.assignment3.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, RoomModule::class, RepositoryModule::class, SharedPrefsModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}