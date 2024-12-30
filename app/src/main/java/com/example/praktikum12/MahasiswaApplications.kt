package com.example.praktikum12

import android.app.Application
import com.example.praktikum12.repository.AppContainer
import com.example.praktikum12.repository.MahasiswaContainer

class MahasiswaApplications:Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}