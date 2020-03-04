package com.example.githubfinder.view

import android.app.Application

class CustomApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this.applicationContext as CustomApplication
    }
    companion object{
        private var instance: CustomApplication? = null
        fun getApplication() = instance
    }
}