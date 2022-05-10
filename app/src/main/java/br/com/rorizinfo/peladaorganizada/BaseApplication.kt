package br.com.rorizinfo.peladaorganizada

import android.app.Application
import br.com.rorizinfo.peladaorganizada.di.projectModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            loadKoinModules(projectModules)
        }
    }
}