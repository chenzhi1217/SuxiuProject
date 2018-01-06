package com.suxiunet.repair.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : chenzhi
 * time   : 2017/12/20
 * desc   : 时刻监听RecyclerView数据的变化，动态去判断空布局的显示与隐藏
 */
public class CustomRecyclerView extends RecyclerView {
    private View emptyView;//空数据的布局

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            checkIfEmpty();
        }
    };


    @Override
    public void setAdapter(Adapter adapter) {
        Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
        if (adapter != null) {
            adapter.registerAdapterDataObserver(adapterDataObserver);
        }
        super.setAdapter(adapter);

        checkIfEmpty();
    }

    public void setEmptyView(View emptyView) {
        if (emptyView == null) {
            return;
        }
        this.emptyView = emptyView;
    }

    private void checkIfEmpty() {
        Adapter adapter = getAdapter();
        if (emptyView != null && adapter != null) {
            boolean isEmpty = adapter == null || adapter.getItemCount() == 0;

            emptyView.setVisibility(isEmpty ? View.VISIBLE : GONE);
            setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        }
    }
}
