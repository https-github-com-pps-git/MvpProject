package com.dfzt.mvpproject.api

import com.dfzt.network.base.BaseNetWorkUtil

class PhotoNetworUtil : BaseNetWorkUtil {

    private constructor(): super("http://gank.io/api/")

    companion object{
        @Volatile
        private var mPhotoApi: PhotoNetworUtil? = null

        fun getInstance(): PhotoNetworUtil?{
            if (mPhotoApi == null){
                synchronized(this){
                    if (mPhotoApi == null){
                        mPhotoApi = PhotoNetworUtil()
                    }
                }
            }
            return mPhotoApi
        }
    }

    fun <T> getServiceApi(cls: Class<T>): T? {
        return getInstance()?.getRetrofit(cls)?.create(cls)
    }

}