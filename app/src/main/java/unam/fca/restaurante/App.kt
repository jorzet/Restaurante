package unam.fca.restaurante

import android.app.Application

class App: Application() {
    companion object {
        var INSTANCE: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}