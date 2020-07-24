package com.dfzt.mvpproject.application

import android.app.Application
import com.dfzt.common.util.SPFToolUtil

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SPFToolUtil.getInstance()?.init(this)
    }
}