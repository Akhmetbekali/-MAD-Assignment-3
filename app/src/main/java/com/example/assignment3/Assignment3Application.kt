package com.example.assignment3


import android.app.Application
import com.example.assignment3.di.ApplicationComponent
import com.example.assignment3.di.DaggerApplicationComponent
import com.example.assignment3.di.RoomModule


class Assignment3Application : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        appComponent = DaggerApplicationComponent
            .builder().roomModule(RoomModule(this))
            .build()
    }

    companion object {
        lateinit var APPLICATION: Application
    }
}