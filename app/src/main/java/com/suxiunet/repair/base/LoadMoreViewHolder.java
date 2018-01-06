package com.suxiunet.repair.base;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * 描述 用来显示加载更多、加载更多失败、下载更多到底了的ViewHolder。
 * 创建人 kelin
 * 创建时间 2017/5/28  下午2:09
 * 版本 v 1.0.0
 */

public class LoadMoreViewHolder<D extends ViewDataBinding> extends BasicHolder<Object,D> {

    public LoadMoreViewHolder(View itemView) {
        super(itemView);
    }
    @Override
    protected void bindData(Object data) {
    }
}
