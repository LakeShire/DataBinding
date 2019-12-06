package com.example.databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class CommonAdapter<T>(
    private val mContext: Context?,
    private val mList: List<T>,
    private val mLayoutId: Int,
    private val mVariableId: Int,
    private val mPresenter: ItemPresenter<T>
) : BaseAdapter() {

    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): T {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding: ViewDataBinding? = null
        binding = if (convertView == null) {
            DataBindingUtil.inflate(LayoutInflater.from(mContext), mLayoutId, parent, false)
        } else {
            DataBindingUtil.getBinding(convertView)
        }
        binding?.setVariable(mVariableId, mList[position])
        binding?.setVariable(BR.presenter, mPresenter)
        return binding!!.root
    }
}
