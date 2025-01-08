package com.tawuniya.userinfo.application

import android.app.Application
import com.tawuniya.userinfo.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger

@HiltAndroidApp
class UserInfoApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        plantTimberTrees()
        initRemoteDebugger()
    }



    private fun plantTimberTrees() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.plant(RemoteDebuggerTree())
        }
    }


    private fun initRemoteDebugger() {
        if (!BuildConfig.DEBUG) return
        val remoteDebugger =
            AndroidRemoteDebugger.Builder(applicationContext).disableInternalLogging()
                .port(getRemoteDebuggerPort()).build()
        AndroidRemoteDebugger.init(remoteDebugger)
    }

    private fun getRemoteDebuggerPort() = 9098

    private inner class RemoteDebuggerTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            AndroidRemoteDebugger.Log.log(priority, tag, message, t)
        }
    }
}