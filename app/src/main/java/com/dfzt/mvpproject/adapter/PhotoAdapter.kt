package com.dfzt.mvpproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dfzt.common.util.LogUtil
import com.dfzt.mvpproject.R
import com.dfzt.mvpproject.bean.PhotoBean
import com.dfzt.mvpproject.bean.Result
import kotlinx.android.synthetic.main.photo_adapter.view.*

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    private var mContext: Context? = null
    private var mLists: MutableList<Result>? = null

    constructor(mContext: Context?){
        this.mContext = mContext
    }

    fun setList(mLists: MutableList<Result>?){
        this.mLists = mLists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.photo_adapter,parent,false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mLists != null){
            mLists!!.size
        }else {
            0
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        Glide.with(mContext).load(mLists?.get(position)?.url)
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.itemView.photo_item_image)


    }


    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view)
}