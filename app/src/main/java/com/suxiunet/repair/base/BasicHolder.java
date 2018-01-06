package com.suxiunet.repair.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : chenzhi
 * time   : 2017/12/06
 * desc   : 所有Holder的基类
 */
public abstract class BasicHolder<T,B extends ViewDataBinding> extends RecyclerView.ViewHolder{


    protected B mBinding;

    public BasicHolder(View itemView) {
        super(itemView);
    }

    public BasicHolder(ViewGroup parent, int resId) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),resId,parent,false).getRoot());
        mBinding = DataBindingUtil.getBinding(this.itemView);
    }

    public void bindHolder(T data){
        bindData(data);
        mBinding.executePendingBindings();
    }

    protected abstract void bindData(T data);


    public void onViewRecycled() {}
}
