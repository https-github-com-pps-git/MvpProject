package com.dfzt.mvpproject.ui.photoActivity

import com.dfzt.base.mvp.BasePresenter
import com.dfzt.base.mvp.IBaseModelCallback
import com.dfzt.common.util.LogUtil
import com.dfzt.mvpproject.bean.PhotoBean
import io.reactivex.disposables.Disposable

class PhotoPresenterImpl : BasePresenter<PhotoContract.PhotoActivityView> ,
    PhotoContract.PhotoPresenter {

    private var mPhotoModel: PhotoModelImpl? = null
    private var mDisposable: Disposable? = null
    constructor(){
        mPhotoModel = PhotoModelImpl()
    }

    override fun getPhotos(page: Int, pageIndex: Int) {
        mView?.start()
        mDisposable = mPhotoModel?.getPhotos(page,pageIndex,object : IBaseModelCallback<PhotoBean>{
            override fun getDataSuccess(t: PhotoBean) {
                mView?.getDataSuccess(t)
            }

            override fun getDataFailed(msg: String?) {
               mView?.onRefreshFailure(msg)
            }

            override fun onFinish() {
               mView?.onFinish()
            }

        })
    }

    override fun cancelNet() {
        cancelNetwork()
    }

    override fun detachView() {
        super.detachView()
        mPhotoModel = null
        cancelNetwork()
    }

    private fun cancelNetwork() {
        if (mDisposable != null && !mDisposable?.isDisposed!!) {
            mDisposable?.dispose()
            LogUtil.e("取消网络请求")
        }
    }
}