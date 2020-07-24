package com.dfzt.mvpproject.ui.photoActivity

import com.dfzt.base.mvp.IBaseModel
import com.dfzt.base.mvp.IBaseModelCallback
import com.dfzt.base.mvp.IBaseView
import com.dfzt.mvpproject.bean.PhotoBean
import io.reactivex.disposables.Disposable

/**
 * 吧这个Activity的所有需要的接口都统一进来
 *
 * */
class PhotoContract {

    /**
     * 定义Modle层的业务逻辑处理
     */
    interface PhotoModel{
        //处理获取图片的逻辑
        fun getPhotos(page: Int, pageIndex: Int,
                      mListener: IBaseModelCallback<PhotoBean>): Disposable?
    }

    /**
     * 定义View层的界面处理
     */
    interface PhotoActivityView : IBaseView{
        /**
         * 获取数据成功
         */
        fun getDataSuccess(mPhotoBean: PhotoBean?)
    }

    /**
     * 定义Presenter层 的接口方法
     */
    interface PhotoPresenter{
        //获取图片数据
        fun getPhotos(page: Int, pageIndex: Int)

        //取消这次网络请求
        fun cancelNet()
    }


}

