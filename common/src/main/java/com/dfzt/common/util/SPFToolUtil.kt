package com.dfzt.common.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class SPFToolUtil {
    private constructor()
    private var mSpf: SharedPreferences? = null
    companion object{
        @Volatile
        private var mSPFToolUtil: SPFToolUtil? = null

        private val SPF_NAME = "MVP_SP"
        fun getInstance(): SPFToolUtil?{
            if (mSPFToolUtil == null){
                synchronized(this){
                    if (mSPFToolUtil == null){
                        mSPFToolUtil = SPFToolUtil()
                    }
                }
            }
            return mSPFToolUtil
        }
    }

    fun init(context: Context){
        mSpf = context.getSharedPreferences(SPF_NAME,Context.MODE_PRIVATE)
    }

    fun getStringValue(key: String): String? {
        return mSpf?.getString(key,"")
    }

    fun getStringValue(key: String, dValue: String): String? {
        return mSpf?.getString(key, dValue)
    }


    fun putStringValue(key: String, value: String) {
        val editor = mSpf?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun putIntValue(key: String, value: Int) {
        val editor = mSpf?.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun getIntValue(key: String, value: Int): Int? {
        return mSpf?.getInt(key, value)
    }

    fun putBooleanValue(key: String, value: Boolean) {
        val editor = mSpf?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun getBooleanValue(key: String, value: Boolean): Boolean? {
        return mSpf?.getBoolean(key, value)
    }

    fun putLongValue(key: String, value: Long) {
        val editor =  mSpf?.edit()
        editor?.putLong(key, value)
        editor?.apply()
    }

    fun getLongValue(key: String, value: Long): Long? {
        return mSpf?.getLong(key, value)
    }

    fun removeSPFKey(key: String) {
        val editor =  mSpf?.edit()
        editor?.remove(key)
        editor?.apply()
    }
}