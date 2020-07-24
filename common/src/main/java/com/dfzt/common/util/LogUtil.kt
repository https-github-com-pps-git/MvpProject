package com.dfzt.common.util

import android.util.Log

class LogUtil {
    companion object{
        private val TAG: String = "MVPProject"
        private var flag: Boolean = true

        fun v(msg: String){
            if (flag) {
                Log.v(TAG, msg)
            }
        }

        fun v(TAG: String,msg: String){
            if (flag) {
                Log.v(TAG, msg)
            }
        }

        fun d(msg: String){
            if (flag) {
                Log.d(TAG, msg)
            }
        }

        fun d(TAG: String,msg: String){
            if (flag) {
                Log.d(TAG, msg)
            }
        }

        fun e(msg: String){
            if (flag) {
                Log.e(TAG, msg)
            }
        }

        fun e(TAG: String,msg: String){
            if (flag) {
                Log.e(TAG, msg)
            }
        }

        fun i(msg: String){
            if (flag) {
                Log.i(TAG, msg)
            }
        }

        fun i(TAG: String,msg: String){
            if (flag) {
                Log.i(TAG, msg)
            }
        }


    }
}